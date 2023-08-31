package com.example.little_lemon_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon_project.ui.theme.Little_Lemon_ProjectTheme
import androidx.compose.material3.TextField as TextField1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Little_Lemon_ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    MyNavigation(navController = navController)
    var searchPhrase by remember { mutableStateOf(TextFieldValue()) }
    var menuItems = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Another Item") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        TextField1(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            placeholder = { Text("Enter Search Phrase") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        val filteredItems = if (searchPhrase.text.isNotBlank()) {
            menuItems.filter { it.contains(searchPhrase.text, ignoreCase = true) }
        } else {
            menuItems
        }

        MenuItems(items = filteredItems)
    }
}

@Composable
fun MenuItems(items: List<String>) {
    Column {
        items.forEach { item ->
            Text(text = item, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        }
    }
}