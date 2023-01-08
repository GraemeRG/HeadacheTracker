package com.sbnl.headachetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sbnl.headachetracker.MainScreenState.*
import com.sbnl.headachetracker.ui.theme.HeadacheTrackerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeadacheTrackerTheme {
                // A surface container using the 'background' color from the theme
                AppMainScreenVersionOne()
            }
        }
    }
}

// This is a version without the bottom sheet madness.
@Composable
fun AppMainScreenVersionOne() {
    Scaffold {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = homeScreen) {
            composable(homeScreen) {
                HeadacheTrackerHomeScreen {
                    navController.navigate(headacheQuestionnaire)
                }
            }
            composable(headacheQuestionnaire) {
                HeadacheQuestionnaire()
            }
        }
    }
}

@Composable
fun HeadacheTrackerHomeScreen(onLaunchToHeadacheQuestionnaire: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        RecordAHeadacheButton {
            onLaunchToHeadacheQuestionnaire()
        }
    }
}

@Composable
fun RecordAHeadacheButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(32.dp),
        onClick = { onClick() }
    ) {
        Text(text = "Record a headache")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HeadacheTrackerTheme {
        AppMainScreenVersionOne()
    }
}

interface AppNavigator {
    fun launchHeadacheQuestionnaire()
}

class AppNavigatorImpl: AppNavigator {
    override fun launchHeadacheQuestionnaire() {
        TODO("Not yet implemented")
    }

}