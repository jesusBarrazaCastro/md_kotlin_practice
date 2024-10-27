package com.example.myapplication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.InternalCoroutinesApi
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension


class Ejercicio2 {

    @OptIn(InternalCoroutinesApi::class)
    @Composable
    fun CheckBoxWithLabel(
        label: String,
        state: MutableState<Boolean>,
        modifier: Modifier = Modifier
    ) {
        ConstraintLayout(
            modifier = modifier.clickable {
                state.value = !state.value
            }
        ) {
            val (checkbox, text) = createRefs()

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (state.value) Color.Blue else Color.LightGray,
                        shape = CircleShape
                    )
                    .constrainAs(checkbox) {
                        top.linkTo(parent.top)
                    }
            ) {
                Checkbox(
                    checked = state.value,
                    onCheckedChange = {
                        state.value = it
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }

            Text(
                text = label,
                modifier = Modifier
                    .constrainAs(text) {
                        start.linkTo(checkbox.end, margin = 8.dp)
                        top.linkTo(checkbox.top)
                        bottom.linkTo(checkbox.bottom)
                    }
                    .fillMaxWidth()
            )
        }
    }

    @Composable
    fun Layout(modifier: Modifier = Modifier) {
        val rojo = remember { mutableStateOf(true) }
        val verde = remember { mutableStateOf(true) }
        val azul = remember { mutableStateOf(true) }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val (cbRojo, cbVerde, cbAzul, boxRojo, boxVerde, boxAzul) = createRefs()

            CheckBoxWithLabel(
                label = "Azul",
                state = azul,
                modifier = Modifier.constrainAs(cbAzul) {
                    bottom.linkTo(boxAzul.top, margin = 5.dp)
                    start.linkTo(parent.start)
                }
            )
            CheckBoxWithLabel(
                label = "Verde",
                state = verde,
                modifier = Modifier.constrainAs(cbVerde) {
                    bottom.linkTo(boxVerde.top, margin = 5.dp)
                    start.linkTo(boxVerde.start)
                }
            )
            CheckBoxWithLabel(
                label = "Rojo",
                state = rojo,
                modifier = Modifier.constrainAs(cbRojo) {
                    top.linkTo(cbVerde.top,)
                    start.linkTo(boxRojo.start,)
                }
            )

            if (rojo.value) {
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .constrainAs(boxRojo) {
                            top.linkTo(parent.top,)
                            start.linkTo(boxVerde.end, margin = 5.dp)
                            width = Dimension.value(120.dp)
                            height = Dimension.value(120.dp)
                        }
                )
            }
            Box(
                modifier = Modifier
                    .background(if(azul.value) Color.Blue else Color.White)
                    .constrainAs(boxAzul) {
                        top.linkTo(boxRojo.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        width = Dimension.value(120.dp)
                        height = Dimension.value(120.dp)
                    }
            )
            Box(
                modifier = Modifier
                    .background(if(verde.value) Color.Green else Color.White)
                    .constrainAs(boxVerde) {
                        top.linkTo(boxAzul.bottom, margin = 5.dp)
                        start.linkTo(boxAzul.end, margin = 5.dp)
                        width = Dimension.value(120.dp)
                        height = Dimension.value(120.dp)
                    }
            )

            

            
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Layout()
    }
}