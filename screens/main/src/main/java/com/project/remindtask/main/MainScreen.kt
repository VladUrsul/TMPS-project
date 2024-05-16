package com.project.remindtask.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.project.remindtask.main.component.MainBottomBar
import com.project.remindtask.navigation.Screens
import com.project.remindtask.notes.notesScreen
import com.project.remindtask.notes.toAddNote
import com.project.remindtask.tasks.tasksScreen
import com.project.remindtask.tasks.toAddTask

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val localNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            MainBottomBar(
                navController = localNavController,
                onAddClick = {
                    val currentRoute = localNavController.currentBackStackEntry?.destination?.route
                    if (currentRoute == Screens.Tasks.route) {
                        navController.toAddTask()
                    } else {
                        navController.toAddNote()
                    }
                }
            )
        }
    ) {
        Box(modifier = modifier.padding(it)) {
            NavHost(navController = localNavController, startDestination = Screens.Tasks.route) {
                tasksScreen(
                    navigateToEditTask = { task -> navController.toAddTask(task) }
                )
                notesScreen(
                    navigateToEditNote = { note -> navController.toAddNote(note) }
                )
            }
        }
    }
}