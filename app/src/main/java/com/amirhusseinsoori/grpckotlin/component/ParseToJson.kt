package com.amirhusseinsoori.grpckotlin.component

import com.google.gson.Gson


inline fun <reified T> getArgByGson(json: String): T {
    return Gson().fromJson(json, T::class.java)
}


inline fun <reified T> sendArgByGson(input: T): String {
    return Gson().toJson(input)
}