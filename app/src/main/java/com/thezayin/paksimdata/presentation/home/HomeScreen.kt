package com.thezayin.paksimdata.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.paksimdata.presentation.activities.MainActivity
import com.thezayin.paksimdata.presentation.activities.MainViewModel
import com.thezayin.paksimdata.presentation.activities.dialogs.LoadingDialog
import com.thezayin.paksimdata.presentation.activities.dialogs.NetworkDialog
import com.thezayin.paksimdata.presentation.destinations.ResultScreenDestination
import com.thezayin.paksimdata.presentation.home.component.HomeBottomBar
import com.thezayin.paksimdata.presentation.home.component.SearchBox
import com.thezayin.paksimdata.presentation.home.component.TopBarComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import timber.log.Timber

@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel: HomeViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()
    val scope = rememberCoroutineScope()
    val nativeAd = remember { mainViewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading.value
    val activity = LocalContext.current as MainActivity

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    val simDetailsModel = viewModel.simDetails.collectAsState().value.data.value
    val isResultSuccessful = viewModel.resultSuccess.collectAsState().value.isSuccessful.value
    LaunchedEffect(isResultSuccessful) {
        scope.launch(Dispatchers.Main) {
            if (isResultSuccessful) {
                navigator.navigate(ResultScreenDestination(simDetailsModel))
            }
        }
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
            TopBarComponent(modifier = Modifier, navigator = navigator)
        },

        bottomBar = {
            HomeBottomBar(
                modifier = Modifier.navigationBarsPadding(),
                navigator = navigator,
                nativeAd = nativeAd.value
            )
        }
    ) { padding ->
        SearchBox(
            modifier = Modifier.padding(padding),
            viewModel = viewModel,
        )
    }

    BackHandler {
        activity.finish()
    }
}