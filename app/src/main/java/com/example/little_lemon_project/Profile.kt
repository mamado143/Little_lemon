package com.example.little_lemon_project

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController, sharedPreferences: SharedPreferences) {
    Column(
    ) {
        var firstName = sharedPreferences.getString("firstName", "FIRST NAME")!!
        var lastName = sharedPreferences.getString("lastName", "LAST NAME")!!
        var email = sharedPreferences.getString("email", "EMAIL")!!
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon logo",
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f)
        )
        Text(
            text = "Personal information",
            fontSize = 20.sp,
            style = MaterialTheme.typography.displayLarge,
            color = Color(0xFF333333),
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 20.dp)
        )

        Column(
            Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = {},//newText: String -> firstName=newText
                label = {
                    Text(text = "First name", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = {},//newText: String -> lastName=newText
                label = {
                    Text(text = "Last name", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = {},//newText: String -> email=newText
                label = {
                    Text(text = "e-Mail", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

        }

        Button(
            onClick = {
                sharedPreferences.edit().putBoolean("loggedIn", false).commit()
                navController.navigate(Onboarding.route)
            },
            colors = ButtonDefaults.buttonColors(
                Color(0xFFF4CE14)
            ),

            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Log Out",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }


    }
}