package com.core.firebase

import com.core.firebase.common.Constants.ARREST
import com.core.firebase.common.Constants.ID
import com.core.firebase.model.ArrestDTO
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject

class ArrestClient @Inject constructor(
    private val ref: DatabaseReference,
) {

    fun getLastMyArrestList(
        id: String,
        onSuccess: (Map<String, ArrestDTO>?) -> Unit,
        onError: (Exception) -> Unit,
    ) {
        ref.child(ARREST).orderByChild(ID).equalTo(id).limitToLast(5).get().addOnSuccessListener { snapshot ->
            val result = snapshot.getValue<Map<String, ArrestDTO>>()
            onSuccess(result)
        }.addOnFailureListener { e ->
            onError(e)
        }
    }

    fun getMyArrestList(
        id: String,
        onSuccess: (Map<String, ArrestDTO>?) -> Unit,
        onError: (Exception) -> Unit,
    ) {
        ref.child(ARREST).orderByChild(ID).equalTo(id).get().addOnSuccessListener { snapshot ->
            val result = snapshot.getValue<Map<String, ArrestDTO>>()
            onSuccess(result)
        }.addOnFailureListener { e ->
            onError(e)
        }
    }

    fun getArrestList(
        onSuccess: (Map<String, ArrestDTO>?) -> Unit,
        onError: (Exception) -> Unit,
    ) {
        ref.child(ARREST).get().addOnSuccessListener { snapshot ->
            val result = snapshot.getValue<Map<String, ArrestDTO>>()
            onSuccess(result)
        }.addOnFailureListener { e ->
            onError(e)
        }
    }
}
