package com.example.keymanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.keymanagement.navigation.NavigationScreen
import com.example.keymanagement.ui.features.authorization.GreetingScreen
import com.example.keymanagement.ui.theme.KeyManagementTheme
import com.example.keymanagement.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyManagementTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationScreen(viewModel)
                }
            }
        }
    }
}




