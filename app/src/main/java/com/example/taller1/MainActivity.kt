package com.example.taller1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taller1.ui.theme.Taller1Theme
import com.example.taller1.ui.screen.MainScreen
import com.example.taller1.data.remote.api.KtorApiClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Taller1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    MainScreen(KtorApiClient.getUsers(), Modifier.padding(padding))
                }
            }
        }
    }
}

// El Greeting y GreetingPreview pueden permanecer aquí o moverse a otro archivo si lo prefieres
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // ... (código del Greeting)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // ... (código del GreetingPreview)
}
    