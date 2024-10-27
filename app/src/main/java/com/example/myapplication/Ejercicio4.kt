package com.example.myapplication
import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.times
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import coil3.compose.AsyncImage
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.time.times


data class Producto(
    var cantidad: MutableState<Int> = mutableIntStateOf(0),
    val nombre: String,
    val precio: Double
)

class Ejercicio4 {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Layout(modifier: Modifier = Modifier){

        val productos = remember {
            mutableStateListOf(
                Producto(nombre = "Taco de maíz", precio = 45.00),
                Producto(nombre = "Quesadilla", precio = 70.00),
                Producto(nombre = "Vampiro", precio = 85.00),
                Producto(nombre = "Agua de Horchata", precio = 35.00),
                Producto(nombre = "Coca", precio = 45.00)
            )
        }

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
            Box(modifier = Modifier.padding(it)) {
                NavigationComponent(productos = productos, navController = navController, modifier = Modifier)
            }
        }
    }

    @Composable
    fun NavigationComponent(productos: List<Producto>,navController: NavHostController, modifier: Modifier) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Home(productos, navController, Modifier)
            }
            composable("order") {
                Order(productos, navController, Modifier)
            }
        }
    }

    @Composable
    fun OrderItem(producto: Producto) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                IconButton(
                    onClick = {
                        if (producto.cantidad.value > 0) {
                            producto.cantidad.value--
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.RemoveCircle,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                TextField(
                    value = producto.cantidad.value.toString(),
                    onValueChange = { input: String ->
                        if (input.all { it.isDigit() }) {
                            producto.cantidad.value = if(input.isEmpty())  0 else input.toInt()
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp)
                )
                IconButton(
                    onClick = {
                        producto.cantidad.value++
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = producto.nombre,
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$${producto.precio}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }

    @Composable
    fun TotalItem(producto: Producto) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Text(
                    text = producto.cantidad.value.toString(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = producto.nombre,
                    style = TextStyle(
                        fontSize = 22.sp,
                        textAlign = TextAlign.Start
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$${producto.precio * producto.cantidad.value}",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }


    @Composable
    fun Home(productos: List<Producto>, navController: NavHostController, modifier: Modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column {
                Text(
                    text = "Orden",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = modifier.weight(1f))
                LazyColumn {
                    items(productos) { item ->
                        OrderItem(producto = item)
                    }
                }
                Spacer(modifier = modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        onClick = {
                            productos.map { item ->
                                item.cantidad.value = 0
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = modifier.weight(1f))
                    Button(
                        onClick = {
                            navController.navigate("order")
                        }
                    ) {
                        Text(text = "Calcular total")
                    }
                }
            }
        }
    }

    @Composable
    fun Order(productos: List<Producto>, navController: NavHostController, modifier: Modifier) {
        val propinaSelected = remember { mutableIntStateOf(0) }
        val totalConPropina = remember(productos, propinaSelected.value) {
            productos.sumOf { (it.cantidad.value * it.precio) + ((it.cantidad.value * it.precio) * (propinaSelected.value / 100.0)) }
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total de la orden",
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        ),
                        onClick = {
                            navController.navigate("home")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                        Text(text = "Cancelar")
                    }
                }
                LazyColumn {
                    items(productos) { item ->
                        TotalItem(producto = item)
                    }
                }
                Spacer(modifier = modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                ) {
                    Text(
                        text = "Total: ",
                        style = TextStyle(
                            fontSize = 27.sp,
                            //fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        text = "$${productos.sumOf { it.cantidad.value * it.precio }}",
                        style = TextStyle(
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                ) {
                    Text(
                        text = "Propina: ",
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(propinaSelected.value == 0) Color.DarkGray else Color.LightGray
                        ),
                        onClick = {
                            propinaSelected.value = 0
                        }
                    ) {
                        Text(text = "0 %")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(propinaSelected.value == 10) Color.DarkGray else Color.LightGray
                        ),
                        onClick = {
                            propinaSelected.value = 10
                        }
                    ) {
                        Text(text = "10 %")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(propinaSelected.value == 15) Color.DarkGray else Color.LightGray
                        ),
                        onClick = {
                            propinaSelected.value = 15
                        }
                    ) {
                        Text(text = "15 %")
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                ) {
                    Text(
                        text = "Total con propina: ",
                        style = TextStyle(
                            fontSize = 27.sp,
                            //fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        text = "$${totalConPropina}",
                        style = TextStyle(
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
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