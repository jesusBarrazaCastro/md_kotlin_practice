package com.example.myapplication
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


class oct15 {

    @Composable
    fun imagePractice(){
        var image = painterResource(R.drawable.mando)
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                painter = image,
                "The mandalorian",
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        imagePractice()
    }
}