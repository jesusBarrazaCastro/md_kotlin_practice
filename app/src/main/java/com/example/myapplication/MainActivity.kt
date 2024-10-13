package com.example.myapplication

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

val decimalFormat = DecimalFormat("#,##0.00")
val style = TextStyle(
    textAlign = TextAlign.End,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold
)
val style2 = TextStyle(
    textAlign = TextAlign.End,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("")}
    var iva: MutableState<Double> = remember { mutableStateOf(0.00)}
    var subtotal: MutableState<Double> = remember { mutableStateOf(0.00)}
    var ivaRetencion: MutableState<Double> = remember { mutableStateOf(0.00)}
    var isrRetencion: MutableState<Double> = remember { mutableStateOf(0.00)}
    var total: MutableState<Double> = remember { mutableStateOf(0.00)}
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
            .padding(20.dp)
    ) {
        Text(
            text = "Impuestos",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = modifier
        )
        TextField(
            value = text,
            label = { Text("Importe") },
            textStyle = TextStyle(
                textAlign = TextAlign.End,
                fontSize = 16.sp
            ),
            onValueChange = {newValue ->
                val regex = Regex("^\\d*\\.?\\d{0,2}$")
                if (newValue.isEmpty() || regex.matches(newValue)) {
                    text = newValue//"$${decimalFormat.format(newValue)}"
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = modifier
                .fillMaxWidth()
        )
        Row {
            Text(text = "IVA (16%) =", style = style)
            Spacer(modifier = modifier.weight(1f))
            Text(text = "$${decimalFormat.format(iva.value)}", style = style)
        }
        Button(
            onClick = {
                val importe = text.toDoubleOrNull() ?: 0.0

                val calculatedIva = importe * 0.16
                val calculatedSubtotal = importe + calculatedIva
                val calculatedIvaRetencion = importe * 0.1066
                val calculatedIsrRetencion = importe * 0.10
                val calculatedTotal = calculatedSubtotal - (calculatedIvaRetencion + calculatedIsrRetencion)

                iva.value = calculatedIva
                subtotal.value = calculatedSubtotal
                ivaRetencion.value = calculatedIvaRetencion
                isrRetencion.value = calculatedIsrRetencion
                total.value = calculatedTotal
                keyboardController?.hide()
            },
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(text = "Calcular")
        }
        Row {
            Text(text = "Subtotal =", style = style)
            Spacer(modifier = modifier.weight(1f))
            Text(text = "$${decimalFormat.format(subtotal.value)}", style = style)
        }
        Row {
            Text(text = "Retención IVA (10.66%) =", style = style)
            Spacer(modifier = modifier.weight(1f))
            Text(text = "$${decimalFormat.format(ivaRetencion.value)}", style = style)
        }
        Row {
            Text(text = "Retención ISR (10%) =", style = style)
            Spacer(modifier = modifier.weight(1f))
            Text(text = "$${decimalFormat.format(isrRetencion.value)}", style = style)
        }
        Row {
            Text(text = "Total =", style = style2)
            Spacer(modifier = modifier.weight(1f))
            Text(text = "$${decimalFormat.format(total.value)}", style = style2)
        }
        Spacer(modifier = modifier.weight(1f))
        Row {
            Spacer(modifier = modifier.weight(1f))
            Button(
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                onClick = {
                    iva.value = 0.00
                    subtotal.value = 0.00
                    ivaRetencion.value = 0.00
                    isrRetencion.value = 0.00
                    total.value = 0.00
                    text = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Limpiar",
                    modifier = modifier
                        .size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}