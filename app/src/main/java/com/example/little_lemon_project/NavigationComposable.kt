// In MyNavigation.kt
package com.example.little_lemon_project

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Onboarding.route) {
        composable(Onboarding.route) {
            Onboarding(navController = navController, context = LocalContext.current)
        }
        composable(Home.route) {
            Home(navController = navController, context = LocalContext.current)
        }
    }
}
