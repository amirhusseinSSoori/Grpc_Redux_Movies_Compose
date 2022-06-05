package com.amirhusseinsoori.common


fun String.showMessage(): String {
    return when (this) {
        "UNAVAILABLE" -> "check network connection"
        else -> "ops you have problems try again"
    }
}