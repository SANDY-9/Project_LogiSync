package com.core.firebase

import com.core.firebase.common.Constants.ADMIN
import com.core.firebase.common.Constants.DUTY
import com.core.firebase.common.Constants.TEL
import com.core.firebase.common.Constants.USERS
import com.core.firebase.mappers.toAccount
import com.core.firebase.model.AccountDTO
import com.core.firebase.utils.EmptyValueError
import com.core.firebase.utils.ErrorMassage.LOGIN_ERROR_MESSAGE
import com.core.firebase.utils.ErrorMassage.NETWORK_ERROR_MESSAGE
import com.core.firebase.utils.LoginError
import com.core.firebase.utils.NetworkError
import com.core.model.Account
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject

class AuthClient @Inject constructor(
    private val ref: DatabaseReference,
) {

    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: String,
        onSuccess: (Boolean) -> Unit,
    ) {
        val user = ref.child(USERS)
        val account = AccountDTO(
            pwd = pwd,
            name = name,
            tel = tel,
            duty = duty
        )
        user.child(id).setValue(account)

        val signupListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // 최초 가입의 경우
                if (snapshot.children.count() == 1) {
                    user.child(id).child(DUTY).setValue(ADMIN)
                }
                onSuccess(true)
            }

            override fun onCancelled(error: DatabaseError) {
                throw NetworkError(NETWORK_ERROR_MESSAGE)
            }
        }
        user.addListenerForSingleValueEvent(signupListener)
    }

    fun checkTel(
        tel: String,
        onExisted: (Boolean) -> Unit
    ) {
        ref.child(USERS).orderByChild(TEL).equalTo(tel).get().addOnSuccessListener { snapshot ->
            val existed = snapshot.exists()
            onExisted(existed)
        }.addOnFailureListener {
            throw NetworkError(NETWORK_ERROR_MESSAGE)
        }
    }

    fun checkId(
        id: String,
        onExisted: (Boolean) -> Unit,
    ) {
        ref.child(USERS).child(id).get().addOnSuccessListener { snapshot ->
            val existed = snapshot.exists()
            onExisted(existed)
        }.addOnFailureListener {
            throw NetworkError(NETWORK_ERROR_MESSAGE)
        }
    }

    fun login(
        id: String,
        pwd: String,
        onLogin: (Account) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        if (id.isEmpty() || pwd.isEmpty()) throw EmptyValueError(LOGIN_ERROR_MESSAGE)
        ref.child(USERS).child(id).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val account = snapshot.getValue<AccountDTO>()
                if (account?.pwd == pwd) {
                    onLogin(account.toAccount(id))
                }
                else onError(LoginError(LOGIN_ERROR_MESSAGE))
            }
            else onError(LoginError(LOGIN_ERROR_MESSAGE))
        }.addOnFailureListener {
            onError(NetworkError(NETWORK_ERROR_MESSAGE))
        }
    }
}
