package com.thezayin.ads.utils

class AdUnit private constructor(
    private val id: String, private val testId: String
) {
    companion object {
        val native = AdUnit("ca-app-pub-2913057115284606/3787691757", TEST_NATIVE_ID)
        val appOpen = AdUnit("ca-app-pub-2913057115284606/2622149438", TEST_APP_OPEN_ID)
        val rewarded = AdUnit("ca-app-pub-2913057115284606/3648090953", TEST_REWARDED_ID)
        val interstitial = AdUnit("ca-app-pub-2913057115284606/5643698909", TEST_INTERSTITIAL_ID)
    }
    fun resolve(debug: Boolean = false) = if (debug) this.testId else id
}

private const val TEST_NATIVE_ID = "ca-app-pub-3940256099942544/2247696110"
private const val TEST_INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712"
private const val TEST_APP_OPEN_ID = "ca-app-pub-3940256099942544/9257395921"
private const val TEST_REWARDED_ID = "ca-app-pub-3940256099942544/5224354917"