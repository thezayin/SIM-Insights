package com.thezayin.home.state

import com.thezayin.domain.model.HistoryModel

data class HomeState(
    val getHistory: List<HistoryModel>? = null,
)