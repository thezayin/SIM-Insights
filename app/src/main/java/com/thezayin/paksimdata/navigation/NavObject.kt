package com.thezayin.paksimdata.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreenNav

@Serializable
object HomeScreenNav

@Serializable
data class ResultScreenNav(val number: String)

@Serializable
object ServerScreenNav

@Serializable
data class WebScreenNav(val url: String)

@Serializable
object SettingScreenNav

@Serializable
object PremiumScreenNav

@Serializable
object HistoryScreenNav