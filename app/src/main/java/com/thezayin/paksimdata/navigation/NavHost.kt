package com.thezayin.paksimdata.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.thezayin.home.HomeScreen
import com.thezayin.premium.PremiumScreen
import com.thezayin.presentation.ResultScreen
import com.thezayin.presentation.ServerScreen
import com.thezayin.presentation.SplashScreen
import com.thezayin.setting.SettingScreen
import com.thezayin.web.WebScreen

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = SplashScreenNav
    ) {
        composable<SplashScreenNav> {
            SplashScreen(
                onNavigate = {
                    navController.navigate(HomeScreenNav)
                }
            )
        }

        composable<HomeScreenNav> {
            HomeScreen(
                onSearchClick = {
                    navController.navigate(ResultScreenNav(it))
                },
                onMenuClick = {
                    navController.navigate(SettingScreenNav)
                },
                onPremiumClick = {
                    navController.navigate(PremiumScreenNav)
                },
                onServerClick = {
                    navController.navigate(ServerScreenNav)
                }
            )
        }

        composable<ResultScreenNav> {
            val args = it.toRoute<ResultScreenNav>()
            ResultScreen(
                phoneNumber = args.number,
                onBackPress = {
                    navController.navigateUp()
                },
                onPremiumClick = {
                    navController.navigate(PremiumScreenNav)
                }
            )
        }

        composable<ServerScreenNav> {
            ServerScreen(
                onServerClick = {
                    navController.navigate(WebScreenNav(it))
                },
                onBackPress = { navController.navigateUp() },
                onPremiumClick = {
                    navController.navigate(PremiumScreenNav)
                }
            )
        }

        composable<WebScreenNav> {
            val args = it.toRoute<WebScreenNav>()
            WebScreen(
                url = args.url,
                onBackPress = { navController.navigateUp() },
                onPremiumClick = {
                    navController.navigate(PremiumScreenNav)
                }
            )
        }

        composable<SettingScreenNav> {
            SettingScreen(
                onBackClick = { navController.navigateUp() },
                onPremiumClick = {
                    navController.navigate(PremiumScreenNav)
                }
            )
        }

        composable<PremiumScreenNav> {
            PremiumScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}