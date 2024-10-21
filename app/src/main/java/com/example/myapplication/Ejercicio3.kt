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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class Ejercicio3 {

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
            floatingActionButton = {
                if(currentRoute.value == "home"){
                    FloatingActionButton(
                        onClick = {
                            navController.navigate("cliente")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AddCircle,
                            contentDescription = "Add"
                        )
                    }
                }
            },
            modifier = Modifier
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
            composable("cliente") {
                Cliente(navController)
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
            Text(text = "Hola mundo!", fontSize = 24.sp)
        }
    }

    @Composable
    fun Cliente(navController: NavHostController) {
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
    fun Ejercicio3Preview() {
        Layout()
    }
}