package com.project.remindtask

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.remindtask.main.MainScreen
import com.project.remindtask.navigation.Screens
import com.project.remindtask.notes.addNoteScreen
import com.project.remindtask.tasks.addTaskScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screens.Main.route) {
        composable(Screens.Main.route) { MainScreen(navController = navController) }
        addTaskScreen(
            navController = navController,
            onNavigationBack = { navController.navigateUp() }
        )
        addNoteScreen(
            navController = navController,
            onNavigationBack = { navController.navigateUp() }
        )
    }
}