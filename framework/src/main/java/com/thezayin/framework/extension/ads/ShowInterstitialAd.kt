package com.thezayin.framework.extension.ads

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.analytics.helpers.AnalyticsHelper
import com.thezayin.framework.remote.RemoteConfig

fun showInterstitialAd(
    googleManager: GoogleManager,
    activity: Activity,
    analytics: AnalyticsHelper,
    remoteConfig: RemoteConfig,
    callBack: () -> Unit,
) {
    if (!remoteConfig.showAdOnAppOpen) {
        callBack()
        return
    }

    googleManager.createInterstitialAd()?.apply {
        fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                callBack()
            }

            override fun onAdImpression() {
                super.onAdImpression()
                analytics.logEvent(
                    AnalyticsEvent(
                        type = AnalyticsEvent.Types.AD_IMPRESSION,
                        extras = listOf(
                            AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.AD_TYPE, "appOpen ad"),
                        ),
                    ),
                )
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                super.onAdFailedToShowFullScreenContent(error)
                callBack()
            }
        }
        show(activity)
    } ?: run { callBack() }
}

fun Activity?.showInterstitialAd(
    googleManager: GoogleManager,
    analytics: AnalyticsHelper,
    remoteConfig: RemoteConfig,
    callback: () -> Unit
) {
    this?.let { showInterstitialAd(googleManager, this, analytics, remoteConfig, callback) }
        ?: callback()
}