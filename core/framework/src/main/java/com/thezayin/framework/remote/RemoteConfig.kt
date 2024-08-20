package com.thezayin.framework.remote

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.json.Json


private const val INIT_ADS = "init_ads"
private const val SERVER_LIST = "server_list"
private const val AD_CONFIGS = "ad_configs"

class RemoteConfig(
    private val json: Json
) {
    private val default: Map<String, Any> = mapOf(
        INIT_ADS to true,
        SERVER_LIST to defaultServerUrl,
        AD_CONFIGS to defaultAdConfigs
    )

    @SuppressLint("LogNotTimber")
    private val config = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        })
        setDefaultsAsync(default)
        fetchAndActivate().addOnCompleteListener {
            Log.d("RemoteConfig", "fetchAndActivate: ${all.mapValues { (_, v) -> v.asString() }}")
        }
    }

    val initAds: Boolean
        get() = config.getBoolean(INIT_ADS)

    val serverList: ServerUrl
        get() = json.decodeFromString(ServerUrl.serializer(), config.getString(SERVER_LIST))

    val adConfigs: AdConfigs
        get() = try {
            json.decodeFromString(AdConfigs.serializer(), config.getString(AD_CONFIGS))
        } catch (e: MissingFieldException) {
            // Provide a fallback AdConfigs instance with default values
            AdConfigs()
        }
}




