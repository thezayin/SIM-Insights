package com.thezayin.framework.extension.ads

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.analytics.Analytics
import com.thezayin.analytics.events.AnalyticsEvent

fun Activity.showInterstitialAd(
    showAd: Boolean,
    googleManager: GoogleManager,
    callback: () -> Unit,
    showLoading: () -> Unit,
    hideLoading: () -> Unit,
) {
    if (!showAd) {
        callback()
        return
    }

    googleManager.getInterstitialAd(
        onLoading = {
            showLoading()
        },
        onAdReady = { interstitialAd ->
            if (interstitialAd != null) {
                hideLoading()
                interstitialAd.fullScreenContentCallback = AdmobInterListener(callback)
                interstitialAd.show(this@showInterstitialAd)
            } else {
                callback()
                hideLoading()
            }
        },
    )
}

class AdmobInterListener(
    private val callback: () -> Unit,
) : FullScreenContentCallback() {
    private var clicks = 0

    override fun onAdClicked() {
        super.onAdClicked()
        clicks++
    }

    override fun onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent()
        callback.invoke()
    }

    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
        super.onAdFailedToShowFullScreenContent(adError)
        callback.invoke()
    }
}
