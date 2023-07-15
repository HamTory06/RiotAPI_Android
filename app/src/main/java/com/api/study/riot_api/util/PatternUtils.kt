package com.api.study.riot_api.util

class PatternUtils {
    companion object {
        private val passwordPattern = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+\$).{8,50}$")
        private val idPattern = Regex("^[\\s\\S]{1,16}$")

        fun isPasswordValid(password: String): Boolean {
            return !passwordPattern.matches(password)
        }

        fun isIdValid(id: String): Boolean{
            return !idPattern.matches(id)
        }
    }
}