package com.api.study.riot_api.util

import android.graphics.Color
import android.util.Log
import com.api.study.riot_api.ui.fragment.account.LoginFragment
import com.api.study.riot_api.ui.fragment.account.SignupFragment

class PatternUtils {
    companion object {
        private val passwordPattern = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+\$).{8,50}$")
        private val idPattern = Regex("^[\\s\\S]{1,16}$")

        fun isPasswordValid(signupFragment: SignupFragment, password: String): Boolean {
            if (!passwordPattern.matches(password)) {
                signupFragment.passwordErrorMessage(Color.RED, "다시 적어주세요")
            }

            return !passwordPattern.matches(password)
        }

        fun isIdValid(signupFragment: SignupFragment, id: String): Boolean {
            if (!idPattern.matches(id)) {
                signupFragment.idErrorMessage(Color.RED, "다시 적어주세요")
            }
            return !idPattern.matches(id)
        }
    }
}
