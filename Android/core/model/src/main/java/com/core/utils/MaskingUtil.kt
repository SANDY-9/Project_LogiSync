package com.core.utils

object MaskingUtil {
    fun maskName(name: String): String {
        if(name.isBlank()) return name
        return when (name.length) {
            1 -> name
            2 -> "${name[0]}*"
            else -> name[0] + "*".repeat(name.length - 2) + name.last()
        }
    }
    fun maskId(id: String): String {
        if(id.isBlank()) return id
        val regex = "(?<=.{2}).".toRegex()
        val maskingId = regex.replace(id, "*")
        return maskingId
    }
    fun maskTel(tel: String): String {
        if(tel.isBlank()) return tel
        val firstHyphenIndex = tel.indexOf('-')
        val secondHyphenIndex = tel.indexOf('-', firstHyphenIndex + 1)
        return if (secondHyphenIndex != -1) {
            // 두 번째 하이픈이 있는 경우
            tel.substring(0, firstHyphenIndex + 1) +
                    "*".repeat(secondHyphenIndex - firstHyphenIndex - 1) +
                    tel.substring(secondHyphenIndex)
        }
        else {
            // 두 번째 하이픈이 없는 경우
            tel.substring(0, firstHyphenIndex + 1) +
                    "*".repeat(tel.length - firstHyphenIndex - 1)
        }
    }
}
