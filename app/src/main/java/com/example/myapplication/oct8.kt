package com.example.myapplication

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

public class MainRoutes {

}

class oct8 {
    @Composable
    fun NavigationDrawer(

    ) {
        var drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Blue)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Blue),
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        NavigationDrawerItem(
                            label = { Text(text = "Menu 1")},
                            icon = { Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)},
                            selected = false,
                            onClick = {

                            }
                        )
                    }

                }
            },
        ) {

        }
    }
    @Composable
    fun Example0810(name: String, modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = modifier
                .padding(20.dp)
        ) {

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyApplicationTheme {
            NavigationDrawer()
        }

    }
}