package com.example.little_lemon_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.little_lemon_project.ui.theme.Little_Lemon_ProjectTheme

@Composable
fun Onboarding() {
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

        PersonalInfo()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfo() {
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
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        ) {
        Text(
            text = "Register")
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Little_Lemon_ProjectTheme {
        Onboarding()
    }
}