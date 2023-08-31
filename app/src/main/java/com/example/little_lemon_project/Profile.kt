package com.example.little_lemon_project

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavHostController, context: Context) {
    val sharedPreferences = context.getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("first_name", "") ?: ""
    val lastName = sharedPreferences.getString("last_name", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Header with logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth()
        )

        // Profile information
        Text(text = "Profile information:")
        Text(text = "First Name: $firstName")
        Text(text = "Last Name: $lastName")
        Text(text = "Email: $email")

        // Logout button
        Button(
            onClick = {
                // Clear shared preferences
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                // Navigate to Onboarding screen
                navController.navigate(Destinations.Onboarding)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Log out")
        }
    }
}

@Composable
@Preview (showBackground = true)
fun ProfilePreview() {
    val mockNavController = rememberNavController() // Import rememberNavController if needed
    val mockContext = LocalContext.current

    Profile(navController = mockNavController, context = mockContext)
}
