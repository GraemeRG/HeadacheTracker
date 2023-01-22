package com.sbnl.headachetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sbnl.headachetracker.features.headachequestionnaire.composables.HeadacheQuestionnaire
import com.sbnl.headachetracker.features.homescreen.composables.HeadacheTrackerHomeScreen
import com.sbnl.headachetracker.features.medicationquestionnaire.composables.MedicationQuestionnaire
import com.sbnl.headachetracker.ui.theme.HeadacheTrackerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeadacheTrackerTheme {
                BaseAppNavigation()
            }
        }
    }
}

// This is a version without the bottom sheet madness.
@Composable
fun BaseAppNavigation() {
    Scaffold {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = homeScreen) {
            composable(homeScreen) {
                HeadacheTrackerHomeScreen(
                    onLaunchToHeadacheQuestionnaire = { navController.navigate(headacheQuestionnaire) },
                    onLaunchToMedicationTakenQuestionnaire = { navController.navigate(medicationQuestionnaire) }
                )
            }

            composable(headacheQuestionnaire) {
                HeadacheQuestionnaire({
                    navController.navigateUp()
                })
            }

            composable(medicationQuestionnaire) {
                MedicationQuestionnaire({
                    navController.navigateUp()
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HeadacheTrackerTheme {
        BaseAppNavigation()
    }
}