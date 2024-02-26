package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtworkApp()
                }
            }
        }
    }
}

data class Artwork(val title: String, val description: String, val image: Painter)


@Composable
fun ArtworkApp() {
    // Define the list of artworks
    val artworks = listOf(
        Artwork("pastel art", "Suzan (2003)", painterResource(R.drawable.abc)),
        Artwork("sunset", "Sam (1990)", painterResource(R.drawable.download)),
        Artwork("Traditional art", "Kakon (1980)", painterResource(R.drawable.traditional_paintings_02))
    )

    var currentIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Artwork wall section
        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = artworks[currentIndex].image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.FillBounds
            )
        }

        // Artwork descriptor section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = artworks[currentIndex].title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = artworks[currentIndex].description)
        }

        // Display controller section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
                }
            ) {
                Text("Previous")
            }
            Button(
                onClick = {
                    currentIndex = (currentIndex + 1) % artworks.size
                }
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtworkAppPreview() {
    Assignment2Theme {
        ArtworkApp()
    }
}