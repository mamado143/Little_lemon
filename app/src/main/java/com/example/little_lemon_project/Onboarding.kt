package com.example.little_lemon_project

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

val fonts = FontFamily(
    Font(R.font.karla_regular),
    Font(R.font.markazi_text_regular)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavController, sharedPreferences: SharedPreferences){

    Column(
    ){
        var firstName = remember {
            mutableStateOf("")
        }
        var lastName = remember {
            mutableStateOf("")
        }
        var email = remember {
            mutableStateOf("")
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon logo",
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f)
        )
        Text(
            text = "Let's get to know you",
            Modifier
                .background(color = Color(0xFF495E57))
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(horizontal = 8.dp, vertical = 22.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            color = Color(0xFFEDEFEE),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Personal information",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge,
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
                value = firstName.value,
                onValueChange = {newText: String -> firstName.value=newText},
                label = {
                    Text(text = "First name", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
            OutlinedTextField(
                value = lastName.value,
                onValueChange = {newText: String -> lastName.value=newText},
                label = {
                    Text(text = "Last name", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = {newText: String -> email.value=newText},
                label = {
                    Text(text = "e-Mail", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )

        }

        Button(
            onClick = {
                if(firstName.value.isBlank()||lastName.value.isBlank()||email.value.isBlank()){

                }else{
                    sharedPreferences.edit()
                        .putString("firstName", firstName.value)
                        .putString("lastName", lastName.value)
                        .putString("email", email.value)
                        .putBoolean("loggedIn", true)
                        .commit()
                    navController.navigate(Home.route)
                }
            },
            colors = ButtonDefaults.buttonColors( Color(0xFFF4CE14)),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Register", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        }

        if(firstName.value.isBlank()||lastName.value.isBlank()||email.value.isBlank()){
            Text(
                text = "Please fill all the details!",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Light,
                color = Color.Red,
                modifier = Modifier.padding(10.dp)
            )
        }

    }
}
