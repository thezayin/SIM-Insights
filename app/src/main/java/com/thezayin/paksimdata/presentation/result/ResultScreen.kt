package com.thezayin.paksimdata.presentation.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.paksimdata.domain.model.SimDataModel
import com.thezayin.paksimdata.presentation.activities.MainViewModel
import com.thezayin.paksimdata.presentation.result.component.ResultDetails
import com.thezayin.paksimdata.presentation.result.component.ResultTopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import timber.log.Timber

@Destination
@Composable
fun ResultScreen(
    navigator: DestinationsNavigator,
    simDetails: SimDataModel
) {
    val mainViewModel: MainViewModel = koinInject()
    val scope = rememberCoroutineScope()
    val nativeAd = remember { mainViewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        mainViewModel.getNativeAd()
                        Timber.d("Native_Ad", "getNativeAd:${nativeAd} ")
                        delay(20000L)
                    }
                }
            }

            else -> {
                Timber.tag("Native_Ad").d("Not Called")
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 25.dp)
        ) {
            ResultTopBar(mainViewModel = mainViewModel, modifier = Modifier, navigator = navigator)
            Text(
                text = "Result",
                modifier = Modifier.padding(top = 34.dp, bottom = 24.dp),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_italic)),
            )
            ResultDetails(modifier = Modifier, simDetails = simDetails)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (mainViewModel.remoteConfig.showAdOnResultScreenNative) {
                GoogleNativeAd(
                    nativeAd = nativeAd.value,
                    style = GoogleNativeAdStyle.Small,
                )
            }
        }
    }
}