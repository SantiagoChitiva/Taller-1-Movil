package com.example.taller1.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taller1.data.remote.api.KtorApiClient
import com.example.taller1.data.remote.model.User
import com.example.taller1.ui.screen.MainScreen
import com.example.taller1.ui.screen.UserDetailScreen

object Screen {
    object Main { const val route = "main" }
    object Detail { const val route = "detail" }
}

@Composable
fun NavigationStack() {
    val navController: NavHostController = rememberNavController()
    var users by remember { mutableStateOf<List<User>>(emptyList()) }

    // Cargar usuarios solo una vez
    LaunchedEffect(Unit) {
            users = KtorApiClient.getUsers().users
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        // Pantalla principal con lista de usuarios
        composable(route = Screen.Main.route) {
            MainScreen(users = users) { user ->
                navController.navigate(Screen.Detail.route + "?userId=${user.id}")
            }
        }

        // Pantalla de detalles con argumento userId
        composable(
            route = Screen.Detail.route + "?userId={userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            val user = users.find { it.id == userId }

            if (user != null) {
                UserDetailScreen(user = user)
            }
        }
    }
}
