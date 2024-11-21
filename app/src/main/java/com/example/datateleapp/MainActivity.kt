package com.example.datateleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datateleapp.ui.theme.DataTeleAppTheme
import kotlin.random.Random
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.toArgb

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataTeleAppTheme {
                ColorRandomizer()
            }
        }
    }
}

@Composable
fun ColorRandomizer() {
    var color by remember { mutableStateOf(randomColor()) }
    val colorVariants = generateColorVariants(color)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        colorVariants.forEach { variant ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(variant)
                    .padding(8.dp)
            ) {
                Text(
                    text = "#${variant.toArgb().toString(16)}",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { color = randomColor() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(text = "Slumpa Färger")
        }
    }
}

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat()
    )
}

fun generateColorVariants(baseColor: Color): List<Color> {
    val variants = mutableListOf<Color>()
    for (i in 1..4) {
        variants.add(
            Color(
                red = (baseColor.red + i * 0.1f).coerceIn(0f, 1f),
                green = (baseColor.green + i * 0.1f).coerceIn(0f, 1f),
                blue = (baseColor.blue + i * 0.1f).coerceIn(0f, 1f)
            )
        )
    }
    return variants
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataTeleAppTheme {
        ColorRandomizer()
    }
}