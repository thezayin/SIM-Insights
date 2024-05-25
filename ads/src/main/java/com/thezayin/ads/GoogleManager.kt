package com.thezayin.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.thezayin.ads.builders.GoogleAppOpenAdBuilder
import com.thezayin.ads.builders.GoogleInterstitialAdBuilder
import com.thezayin.ads.builders.GoogleNativeAdBuilder
import com.thezayin.ads.builders.GoogleRewardedAdBuilder
import com.thezayin.ads.ump.ConsentManager
import com.thezayin.ads.utils.AdUnit

class GoogleManager(
    private val context: Context, private val consentManager: ConsentManager
) {
    private val debug get() = BuildConfig.DEBUG
    private var googleInterAd: GoogleAd<InterstitialAd>? = null
    private var googleAppOpen: GoogleAd<AppOpenAd>? = null
    private var googleNativeAd: GoogleAd<NativeAd>? = null
    private var googleRewardedAd: GoogleAd<RewardedAd>? = null

    private val testDeviceIds: List<String> = listOf(
        AdRequest.DEVICE_ID_EMULATOR, "990C1C4A58DB7FED6AF5D9A33E3DD1FF",//Samsung,
        "65B571F43583ED2ABB211D2965BE3E11"
    )

    fun init(activity: Activity) {
        consentManager.getUserConsent(activity = activity,
            onConsentGranted = { loadAds() },
            onError = { loadAds() })
    }

    fun initOnLastConsent() = consentManager.ifCanRequestAds { loadAds() }

    private fun loadAds() {
        MobileAds.initialize(context)
        if (debug) MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        )

        googleRewardedAd = ::GoogleRewardedAdBuilder.from(AdUnit.rewarded)
        googleInterAd = ::GoogleInterstitialAdBuilder.from(AdUnit.interstitial)
        googleAppOpen = ::GoogleAppOpenAdBuilder.from(AdUnit.appOpen)
        googleNativeAd = ::GoogleNativeAdBuilder.from(AdUnit.native)
    }

    private fun <T> ((Context, String) -> AdBuilder<T>).from(unit: AdUnit) = GoogleAd(
        this(context, unit.resolve(debug)).withAnalytics()
    )

    private fun <T> AdBuilder<T>.withAnalytics() = apply {
        onPaid {

        }
    }

    fun createAppOpenAd() = ifNotSubscribed { googleAppOpen?.get() }
    fun createInterstitialAd() = ifNotSubscribed { googleInterAd?.get() }
    fun createNativeAd(): NativeAd? = ifNotSubscribed { googleNativeAd?.get() }
    fun createRewardedAd() = ifNotSubscribed { googleRewardedAd?.get() }
    private fun <T : Any?> ifNotSubscribed(block: () -> T?) = block()
}