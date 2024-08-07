package com.thezayin.paksimdata.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.paksimdata.R
import com.thezayin.paksimdata.presentation.activities.MainActivity
import com.thezayin.paksimdata.presentation.activities.MainViewModel
import com.thezayin.paksimdata.presentation.activities.dialogs.interstitialAd
import com.thezayin.paksimdata.presentation.destinations.HomeScreenDestination
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
@RootNavGraph(start = true)
@Destination
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    val mainViewModel: MainViewModel = koinInject()
    val activity = LocalContext.current as MainActivity
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        delay(5000L)
        activity.interstitialAd(
            scope= scope,
            viewModel = mainViewModel,
            showAd = mainViewModel.remoteConfig.showAdOnAppOpen
        ) {
            navigator.navigate(HomeScreenDestination)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_main),
            contentDescription = "logo",
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 25.dp, vertical = 25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "SIM Insights",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular))
            )
        }
    }
}