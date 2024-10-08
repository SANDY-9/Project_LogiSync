package com.sandy.logisync.data.network

import android.content.Context
import com.google.auth.oauth2.GoogleCredentials
import com.sandy.logisync.BuildConfig
import com.sandy.logisync.data.network.utils.CreateBodyUtil
import com.sandy.logisync.model.Arrest.ArrestType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

class MyMessagingClient @Inject constructor(
    private val okHttpClient: OkHttpClient,
    @ApplicationContext private val context: Context,
) {

    private fun getAccessToken(): String? {
        val asset = context.resources.assets.open(BuildConfig.MASSAGING_ADMIN)
        val googleCredential = GoogleCredentials
            .fromStream(asset)
            .createScoped(listOf(FIREBASE_MESSAGING_SCOPED))
        val accessToken = googleCredential.refreshAccessToken().tokenValue
        return accessToken
    }

    private fun getAuthorizationHeader(): String {
        val accessToken = getAccessToken()
        return "Bearer $accessToken"
    }

    private fun createArrestRequest(requestBody: RequestBody): Request {
        val request = Request.Builder()
            .url(BuildConfig.FCM_REST_URL)
            .post(requestBody)
            .addHeader(AUTHORIZATION_HEADER, getAuthorizationHeader())
            .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
            .build()
        return request
    }

    private suspend fun OkHttpClient.createCallFlow(request: Request) = callbackFlow {
        newCall(request).enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    close(e)
                }
                override fun onResponse(call: Call, response: Response) {
                    trySend(response)
                }
            }
        )
        awaitClose()
    }

    suspend fun sendArrestMessage(
        id: String,
        time: LocalDateTime,
        lat: Double,
        lng: Double,
        name: String,
        tel: String,
        arrestType: ArrestType,
    ) : Flow<Response> {
        val arrestMessageBody = CreateBodyUtil.createArrestRequestBody(id, time, name, tel, lat, lng, arrestType)
        val request = createArrestRequest(arrestMessageBody)
        return okHttpClient.createCallFlow(request)
    }

    suspend fun sendMyArrestMessage(
        token: String,
        id: String,
        time: LocalDateTime,
        lat: Double,
        lng: Double,
        name: String,
        tel: String,
        arrestType: ArrestType,
    ) : Flow<Response> {
        val arrestMessageBody = CreateBodyUtil.createMyArrestRequestBody(token, id, time, name, tel, lat, lng, arrestType)
        val request = createArrestRequest(arrestMessageBody)
        return okHttpClient.createCallFlow(request)
    }

    suspend fun sendWarningMessage(
        id: String,
        bpm: Int,
        time: LocalDateTime,
        lat: Double,
        lng: Double,
        name: String,
        tel: String,
        arrestType: ArrestType,
    ) : Flow<Response> {
        val warningMessageBody = CreateBodyUtil.createWarningRequestBody(id, name, tel, bpm, lat, lng, time, arrestType)
        val request = createArrestRequest(warningMessageBody)
        return okHttpClient.createCallFlow(request)
    }

    suspend fun sendMyWarningMessage(
        token: String,
        id: String,
        bpm: Int,
        time: LocalDateTime,
        lat: Double,
        lng: Double,
        name: String,
        tel: String,
        arrestType: ArrestType,
    ) : Flow<Response> {
        val warningMessageBody = CreateBodyUtil.createMyWarningRequestBody(token, id, name, tel, bpm, lat, lng, time, arrestType)
        val request = createArrestRequest(warningMessageBody)
        return okHttpClient.createCallFlow(request)
    }


    companion object {
        private const val FIREBASE_MESSAGING_SCOPED = "https://www.googleapis.com/auth/firebase.messaging"
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val CONTENT_TYPE_HEADER = "Content-Type"
        private const val CONTENT_TYPE_JSON = "application/json"
    }
}
