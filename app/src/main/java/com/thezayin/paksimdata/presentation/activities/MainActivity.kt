package com.thezayin.paksimdata.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.analytics.helpers.LocalAnalyticsHelper
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.framework.utils.Constants
import com.thezayin.paksimdata.presentation.NavGraphs
import com.thezayin.paksimdata.presentation.theme.PakSimDataTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.TOPIC)

        val analyticsHelper = viewModel.analyticsHelper

        if (viewModel.remoteConfig.initAds) {
            viewModel.googleManager.init(this)
            viewModel.googleManager.initOnLastConsent()
        }

        setContent {
            CompositionLocalProvider(
                LocalAnalyticsHelper provides analyticsHelper,
            ) {
                PakSimDataTheme {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        showAppOpenAd(
            activity = this,
            analytics = viewModel.analyticsHelper,
            googleManager = viewModel.googleManager
        )
    }
}
