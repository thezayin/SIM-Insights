package com.thezayin.paksimdata.activity

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.analytics.Analytics
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.framework.remote.RemoteConfig
import com.thezayin.framework.utils.Constants
import com.thezayin.paksimdata.navigation.NavHost
import com.thezayin.paksimdata.theme.PakSimDataTheme
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private val googleManager: GoogleManager by inject()
    private val remoteConfig: RemoteConfig by inject()
    private val analytics: Analytics by inject()
    val REQUEST_PERMISSION_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.TOPIC)
        googleManager.init(this)
        googleManager.initOnLastConsent()
        setContent {
            PakSimDataTheme {
                val navController = rememberNavController()
                NavHost(navController = navController)
            }
        }
        if (!checkPermissions()) {
            requestPermissions()
        }
    }

    override fun onStart() {
        super.onStart()
        this.showAppOpenAd(
            googleManager = googleManager,
            analytics = analytics,
            showAd = remoteConfig.adConfigs.appOpenAd
        )
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)} passing\n      in a {@link RequestMultiplePermissions} object for the {@link ActivityResultContract} and\n      handling the result in the {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Permission granted", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun checkPermissions(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(READ_EXTERNAL_STORAGE),
            REQUEST_PERMISSION_CODE
        )
    }
}
