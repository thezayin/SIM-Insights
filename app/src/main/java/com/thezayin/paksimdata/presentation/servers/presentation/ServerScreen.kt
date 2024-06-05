package com.thezayin.paksimdata.presentation.servers.presentation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.paksimdata.presentation.activities.MainViewModel
import com.thezayin.paksimdata.presentation.activities.dialogs.LoadingDialog
import com.thezayin.paksimdata.presentation.activities.dialogs.NetworkDialog
import com.thezayin.paksimdata.presentation.servers.presentation.components.ServerList
import com.thezayin.paksimdata.presentation.servers.presentation.components.ServerTopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import timber.log.Timber

@Destination
@Composable
fun ServerScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: ServerViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading.value
    val serverList = viewModel.serverList.collectAsState().value.list.value
    val mainViewModel: MainViewModel = koinInject()
    val scope = rememberCoroutineScope()
    val nativeAd = remember { mainViewModel.nativeAd }

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog(
            viewModel = mainViewModel,
            showAd = mainViewModel.remoteConfig.showAdOnServerScreenLoadingDialog
        )
    }

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


    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = ConstantColor.NeumorphismLightBackgroundColor,
        topBar = {
            ServerTopBar(navigator = navigator, modifier = Modifier, mainViewModel = mainViewModel)
        },
        bottomBar = {
            if (mainViewModel.remoteConfig.showAdOnServerScreenNative) {
                GoogleNativeAd(
                    nativeAd = nativeAd.value,
                    style = GoogleNativeAdStyle.Small,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    ) { padding ->
        ServerList(
            navigator = navigator,
            modifier = Modifier.padding(padding),
            serverList = serverList,
            mainViewModel = mainViewModel
        )
    }
}