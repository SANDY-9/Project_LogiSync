package com.core.firebase

import android.util.Log
import com.core.firebase.common.Constants.TEAM
import com.core.firebase.common.Constants.TEL
import com.core.firebase.common.Constants.USERS
import com.core.firebase.mappers.toAccount
import com.core.firebase.model.AccountDTO
import com.core.firebase.utils.ErrorMassage.LOGIN_ERROR_MESSAGE
import com.core.firebase.utils.ErrorMassage.NETWORK_ERROR_MESSAGE
import com.core.firebase.utils.LoginError
import com.core.firebase.utils.NetworkError
import com.core.model.Account
import com.core.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthClient @Inject constructor(
    private val ref: DatabaseReference,
    private val messagingClient: MessagingClient,
    private val heartRateClient: HeartRateClient,
) {

    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        onSuccess: (Account) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val user = ref.child(USERS)
        val account = AccountDTO(
            pwd = pwd,
            name = name,
            tel = tel,
        )
        user.orderByChild(TEAM).equalTo(account.team).get().addOnSuccessListener { snapshot ->
            val signupAccount = account.copy(
                duty = if(snapshot.exists()) User.Duty.NORMAL.name else User.Duty.ADMIN.name
            )
            Log.e("확인", "signup: ${snapshot.exists()}", )
            Log.e("확인", "signup: $signupAccount", )
            user.child(id).setValue(signupAccount)

            // FCM토큰 등록
            CoroutineScope(Dispatchers.IO).launch {
                messagingClient.registerToken(
                    id = id,
                    onSuccess = { },
                    onError = { },
                )
            }

            // FCM 구독
            if(signupAccount.duty == User.Duty.ADMIN.name) {
                messagingClient.subscribeToArrestTopic()
            }
            else {
                messagingClient.unsubscribeToArrestTopic()
            }

            // 심박수 임계치 등록
            CoroutineScope(Dispatchers.IO).launch {
                heartRateClient.updateHeartRateCriticalPoint(id)
            }

            onSuccess(signupAccount.toAccount(id))
        }.addOnFailureListener {
            onError(it)
        }
    }

    fun checkTel(
        tel: String,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).orderByChild(TEL).equalTo(tel).get().addOnSuccessListener { snapshot ->
            val existed = snapshot.exists()
            onSuccess(existed)
        }.addOnFailureListener {
            onError(it)
        }
    }

    fun checkId(
        id: String,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).child(id).get().addOnSuccessListener { snapshot ->
            val existed = snapshot.exists()
            onSuccess(existed)
        }.addOnFailureListener {
            onError(it)
        }
    }

    fun login(
        id: String,
        pwd: String,
        onLogin: (Account) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).child(id).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val account = snapshot.getValue<AccountDTO>()
                if (account?.pwd == pwd) {
                    CoroutineScope(Dispatchers.IO).launch {
                        // FCM 토큰 등록
                        messagingClient.registerToken(
                            id = id,
                            onSuccess = { onLogin(account.toAccount(id)) },
                            onError = { e -> onError(e) },
                        )
                    }

                    // FCM 구독
                    if(account.duty == User.Duty.ADMIN.name) {
                        messagingClient.subscribeToArrestTopic()
                    }
                    else {
                        messagingClient.unsubscribeToArrestTopic()
                    }
                    onLogin(account.toAccount(id))
                }
                else onError(LoginError(LOGIN_ERROR_MESSAGE))
            }
            else onError(LoginError(LOGIN_ERROR_MESSAGE))
        }.addOnFailureListener {
            onError(NetworkError(NETWORK_ERROR_MESSAGE))
        }
    }

    fun bioLogin(
        id: String,
        onLogin: (Account) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).child(id).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val account = snapshot.getValue<AccountDTO>()
                account?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        // FCM 토큰 등록
                        messagingClient.registerToken(
                            id = id,
                            onSuccess = { onLogin(account.toAccount(id)) },
                            onError = { e -> onError(e) },
                        )
                    }

                    // FCM 구독
                    if(account.duty == User.Duty.ADMIN.name) {
                        messagingClient.subscribeToArrestTopic()
                    }
                    else {
                        messagingClient.unsubscribeToArrestTopic()
                    }
                    onLogin(account.toAccount(id))
                } ?: onError(LoginError(LOGIN_ERROR_MESSAGE))
            }
            else onError(LoginError(LOGIN_ERROR_MESSAGE))
        }.addOnFailureListener {
            onError(NetworkError(NETWORK_ERROR_MESSAGE))
        }
    }

}
