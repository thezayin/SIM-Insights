package com.thezayin.framework.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdConfigs(
    @SerialName("appOpenAd") val appOpenAd: Boolean = true,
    @SerialName("adOnSplashScreen") val adOnSplashScreen: Boolean = true,
    @SerialName("adOnPremiumClick") val adOnPremiumClick: Boolean = true,
    @SerialName("adOnSettingClick") val adOnSettingClick: Boolean = true,
    @SerialName("adOnServerClick") val adOnServerClick: Boolean = true,
    @SerialName("adOnSearchClick") val adOnSearchClick: Boolean = true,
    @SerialName("adOnBackPress") val adOnBackPress: Boolean = true,
    @SerialName("onServerClick") val onServerClick: Boolean = true,
    @SerialName("nativeAdOnHomeScreen") val nativeAdOnHomeScreen: Boolean = true,
    @SerialName("nativeAdOnResultScreen") val nativeAdOnResultScreen: Boolean = true,
    @SerialName("nativeAdOnResultLoadingDialog") val nativeAdOnResultLoadingDialog: Boolean = true,
    @SerialName("nativeAdOnSettingScreen") val nativeAdOnSettingScreen: Boolean = true,
    @SerialName("nativeAdOnServerScreen") val nativeAdOnServerScreen: Boolean = true,
    @SerialName("nativeAdOnServerLoadingDialog") val nativeAdOnServerLoadingDialog: Boolean = true,
    @SerialName("nativeAdOnWebScreen") val nativeAdOnWebScreen: Boolean = true,
    @SerialName("nativeAdOnWebLoadingDialog") val nativeAdOnWebLoadingDialog: Boolean = true,
    @SerialName("nativeAdOnPremiumScreen") val nativeAdOnPremiumScreen: Boolean = true
)

val defaultAdConfigs = """
   {
   "appOpenAd": true,
   "adOnSplashScreen": true,
   "adOnPremiumClick": true,
   "adOnSettingClick": true,
   "adOnServerClick": true,
   "adOnSearchClick": true,
   "adOnBackPress": true,
   "onServerClick": true,
   "nativeAdOnHomeScreen": true,
   "nativeAdOnResultScreen": true,
   "nativeAdOnResultLoadingDialog": true,
   "nativeAdOnSettingScreen": true,
   "nativeAdOnServerScreen": true,
   "nativeAdOnServerLoadingDialog": true,
   "nativeAdOnWebScreen": true,
   "nativeAdOnWebLoadingDialog": true,
   "nativeAdOnPremiumScreen": true
}
""".trimIndent()