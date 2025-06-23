package com.example.podcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.podcastapp.presentation.details.DetailsScreen
import com.example.podcastapp.presentation.home.HomeScreen
import com.example.podcastapp.ui.theme.PodcastAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PodcastAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(
                                onItemClick = { podcastId ->
                                    navController.navigate("details/$podcastId")
                                }
                            )
                        }
                        composable(
                            route = "details/{podcastId}",
                            arguments = listOf(navArgument("podcastId") {
                                type = NavType.StringType
                            })
                        ) {
                            DetailsScreen(onBackPress = navController::navigateUp)
                        }
                    }
                }
            }
        }
    }
}