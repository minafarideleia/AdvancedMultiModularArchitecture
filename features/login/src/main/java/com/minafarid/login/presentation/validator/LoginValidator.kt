package com.minafarid.login.presentation.validator

import com.minafarid.login.presentation.error.LoginError

private const val USERNAME_LENGTH = 5
private const val PASSWORD_MIN_LENGTH = 7
private const val PASSWORD_MAX_LENGTH = 10

object LoginValidator {

  fun userNameError(username: String): LoginError {
    return when {
      username.isEmpty() -> LoginError.NoEntry
      !isValidUserNameLength(username) -> LoginError.InCorrectUsernameLength
      !username.isAlphaNumeric() -> LoginError.InCorrectUserName
      else -> LoginError.NoError
    }
  }

  fun passwordError(password: String): LoginError {
    return when {
      password.isEmpty() -> LoginError.NoEntry
      !isValidPasswordLength(password) -> LoginError.InCorrectPasswordLength
      !password.isAlphaNumericWithSpecialCharacters() -> LoginError.InCorrectPassword
      else -> LoginError.NoError
    }
  }

  private fun String.isAlphaNumericWithSpecialCharacters(): Boolean {
    val containsLowerCase = any { it.isLowerCase() }
    val containsUpperCase = any { it.isUpperCase() }
    val containsSpecialCharacters = any { !it.isLetterOrDigit() }
    val containsDigits = any { it.isDigit() }
    return containsDigits && containsLowerCase && containsUpperCase && containsSpecialCharacters
  }

  private fun isValidPasswordLength(password: String): Boolean =
    password.count() in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH

  private fun isValidUserNameLength(userName: String): Boolean =
    userName.count() > USERNAME_LENGTH

  private fun String.isAlphaNumeric() = matches("[a-zA-Z0-9]+".toRegex())
  fun canDoLogin(userNameError: LoginError, passwordError: LoginError): Boolean {
    return userNameError == LoginError.NoError && passwordError == LoginError.NoError
  }
}
