package com.thezayin.paksimdata.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SimDataModel(
    val number: String= "",
    val name: String="",
    val cnic: String="",
    val address: String="",
)