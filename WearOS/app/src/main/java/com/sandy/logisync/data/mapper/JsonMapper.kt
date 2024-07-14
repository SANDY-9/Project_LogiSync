package com.sandy.logisync.data.mapper

import com.sandy.logisync.model.Account
import org.json.JSONObject

fun String.toAccount(): Account {
    val jsonObject = JSONObject(this)
    return Account(
        id = jsonObject.getString("id"),
        name = jsonObject.getString("name"),
        tel = jsonObject.getString("tel"),
        //duty = jsonObject.getString("duty")
    )
}
