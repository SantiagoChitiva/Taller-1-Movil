package com.example.taller1.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.taller1.data.remote.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    user: User,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Detalle de Usuario") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = user.image,
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Empresa: ${user.company.name}")
            Text(
                text = "Teléfono: ${user.phone}",
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${user.phone}")
                    }
                    context.startActivity(intent)
                },
                color = Color.Blue
            )
            Text("Email: ${user.email}")
            Text("Edad: ${user.age}")
            Text("Género: ${user.gender}")
            Text("Altura: ${user.height}")
            Text("Peso: ${user.weight}")
            Text("Universidad: ${user.university}")
        }
    }
}