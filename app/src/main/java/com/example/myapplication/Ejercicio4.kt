package com.example.myapplication
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import coil3.compose.AsyncImage
import com.example.myapplication.ui.theme.MyApplicationTheme


class Ejercicio4 {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Layout(modifier: Modifier = Modifier){
        val navController = rememberNavController()
        val currentRoute = remember { mutableStateOf("home") }


        LaunchedEffect(navController) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                currentRoute.value = destination.route ?: "home"
            }
        }
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Taquería")
                    }
                )
            }
        ) {
            NavigationComponent(navController = navController)
        }
    }

    @Composable
    fun NavigationComponent(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Home()
            }
            composable("order") {
                Order(navController)
            }
        }
    }

    @Composable
    fun orderItem(data: MutableMap<String, Any?>){
        var cantidad by remember { mutableStateOf("") }
        cantidad = (data["cantidad"] as? Int ?: 0).toString()
        Row (
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.RemoveCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
            TextField(
                value = cantidad,
                onValueChange = { input: String ->
                    if (input.all { it.isDigit() }) {
                        cantidad = input
                        data["cantidad"] = input.toInt()
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                placeholder = null,
                modifier = Modifier
                    .width(60.dp)
            )
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.AddCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }

    @Composable
    fun Home() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column {
                orderItem(data = mutableMapOf("cantidad" to 1, "nombre" to "Taco de maíz"))
            }
        }
    }

    @Composable
    fun Order(navController: NavHostController) {
        var nombre by remember { mutableStateOf("") }
        var apellido by remember { mutableStateOf("") }
        var color by remember { mutableStateOf("") }
        var credito by remember { mutableStateOf("") }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                Text(
                    text = "Alta cliente",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                TextField(
                    value = nombre,
                    label = {Text("Nombre")},
                    onValueChange = {
                        nombre = it
                    }
                )
                TextField(
                    value = apellido,
                    label = {Text("Apellido")},
                    onValueChange = {
                        apellido = it
                    }
                )
                TextField(
                    value = color,
                    label = {Text("Color favorito")},
                    onValueChange = {
                        color = it
                    }
                )
                TextField(
                    value = credito,
                    label = {Text("Color favorito")},
                    onValueChange = {
                        credito = it
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    modifier = Modifier
                ) {
                    Button(
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text(text = "Cancelar")
                    }
                    Button(
                        modifier = Modifier,
                        onClick = {

                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Layout()
    }
}