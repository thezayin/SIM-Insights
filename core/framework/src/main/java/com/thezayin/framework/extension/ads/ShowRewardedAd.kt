@file:Suppress("DEPRECATION")

package com.thezayin.framework.extension.ads

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.thezayin.ads.GoogleManager

fun Activity.showRewardedAd(
    showAd: Boolean = true,
    googleManager: GoogleManager,
    callback: () -> Unit = {},
    showLoading: () -> Unit = {},
    hideLoading: () -> Unit = {},
) {
    if (!showAd) {
        callback.invoke()
        return
    }

    if (!isConnected(this)) {
        callback.invoke()
        return
    }

    googleManager.getRewardedAd(
        onLoading = {
            showLoading()
        },
        onAdReady = { rewardedAd ->
            if (rewardedAd != null) {
                hideLoading()
                rewardedAd.fullScreenContentCallback = AdmobRewardListener(callback)
                rewardedAd.show(this@showRewardedAd) { rewardItem ->
                    Log.d("RewardedAd", "User earned the reward: ${rewardItem.type}")
                }
            } else {
                hideLoading()
                callback()
            }
        },
    )
}

@SuppressLint("MissingPermission", "ObsoleteSdkInt")
fun isConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities?.let {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: false

    } else {
        val netInfo = connectivityManager.activeNetworkInfo
        netInfo != null && netInfo.isConnected
    }
}

internal class AdmobRewardListener(
    private val callback: () -> Unit,
) : FullScreenContentCallback() {

    override fun onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent()
        callback.invoke()
    }

    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
        super.onAdFailedToShowFullScreenContent(adError)
        callback.invoke()
    }
}