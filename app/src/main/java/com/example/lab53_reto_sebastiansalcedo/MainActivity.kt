package com.example.lab53_reto_sebastiansalcedo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ThermostatScreen()
                }
            }
        }
    }
}

@Composable
fun ThermostatScreen() {

    // Estado elevado: el padre controla la temperatura
    var temperature by remember {
        mutableIntStateOf(20)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Termostato Inteligente",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        TemperatureDisplay(
            temperature = temperature
        )

        Spacer(modifier = Modifier.height(32.dp))

        TemperatureControls(
            onDecrease = {
                temperature--
            },
            onIncrease = {
                temperature++
            }
        )
    }
}

@Composable
fun TemperatureDisplay(
    temperature: Int
) {

    val isHot = temperature >= 25

    val statusColor =
        if (isHot) Color.Red
        else Color.Blue

    val icon =
        if (isHot) "☀"
        else "❄"

    val status =
        if (isHot) "CALOR"
        else "FRÍO"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = icon,
            fontSize = 70.sp,
            color = statusColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "$temperature °C",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = statusColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = status,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = statusColor
        )
    }
}

@Composable
fun TemperatureControls(
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = onDecrease
        ) {
            Text("Bajar")
        }

        Spacer(modifier = Modifier.width(20.dp))

        Button(
            onClick = onIncrease
        ) {
            Text("Subir")
        }
    }
}