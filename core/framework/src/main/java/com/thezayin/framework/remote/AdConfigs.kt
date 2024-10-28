package com.thezayin.framework.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdConfigs(
    @SerialName("showPremium") val showPremium: Boolean = false,
    @SerialName("appOpenAd") val appOpenAd: Boolean = false,
    @SerialName("adOnSplash") val adOnSplashScreen: Boolean = false,
    @SerialName("adOnPremium") val adOnPremiumClick: Boolean = false,
    @SerialName("adOnSetting") val adOnSettingClick: Boolean = false,
    @SerialName("adOnServer") val adOnServerClick: Boolean = false,
    @SerialName("adOnSearch") val adOnSearchClick: Boolean = false,
    @SerialName("adOnBack") val adOnBackPress: Boolean = false,
    @SerialName("showHistory") val showHistory: Boolean = false,
    @SerialName("onServerClick") val onServerClick: Boolean = false,
    @SerialName("nativeAdOnHome") val nativeAdOnHomeScreen: Boolean = false,
    @SerialName("nativeAdOnResultScreen") val nativeAdOnResultScreen: Boolean = false,
    @SerialName("nativeAdOnResultLoadingDialog") val nativeAdOnResultLoadingDialog: Boolean = false,
    @SerialName("nativeAdOnSettingScreen") val nativeAdOnSettingScreen: Boolean = false,
    @SerialName("nativeAdOnServerScreen") val nativeAdOnServerScreen: Boolean = false,
    @SerialName("nativeAdOnServerLoadingDialog") val nativeAdOnServerLoadingDialog: Boolean = false,
    @SerialName("nativeAdOnWebScreen") val nativeAdOnWebScreen: Boolean = false,
    @SerialName("nativeAdOnWebLoadingDialog") val nativeAdOnWebLoadingDialog: Boolean = false,
    @SerialName("nativeAdOnPremiumScreen") val nativeAdOnPremiumScreen: Boolean = false,
    @SerialName("showServerList") val showServerList: Boolean = false
)

val defaultAdConfigs = """
   {
   "showPremium": false,
   "appOpenAd": false,
   "adOnSplash": false,
   "adOnPremium": false,
   "adOnSetting": false,
   "adOnServer": false,
   "adOnSearch": false,
   "adOnBack": false,
   "showHistory": false,
   "nativeAdOnHome": false,
   "onServer": false,
   "adOnHistoryClick": false,
   "adOnHistoryLoadingDialog": false,
   "adOnHistorySelection": false,
   "adOnHistoryBackClick": false,
   "nativeAdOnHistoryScreen": false,
   "nativeAdOnResultScreen": false,
   "nativeAdOnResultLoadingDialog": false,
   "nativeAdOnSettingScreen": false,
   "nativeAdOnServerScreen": false,
   "nativeAdOnServerLoadingDialog": false,
   "nativeAdOnWebScreen": false,
   "nativeAdOnWebLoadingDialog": false,
   "nativeAdOnPremiumScreen": false,
   "showServerList": false
}
""".trimIndent()