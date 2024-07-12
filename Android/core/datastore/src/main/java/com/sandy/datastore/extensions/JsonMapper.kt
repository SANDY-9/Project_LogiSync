package com.sandy.datastore.extensions

import com.sandy.datastore.model.AccountDTO
import org.json.JSONObject

internal fun AccountDTO.toJson(): String {
    val jsonObject = JSONObject()
    jsonObject.put("id", id)
    jsonObject.put("name", name)
    jsonObject.put("tel", tel)
    jsonObject.put("duty", duty)
    return jsonObject.toString()
}

internal fun String.toAccountDTO(): AccountDTO {
    val jsonObject = JSONObject(this)
    return AccountDTO(
        id = jsonObject.getString("id"),
        name = jsonObject.getString("name"),
        tel = jsonObject.getString("tel"),
        duty = jsonObject.getString("duty")
    )
}
