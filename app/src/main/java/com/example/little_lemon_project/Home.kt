package com.example.little_lemon_project
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    sharedPreferences: SharedPreferences,
    menuItems: List<MenuItemRoom>
) {
    //temporary button
    Column(Modifier.fillMaxWidth()) {
        var searchPhrase = remember {
            mutableStateOf("")
        }
        var items = menuItems
        // Add OutlinedTextField

        var categoryFilter = remember {
            mutableStateOf("")
        }

        if (categoryFilter.value.isNotEmpty()) {
            items = items.filter { it.category!!.contains(categoryFilter.value, ignoreCase = true) }
        }
        if (searchPhrase.value.isNotEmpty()) {
            items = items.filter { it.title!!.contains(searchPhrase.value, ignoreCase = true) }
        }

        @Composable
        fun MenuCategory(category: String) {
            Button(
                onClick = {
                    categoryFilter.value = category
                },
                colors = ButtonDefaults.buttonColors(LightGray),
                shape = RoundedCornerShape(40),
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = category
                )
            }
        }
        //UpperPanel(navController = navController)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = com.example.little_lemon_project.R.drawable.back_arrow_green),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() },
            )
            Image(
                painter = painterResource(id = com.example.little_lemon_project.R.drawable.little_lemon_big),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .padding(horizontal = 20.dp)
                    .size(24.dp)
            )
            IconButton(onClick = {
                navController.navigate(Profile.route)
            }) {
                Image(
                    painter = painterResource(id = com.example.little_lemon_project.R.drawable.profile),
                    contentDescription = "profile",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .background(Color(0xFF495E57))//LittleLemonColor.green
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
        ) {

            Text(
                text = "Little Lemon",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4CE14)
            )
            Text(
                text = "Chicago",
                fontSize = 24.sp,
                color = Color(0xFFEDEFEE)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = stringResource(id = com.example.little_lemon_project.R.string.description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFFEDEFEE),
                    modifier = Modifier
                        .padding(bottom = 28.dp, end = 20.dp)
                        .fillMaxWidth(0.6f)
                )
                Image(
                    painter = painterResource(id =  com.example.little_lemon_project.R.drawable.upperpanelimage),
                    contentDescription = "Upper Panel Image",
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )
            }
            OutlinedTextField(
                value = searchPhrase.value,
                onValueChange = { searchPhrase.value = it },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                label = { Text("Search", color = Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyRow {
            items(Categories) { category ->
                MenuCategory(category)
            }
        }
        Divider(
            modifier = Modifier.padding(8.dp),
            color = Color.Gray,
            thickness = 1.dp
        )
        LazyColumn {
            items(items) { items ->
                MenuDish(items)
            }
        }

    }

}

@Composable
fun MenuCategory(category: String) {
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(LightGray),
        shape = RoundedCornerShape(40),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = category
        )
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish(menuItem: MenuItemRoom) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = menuItem.title!!, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = menuItem.description!!,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = "$" + menuItem.price.toString(),
                    color = Color(0xFF495E57),
                    fontWeight = FontWeight.Bold
                )
            }
            GlideImage(model = menuItem.image, contentDescription = "Item image")
//            Image(
//                painter = painterResource(id = Dish.image),
//                contentDescription = "",
//            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
}

val Categories = listOf(
    "Starters",
    "Mains",
    "Dessert",
    "Drinks"
)
