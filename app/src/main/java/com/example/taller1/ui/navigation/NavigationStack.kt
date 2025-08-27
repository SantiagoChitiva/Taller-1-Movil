package com.example.taller1.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.launch

object Screen {
    object Main { const val route = "main" }
    object Detail { const val route = "detail" }
}

@Composable
fun NavigationStack(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    // Cargar usuarios solo una vez
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                users = KtorApiClient.getUsers()
                isLoading = false
            } catch (e: Exception) {
                error = "Error al cargar usuarios: ${e.message}"
                isLoading = false
            }
        }
    }

    when {
        isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = error!!)
            }
        }
        else -> {
            NavHost(
                navController = navController,
                startDestination = Screen.Main.route,
                modifier = modifier
            ) {
                // Pantalla principal con lista de usuarios
                composable(route = Screen.Main.route) {
                    MainScreen(
                        users = users,
                        onUserClick = { user ->
                            navController.navigate("${Screen.Detail.route}/${user.id}")
                        }
                    )
                }

                // Pantalla de detalles con argumento userId
                composable(
                    route = "${Screen.Detail.route}/{userId}",
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
                        UserDetailScreen(
                            user = user,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Usuario no encontrado")
                        }
                    }
                }
            }
        }
    }
}