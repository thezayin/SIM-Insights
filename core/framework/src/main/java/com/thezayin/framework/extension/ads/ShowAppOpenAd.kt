package com.thezayin.framework.extension.ads

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.thezayin.ads.GoogleManager

fun Activity.showAppOpenAd(
    googleManager: GoogleManager,
    showAd: Boolean,
    callBack: () -> Unit = {},
) {

    if (!showAd) {
        callBack()
        return
    }

    googleManager.getAppOpenAd(
        onLoading = {},
        onAdReady = { appOpenAd ->
            if (appOpenAd != null) {
                appOpenAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        callBack()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        callBack()
                    }
                }
                appOpenAd.show(this@showAppOpenAd)
            } else {
                callBack()
            }
        },
    )
}