package com.sandy.logisync.data.mapper

import com.sandy.logisync.model.Account
import com.sandy.logisync.model.HeartRate
import org.json.JSONObject
import java.time.LocalDateTime

fun String.toAccount(): Account {
    val jsonObject = JSONObject(this)
    return Account(
        id = jsonObject.getString("id"),
        name = jsonObject.getString("name"),
        tel = jsonObject.getString("tel"),
        //duty = jsonObject.getString("duty")
    )
}

fun HeartRate.toJson(): String {
    val jsonObject = JSONObject()
    jsonObject.put("date", date.toString())
    jsonObject.put("value", value)
    return jsonObject.toString()
}

fun String.toHeartRate(): HeartRate {
    val jsonObject = JSONObject(this)
    return HeartRate(
        date = LocalDateTime.parse(jsonObject.getString("date")),
        value = jsonObject.getInt("value")
    )
}
