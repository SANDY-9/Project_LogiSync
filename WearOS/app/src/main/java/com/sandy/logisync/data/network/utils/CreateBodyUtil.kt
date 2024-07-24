package com.sandy.logisync.data.network.utils

import com.sandy.logisync.model.Arrest.ArrestType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.time.LocalDateTime

object CreateBodyUtil {

    fun createArrestRequestBody(
        id: String,
        time: LocalDateTime,
        name: String,
        tel: String,
        lat: Double,
        lng: Double,
        arrestType: ArrestType,
    ): RequestBody {
        val json = createArrestJsonBody(id, name, tel, lat, lng, time, arrestType)
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return body
    }

    fun createMyArrestRequestBody(
        token: String,
        id: String,
        time: LocalDateTime,
        name: String,
        tel: String,
        lat: Double,
        lng: Double,
        arrestType: ArrestType,
    ): RequestBody {
        val json = createMyArrestJsonBody(token, id, name, tel, lat, lng, time, arrestType)
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return body
    }

    fun createWarningRequestBody(
        id: String,
        name: String,
        tel: String,
        bpm: Int,
        lat: Double,
        lng: Double,
        time: LocalDateTime,
        arrestType: ArrestType,
    ): RequestBody {
        val json = createWarningJsonBody(id, name, tel, bpm, lat, lng, time, arrestType)
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return body
    }

    fun createMyWarningRequestBody(
        token: String,
        id: String,
        name: String,
        tel: String,
        bpm: Int,
        lat: Double,
        lng: Double,
        time: LocalDateTime,
        arrestType: ArrestType,
    ): RequestBody {
        val json = createMyWarningJsonBody(token, id, name, tel, bpm, lat, lng, time, arrestType)
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return body
    }

    private fun createTitle(id: String, name: String, arrestType: ArrestType): String {
        return "${name}($id)님 " + ArrestType.getDesc(arrestType) + "알림"
    }
    private fun createMyTitle(arrestType: ArrestType): String {
        return ArrestType.getDesc(arrestType) + "알림"
    }

    private fun createArrestJsonBody(
        id: String,
        name: String,
        tel: String,
        lat: Double,
        lng: Double,
        time: LocalDateTime,
        arrestType: ArrestType,
    ): JSONObject {
        return JSONObject().apply {
            put("message", JSONObject().apply {
                put("topic", "arrest")
                put("notification", JSONObject().apply {
                    put("title", createTitle(id, name, arrestType))
                    put("body", "신고 내용을 확인해주세요.")
                })
                put("data", JSONObject().apply {
                    put("id", id)
                    put("name", name)
                    put("tel", tel)
                    put("lat", lat.toString())
                    put("lng", lng.toString())
                    put("time", time.toString())
                    put("arrestType", arrestType.name)
                })
            })
        }
    }

    private fun createMyArrestJsonBody(
        token: String,
        id: String,
        name: String,
        tel: String,
        lat: Double,
        lng: Double,
        time: LocalDateTime,
        arrestType: ArrestType,
    ): JSONObject {
        return JSONObject().apply {
            put("message", JSONObject().apply {
                put("token", token)
                put("notification", JSONObject().apply {
                    put("title", createMyTitle(arrestType))
                    put("body", "신고 내용을 확인해주세요.")
                })
                put("data", JSONObject().apply {
                    put("id", id)
                    put("name", name)
                    put("tel", tel)
                    put("lat", lat.toString())
                    put("lng", lng.toString())
                    put("time", time.toString())
                    put("arrestType", arrestType.name)
                })
            })
        }
    }

    private fun createWarningJsonBody(
        id: String,
        name: String,
        tel: String,
        bpm: Int,
        lat: Double,
        lng: Double,
        time: LocalDateTime,
        arrestType: ArrestType,
    ): JSONObject {
        return JSONObject().apply {
            put("message", JSONObject().apply {
                put("topic", "arrest")
                put("notification", JSONObject().apply {
                    put("title", createTitle(id, name, arrestType))
                    put("body", "신고 내용을 확인해주세요.")
                })
                put("data", JSONObject().apply {
                    put("id", id)
                    put("name", name)
                    put("tel", tel)
                    put("bpm", bpm.toString())
                    put("lat", lat.toString())
                    put("lng", lng.toString())
                    put("time", time.toString())
                    put("arrestType", arrestType.name)
                })
            })
        }
    }

    private fun createMyWarningJsonBody(
        token: String,
        id: String,
        name: String,
        tel: String,
        bpm: Int,
        lat: Double,
        lng: Double,
        time: LocalDateTime,
        arrestType: ArrestType,
    ): JSONObject {
        return JSONObject().apply {
            put("message", JSONObject().apply {
                put("token", token)
                put("notification", JSONObject().apply {
                    put("title", createMyTitle(arrestType))
                    put("body", "신고 내용을 확인해주세요.")
                })
                put("data", JSONObject().apply {
                    put("id", id)
                    put("name", name)
                    put("tel", tel)
                    put("bpm", bpm.toString())
                    put("lat", lat.toString())
                    put("lng", lng.toString())
                    put("time", time.toString())
                    put("arrestType", arrestType.name)
                })
            })
        }
    }

}
