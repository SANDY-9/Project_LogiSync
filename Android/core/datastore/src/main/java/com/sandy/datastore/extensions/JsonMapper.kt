package com.sandy.datastore.extensions

import com.sandy.datastore.model.AccountDTO
import com.sandy.datastore.model.DeviceDTO
import org.json.JSONObject

internal fun AccountDTO.toJson(): String {
    val jsonObject = JSONObject()
    jsonObject.put("id", id)
    jsonObject.put("name", name)
    jsonObject.put("tel", tel)
    jsonObject.put("duty", duty)
    jsonObject.put("team", team)
    return jsonObject.toString()
}

internal fun String.toAccountDTO(): AccountDTO {
    val jsonObject = JSONObject(this)
    return AccountDTO(
        id = jsonObject.getString("id"),
        name = jsonObject.getString("name"),
        tel = jsonObject.getString("tel"),
        duty = jsonObject.getString("duty"),
        team = jsonObject.getString("team"),
    )
}

internal fun DeviceDTO.toJson(): String {
    val jsonObject = JSONObject()
    jsonObject.put("name", name)
    jsonObject.put("alias", alias)
    jsonObject.put("id", id)
    return jsonObject.toString()
}

internal fun String.toDeviceDTO(): DeviceDTO {
    val jsonObject = JSONObject(this)
    return DeviceDTO(
        name = jsonObject.getString("name"),
        alias = jsonObject.getString("alias"),
        id = jsonObject.getString("id"),
    )
}
