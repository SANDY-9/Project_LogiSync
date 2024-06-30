package com.core.firebase

import com.core.firebase.common.Constants.ADMIN
import com.core.firebase.common.Constants.DUTY
import com.core.firebase.common.Constants.NAME
import com.core.firebase.common.Constants.PASSWORD
import com.core.firebase.common.Constants.TEL
import com.core.firebase.common.Constants.USERS
import com.core.firebase.model.UserDTO
import com.core.firebase.utils.ErrorMassage.LOGIN_ERROR_MESSAGE
import com.core.firebase.utils.ErrorMassage.NETWORK_ERROR_MESSAGE
import com.core.firebase.utils.LoginError
import com.core.firebase.utils.NetworkError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val ref: DatabaseReference,
) : UserDataSource {

    override fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: String,
        onSuccess: (Boolean) -> Unit,
    ) {
        val id = ref.child(USERS).child(id)
        id.child(PASSWORD).setValue(pwd)
        id.child(NAME).setValue(name)
        id.child(TEL).setValue(tel)
        id.child(DUTY).setValue(duty)

        ref.child(USERS).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // 최초 가입의 경우
                    if (snapshot.children.count() == 1) {
                        id.child(DUTY).setValue(ADMIN)
                    }
                    onSuccess(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    throw NetworkError(NETWORK_ERROR_MESSAGE)
                }
            }
        )
    }

    override fun checkTel(
        tel: String,
        onExisted: (Boolean) -> Unit
    ) {
        ref.child(USERS).orderByChild(TEL).equalTo(tel).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val existed = snapshot.exists()
                    onExisted(existed)
                }

                override fun onCancelled(error: DatabaseError) {
                    throw NetworkError(NETWORK_ERROR_MESSAGE)
                }
            }
        )
    }

    override fun checkId(
        id: String,
        onExisted: (Boolean) -> Unit,
    ) {
        ref.child(id).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val existed = snapshot.exists()
                    onExisted(existed)
                }

                override fun onCancelled(error: DatabaseError) {
                    throw NetworkError(NETWORK_ERROR_MESSAGE)
                }
            }
        )
    }

    override fun login(
        id: String,
        onLogin: (UserDTO) -> Unit,
    ) {
        ref.child(id).equalTo(id).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.child(id).getValue(UserDTO::class.java)
                            ?: throw LoginError(LOGIN_ERROR_MESSAGE)
                        onLogin(user)
                    }
                    else {
                        throw LoginError(LOGIN_ERROR_MESSAGE)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    throw NetworkError(NETWORK_ERROR_MESSAGE)
                }
            }
        )
    }
}
