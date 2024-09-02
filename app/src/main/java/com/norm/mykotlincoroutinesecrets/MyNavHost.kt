package com.norm.mykotlincoroutinesecrets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object FirstScreen

@Serializable
object SecondScreen

@Composable
fun MyNavHost(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FirstScreen,
    ) {
        composable<FirstScreen> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = {
                    navController.navigate(SecondScreen) {
                        popUpToRoute
                    }
                }
                ) {
                    Text(
                        text = "Open Second Screen"
                    )
                }
            }
        }

        composable<SecondScreen> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val polingViewModel = viewModel<PolingViewModel>()
                Text(
                    text = "Requesting..."
                )
            }
        }
    }
}