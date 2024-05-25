package com.thezayin.framework.remote

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

private const val SHOW_AD_ON_APP_OPEN = "show_ad_on_app_open"
private const val INIT_ADS = "init_ads"

private const val SERVER_URL_FIRST = "server_1_url"
private const val SERVER_URL_SECOND = "server_2_url"
private const val SERVER_URL_THIRD = "server_3_url"
private const val SERVER_URL_FOURTH = "server_4_url"
private const val SERVER_URL_FIFTH = "server_5_url"

@Suppress("DEPRECATION")
class RemoteConfig(
) {
    private val default: Map<String, Any> = mapOf(
        INIT_ADS to true,
        SHOW_AD_ON_APP_OPEN to true,
        SERVER_URL_FIRST to "https://simdatabase.info/search",
        SERVER_URL_SECOND to "https://simownerdetail.com/search",
        SERVER_URL_THIRD to "https://pakdb.xyz/sim-ownership/",
        SERVER_URL_FOURTH to "https://pakdb.xyz/sim-ownership/",
        SERVER_URL_FIFTH to "https://pakdb.xyz/sim-ownership/"
    )
    private val config = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        })
        setDefaultsAsync(default)
        fetchAndActivate().addOnCompleteListener {
            Log.d("RemoteConfig", "fetchAndActivate: ${all.mapValues { (k, v) -> v.asString() }}")
        }
    }

    val getFirstUrl: String
        get() = config[SERVER_URL_FIRST].asString()

    val getSecondUrl: String
        get() = config[SERVER_URL_SECOND].asString()

    val getThirdUrl: String
        get() = config[SERVER_URL_THIRD].asString()

    val getFourthUrl: String
        get() = config[SERVER_URL_FOURTH].asString()

    val getFifthUrl: String
        get() = config[SERVER_URL_FIFTH].asString()

    val initAds: Boolean
        get() = config[INIT_ADS].asBoolean()

    val showAdOnAppOpen: Boolean
        get() = config[SHOW_AD_ON_APP_OPEN].asBoolean()
}
