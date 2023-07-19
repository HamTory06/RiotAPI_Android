package com.api.study.riot_api.util

import android.graphics.Color
import android.util.Log
import com.api.study.riot_api.ui.fragment.account.LoginFragment
import com.api.study.riot_api.ui.fragment.account.SignupFragment

class PatternUtils {
    companion object {
        private val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*[@#\$!%^&+=])(?=\\S+\$).{8,50}\$")
        private val idPattern = Regex("^[\\s\\S]{1,16}$")

        fun isPasswordValid(password: String): Boolean {
            return !passwordPattern.matches(password)
        }

        fun isIdValid(id: String): Boolean {
            return !idPattern.matches(id)
        }
    }
}
