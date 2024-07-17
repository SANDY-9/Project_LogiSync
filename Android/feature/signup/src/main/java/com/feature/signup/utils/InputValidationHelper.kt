package com.feature.signup.utils

import android.util.Log

object InputValidationHelper {

    fun getValidatedTel(input: String): String {
        // 숫자만 입력 가능
        val tel = input.filter { it.isDigit() }

        // input의 길이가 2보다 작으면 하이픈을 붙이지 않는다.
        if (tel.length < 2) return tel

        // input의 길이가 2보다 크고 02로 시작하면 하이픈을 붙인다
        if (tel.length > 2 && tel.substring(0, 2) == "02") {
            val seoulTel = if (tel.length > 10) tel.substring(0, 10) else tel
            val firstHyphen = seoulTel.substring(0, 2) + "-" + seoulTel.substring(2)

            if (firstHyphen.length > 7) {
                // 뒤에서 4번째 인덱스에 하이픈을 붙인다
                val secondHyphen = firstHyphen.substring(
                    0,
                    firstHyphen.lastIndex - 3
                ) + "-" + firstHyphen.substring(firstHyphen.lastIndex - 3)
                return secondHyphen
            }

            return firstHyphen
        }

        // input의 길이가 3보다 크면 하이픈을 붙인다
        if (input.length > 3) {
            val normalTel = if (tel.length > 11) tel.substring(0, 11) else tel
            val firstHyphen = normalTel.substring(0, 3) + "-" + normalTel.substring(3)

            // 3번째 인덱스에 하이픈을 붙이고 input의 길이가 8보다 크면 뒤에서 4번째 인덱스에 하이픈을 붙인다
            if (firstHyphen.length > 8) {
                // 뒤에서 4번째 인덱스에 하이픈을 붙인다
                val secondHyphen = firstHyphen.substring(
                    0,
                    firstHyphen.lastIndex - 3
                ) + "-" + firstHyphen.substring(firstHyphen.lastIndex - 3)
                return secondHyphen
            }
            return firstHyphen
        }
        return tel
    }
}
