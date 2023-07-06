package com.api.study.riot_api.util

class PasswordUtils {
    companion object {
        private val passwordPattern = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+\$).{8,50}$")

        fun isPasswordValid(password: String): Boolean {
            return passwordPattern.matches(password)
        }
    }
}