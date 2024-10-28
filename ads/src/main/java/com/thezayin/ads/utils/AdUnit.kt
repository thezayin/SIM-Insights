package com.thezayin.ads.utils

class AdUnit private constructor(
    private val id: String, private val testId: String
) {
    companion object {
        val native = AdUnit("ca-app-pub-2913057115284606/3787691757", "ca-app-pub-2913057115284606/3787691757")
        val appOpen = AdUnit("ca-app-pub-2913057115284606/2622149438", "ca-app-pub-2913057115284606/2622149438")
        val rewarded = AdUnit("ca-app-pub-2913057115284606/3648090953", "ca-app-pub-2913057115284606/3648090953")
        val interstitial = AdUnit("ca-app-pub-2913057115284606/5643698909", "ca-app-pub-2913057115284606/5643698909")
    }

    fun resolve(debug: Boolean = false) = if (debug) this.testId else id
}

private const val TEST_NATIVE_ID = "ca-app-pub-3940256099942544/2247696110"
private const val TEST_INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712"
private const val TEST_APP_OPEN_ID = "ca-app-pub-3940256099942544/9257395921"
private const val TEST_REWARDED_ID = "ca-app-pub-3940256099942544/5224354917"