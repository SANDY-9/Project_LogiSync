package com.core.firebase

import com.core.firebase.common.Constants.DUTY
import com.core.firebase.common.Constants.TEL
import com.core.firebase.common.Constants.USERS
import com.core.firebase.mappers.toAccount
import com.core.firebase.model.AccountDTO
import com.core.firebase.utils.EmptyValueError
import com.core.firebase.utils.ErrorMassage.EMPTY_ERROR_MESSAGE
import com.core.firebase.utils.ErrorMassage.LOGIN_ERROR_MESSAGE
import com.core.firebase.utils.ErrorMassage.NETWORK_ERROR_MESSAGE
import com.core.firebase.utils.LoginError
import com.core.firebase.utils.NetworkError
import com.core.model.Account
import com.core.model.Member
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject

class AuthClient @Inject constructor(
    private val ref: DatabaseReference,
    private val messagingClient: MessagingClient,
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
        user.child(id).setValue(account)
        user.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // 최초 가입의 경우
                    val accountDTO = snapshot.getValue<AccountDTO>()
                    accountDTO?.let {
                        var account = it.toAccount(id)
                        if (snapshot.children.count() == 1) {
                            user.child(id).child(DUTY).setValue(Member.Duty.ADMIN.name)
                            account = account.copy(duty = Member.Duty.ADMIN)
                        }
                        messagingClient.registerToken(
                            id = id,
                            onSuccess = { onSuccess(account) },
                            onError = { e -> onError(e) },
                        )
                    }
                    ?: onError(Exception())
                }
                override fun onCancelled(error: DatabaseError) {
                    onError(error.toException())
                }
            }
        )
    }

    fun updateDuty() {

    }

    fun updateCriticalPoint() {

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
        if (id.isEmpty() || pwd.isEmpty()) throw EmptyValueError(EMPTY_ERROR_MESSAGE)
        ref.child(USERS).child(id).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val account = snapshot.getValue<AccountDTO>()
                if (account?.pwd == pwd) {
                    messagingClient.registerToken(
                        id = id,
                        onSuccess = { onLogin(account.toAccount(id)) },
                        onError = { e -> onError(e) },
                    )
                }
                else onError(LoginError(LOGIN_ERROR_MESSAGE))
            }
            else onError(LoginError(LOGIN_ERROR_MESSAGE))
        }.addOnFailureListener {
            onError(NetworkError(NETWORK_ERROR_MESSAGE))
        }
    }

}
