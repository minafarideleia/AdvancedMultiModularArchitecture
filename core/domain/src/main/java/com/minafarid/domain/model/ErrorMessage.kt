package com.minafarid.domain.model

data class ErrorMessage(
  val code: Int,
  val message: String,
  val errorFieldList: List<String>,
)
