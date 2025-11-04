package com.personal.cmptests.composetests


import androidx.compose.runtime.Composable
import com.personal.cmptests.composetests.di.appModule
import com.personal.cmptests.composetests.features.MainTabsScreen.SplashScreen
import com.personal.cmptests.composetests.features.SplashScreen.MainTabsScreen
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        PreComposeApp {
            val navigator = rememberNavigator()

            NavHost(navigator = navigator, initialRoute = "/splash") {
                scene("/splash") {
                    SplashScreen(
                        onFinished = {
                            navigator.popBackStack()
                            navigator.navigate("/main", NavOptions())
                        }
                    )
                }
                scene("/main") {
                    MainTabsScreen()
                }
            }
        }
    }
}