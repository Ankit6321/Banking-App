package com.example.banking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.banking.ui.component.peopleList
import com.example.banking.ui.screens.HomeScreen
import com.example.banking.ui.screens.TransactionDetailsScreen
import com.example.banking.ui.theme.BankingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankingTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            HomeScreen(onPersonClick = { personName ->
                navController.navigate("details/$personName")
            })
        }
        composable(
            route = "details/{personName}",
            arguments = listOf(navArgument("personName") { type = NavType.StringType })
        ) { backStackEntry ->
            val personName = backStackEntry.arguments?.getString("personName")
            val person = peopleList.find { it.name == personName }
            if (person != null) {
                TransactionDetailsScreen(
                    person = person,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
