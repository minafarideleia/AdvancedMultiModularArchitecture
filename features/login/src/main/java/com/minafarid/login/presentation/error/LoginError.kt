package com.minafarid.login.presentation.error

import com.minafarid.login.R

sealed class LoginError : Error() {
    abstract fun getErrorMessage(): Int

    // No Entry - default state
    data object NoEntry : LoginError() {
        override fun getErrorMessage(): Int = R.string.no_error
    }

    // No Error
    data object NoError : LoginError() {
        override fun getErrorMessage(): Int = R.string.no_error
    }

    // Incorrect Username
    data object InCorrectUserName : LoginError() {
        override fun getErrorMessage(): Int = R.string.username_error
    }

    // Incorrect Password
    data object InCorrectPassword : LoginError() {
        override fun getErrorMessage(): Int = R.string.password_error
    }

    // Incorrect Username length
    data object InCorrectUsernameLength : LoginError() {
        override fun getErrorMessage(): Int = R.string.username_length_error
    }

    // Incorrect Password length
    data object InCorrectPasswordLength : LoginError() {
        override fun getErrorMessage(): Int = R.string.password_length_error
    }
}