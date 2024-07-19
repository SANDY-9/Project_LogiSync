package com.sandy.logisync.data.network.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.time.LocalDateTime

object CreateBodyUtil {

    fun createArrestRequestBody(token: String, id: String, time: LocalDateTime): RequestBody {
        val json = createArrestJsonBody(token, id, time)
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return body
    }
    fun createWarningRequestBody(
        token: String,
        id: String,
        bpm: String,
        time: LocalDateTime,
    ): RequestBody {
        val json = createWarningJsonBody(token, id, bpm, time)
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return body
    }

    private fun createArrestJsonBody(
        token: String,
        id: String,
        time: LocalDateTime
    ): JSONObject {
        return JSONObject().apply {
            put("message", JSONObject().apply {
                put("token", token)
                put("notification", JSONObject().apply {
                    put("title", id)
                    put("body", time)
                })
            })
        }
    }

    private fun createWarningJsonBody(
        token: String,
        id: String,
        bpm: String,
        time: LocalDateTime,
    ): JSONObject {
        return JSONObject().apply {
            put("message", JSONObject().apply {
                put("token", token)
                put("notification", JSONObject().apply {
                    put("title", id)
                    put("body", "$time [$bpm]")
                })
            })
        }
    }

}
