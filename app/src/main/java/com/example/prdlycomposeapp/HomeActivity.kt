package com.example.prdlycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.prdlycomposeapp.ui.theme.PRDLYComposeAppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApiRequests.getMoods(getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("token", "")!!).let {
            moods = it
        }
        setContent {
            PRDLYComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

var moods = mutableListOf<Mood>()

@Composable
fun HomeScreen() {
    LazyColumn {
        items(moods.size) { index ->
            Text(text = moods[index].mood)
        }
    }
}