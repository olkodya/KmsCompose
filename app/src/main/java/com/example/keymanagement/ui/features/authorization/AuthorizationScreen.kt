package com.example.keymanagement.ui.features.authorization

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GreetingScreen( loadingProgressBar: Boolean,
                   onclickLogin: (email: String, password: String) -> Unit,
                   imageError: Boolean) {
    Greeting(loadingProgressBar = loadingProgressBar, onclickLogin = onclickLogin, imageError = imageError )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(modifier: Modifier = Modifier,
             loadingProgressBar: Boolean,
             onclickLogin: (email: String, password: String) -> Unit,
             imageError: Boolean ) {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    val text by rememberSaveable { mutableStateOf("Hello") }
    var textVisibility by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                ),
                title = {
                    Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = "Авторизация")
                }
            )
        },
        content = {

        }
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ), modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Логин") },
                    placeholder = { Text("Логин") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    label = { Text("Пароль") },
                    visualTransformation =
                    if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            val description =
                                if (passwordHidden) "Show password" else "Hide password"
                            Icon(imageVector = visibilityIcon, contentDescription = description)
                        }
                    }
                )
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .height(52.dp),
                    onClick =  {onclickLogin(login, password)},
                ) {
                    Text("Войти")
                }

                if (textVisibility) {
                    Text(text)
                }
            }
            ProgressBarLoading(isLoading = loadingProgressBar)
            ErrorImageAuth(isImageValidate = imageError)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingCollapsedPreview() {
//    KeyManagementTheme {
//        Greeting()
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingExpandedPreview() {
//    KeyManagementTheme {
//        Greeting()
//    }
//}