package com.sandy.logisync.model

import org.json.JSONObject

data class Account(
    val id: String,
    val name: String,
    val tel: String,
    //val duty: String,
)

fun String.toAccount(): Account {
    val jsonObject = JSONObject(this)
    return Account(
        id = jsonObject.getString("id"),
        name = jsonObject.getString("name"),
        tel = jsonObject.getString("tel"),
        //duty = jsonObject.getString("duty")
    )
}
