package com.example.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.layouts.ui.theme.LayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutsTheme {
                PracticaLayout()
            }
        }
    }
}

@Preview
@Composable
fun PracticaLayout() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    )
    {
        Elemento1()
        Spacer(Modifier.height(30.dp))
        Elemento2()
        Spacer(Modifier.height(30.dp))
        Elemento3()
    }
}

@Composable
private fun Elemento1() {
    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(.33333f)
            .background(Color.Cyan),
        horizontalArrangement = Arrangement.End
    )
    {
        Text("Ejemplo 1")
    }
}

@Composable
private fun Elemento2() {
    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Red),
            verticalArrangement = Arrangement.Bottom
        )
        {
            Text("Ejemplo 2")
        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text("Ejemplo 3")
        }
    }
}

@Composable
private fun Elemento3() {
    Row(
        Modifier.Companion
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Magenta),
        horizontalArrangement = Arrangement.Center
    )
    {
        Text("Ejemplo 4")
    }
}
