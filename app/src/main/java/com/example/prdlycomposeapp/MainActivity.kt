package com.example.prdlycomposeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.prdlycomposeapp.ui.theme.PRDLYComposeAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        installSplashScreen()
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        if (sharedPref.getString("token", null) != null) {
            startNewActivity(context = this)
        }

        setContent {
            PRDLYComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(sharedPref)
                }
            }
        }
    }
}


fun startNewActivity(context: Context) {
    context.startActivity(Intent(context, HomeActivity::class.java))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(sharedPref: SharedPreferences) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var loginScreen by rememberSaveable { mutableStateOf(true) }

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text("PRDLY Compose App") })
        }
    ) {
        p ->
        Column(
            modifier = Modifier
                .padding(p)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = if (loginScreen) "Zaloguj się!" else "Zarejestruj się!",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Nazwa użytkownika") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Hasło") },
                        singleLine = true,
                        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                                val visibilityIcon =
                                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                                val description = if (passwordHidden) "Show password" else "Hide password"
                                Icon(imageVector = visibilityIcon, contentDescription = description)
                            }
                        }
                    )
                    if (!loginScreen) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = repeatPassword,
                            onValueChange = { repeatPassword = it },
                            label = { Text("Powtórz hasło") },
                            singleLine = true,
                            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                                    val visibilityIcon =
                                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                                    val description = if (passwordHidden) "Show password" else "Hide password"
                                    Icon(imageVector = visibilityIcon, contentDescription = description)
                                }
                            }
                        )
                    }

                    Row() {
                        Button(onClick = {
                            if (loginScreen) {
                                val token: Token = ApiRequests.login(User(null, username, password)) as Token
                                sharedPref.edit().putString("token", token.token).apply()
                                context.startActivity(Intent(context, HomeActivity::class.java))
                            } else {
                                // do nothing
                            }
                        }) {
                            Text(if (loginScreen) "Zaloguj się" else "Zarejestruj się")
                        }
                        TextButton(
                            onClick = { run { loginScreen = !loginScreen } }
                        ) {
                            Text(if (loginScreen) "Nie masz konta? Zarejestruj się" else "Zaloguj się")
                        }
                    }
                    
                }
            }
        }
    }
}