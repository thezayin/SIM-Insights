package com.thezayin.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultModel(
    val number: String = "",
    val name: String = "",
    val cnic: String = "",
    val address: String = "",
)