package com.thezayin.framework.extension.ads

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.appopen.AppOpenAd
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.analytics.helpers.AnalyticsHelper

fun showAppOpenAd(
    activity: Activity,
    googleManager: GoogleManager,
    analytics: AnalyticsHelper,
    callBack: (() -> Unit)? = null,
): AppOpenAd? {
    val ad = googleManager.createAppOpenAd()
    ad?.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            callBack?.invoke()
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

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
            callBack?.invoke()
        }
    }
    ad?.show(activity)
    return ad
}