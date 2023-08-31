package com.example.little_lemon_project

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.little_lemon_project.Destinations.Companion.Home

@Composable
fun Onboarding(navController: NavHostController, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Let's get to know you",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),

            )

        PersonalInfo(navController = navController, context = context)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfo(navController: NavHostController, context: Context) {

    var FirstName = remember {
        mutableStateOf(TextFieldValue(""))
    }
    var LastName = remember {
        mutableStateOf(TextFieldValue(""))
    }
    var Email = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = FirstName.value,
            onValueChange = { FirstName.value = it },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = LastName.value,
            onValueChange = { LastName.value = it },
            label = { Text(text = "Last name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = Email.value,
            onValueChange = { Email.value = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

    }
    Button(
        onClick = {
            val firstName = FirstName.value
            val lastName = LastName.value
            val email = Email.value

            if (isInputValid(firstName, lastName, email)) {
                // Save user data to SharedPreferences
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("first_name", firstName.toString())
                editor.putString("last_name", lastName.toString())
                editor.putString("email", email.toString())

                editor.apply()
                // Print a success message
                println("Registration successful!")

                // Navigate to Home screen
                navController.navigate(Home)
            } else {
                // Show validation error message
                println("Registration unsuccessful. Please enter all data.")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "Register")
    }
}

private fun isInputValid(
    firstName: TextFieldValue,
    lastName: TextFieldValue,
    email: TextFieldValue
): Boolean {
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
}

private fun TextFieldValue.isNotBlank(): Boolean {
    return text.isNotBlank()

}
