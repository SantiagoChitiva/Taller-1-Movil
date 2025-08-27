package com.example.taller1.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.taller1.data.remote.model.User

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    users: List<User>,
    onUserClick: (User) -> Unit
) {
    LazyColumn {
        stickyHeader {
            Text(
                text = "Total usuarios: ${users.size}",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }

        items(users) { user ->
            ListItem(
                headlineContent = { Text("${user.firstName} ${user.lastName}") },
                supportingContent = { Text(user.company.name) },
                leadingContent = {
                    AsyncImage(
                        model = user.image,
                        contentDescription = null
                    )
                },
                trailingContent = {
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                modifier = Modifier.clickable { onUserClick(user) }
            )
            Divider()
        }
    }
}

