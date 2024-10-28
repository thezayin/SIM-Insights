package com.thezayin.ads.builders

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

class GoogleNativeAdLoader(
    private val context: Context,
    private val adUnitId: String
) {
    fun loadAd(onAdLoaded: (NativeAd?) -> Unit) {
        var native: NativeAd? = null
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                native = ad
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    onAdLoaded(null)
                }

                override fun onAdLoaded() {
                    onAdLoaded(native)
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }
}