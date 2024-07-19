package com.core.firebase

import android.content.Context
import android.util.Log
import com.core.firebase.common.Constants.TOKEN
import com.core.firebase.common.Constants.USERS
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.database.DatabaseReference
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class MessagingClient @Inject constructor(
    private val ref: DatabaseReference,
    private val messaging: FirebaseMessaging,
    @ApplicationContext private val context: Context,
) {

    fun getToken(
        onToken: (String) -> Unit,
        onError: (Exception?) -> Unit,
    ) {
        messaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                onError(task.exception)
            }
            else {
                val token = task.result
                Log.e("확인", "getToken: $token", )
                onToken(token)
            }
        }
    }

    fun updateToken(
        id: String,
        token: String,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).child(id).child(TOKEN).setValue(token).addOnFailureListener {
            onError(it)
        }
    }

    fun getAccessToken() : String? {
        val asset = context.resources.assets.open("firebase-admin.json")
        val googleCredential = GoogleCredentials.fromStream(asset)
            .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
        return googleCredential.refreshAccessToken().tokenValue
    }

    fun requestFCM() {
        Log.e("확인", "requestFCM: 실행", )
        val token = "fPJzCSEXQNWzZYXGGx_dk1:APA91bFI6IwtGwE19SLN5SBbJrPOXyqoZkUlWdF3jhiaNWbi1GXrIoC7H-4H3qh4uGYOtZftLZj3yJbPY0uEii7itVsWnn7T7oBDr237_VxnYl6xfxbr-dzPGi5cTOd-C--naeBpY_kp"
        val json = JSONObject().apply {
            put("message", JSONObject().apply {
                put("token", token)
                put("notification", JSONObject().apply {
                    put("title", "테스트")
                    put("body", "과연")
                })
            })
        }
        val accessToken = getAccessToken()
        Log.e("확인", "requestFCM: $accessToken", )
        val client = OkHttpClient().newBuilder().build()
        val request = Request.Builder()
            .url(BuildConfig.FCM_REST_URL)
            .post(json.toString().toRequestBody("application/json".toMediaTypeOrNull()))
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.e("확인", "onFailure: ${e.message}", )
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle success
                val responseBody = response.body?.string()
                Log.e("확인", "onResponse: $responseBody", )
            }
        })
    }

}
