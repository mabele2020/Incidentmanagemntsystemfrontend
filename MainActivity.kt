// MainActivity.kt
package com.example.myproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myproject.ui.IncidentScreen
import com.example.myproject.ui.SignUpScreen

enum class Screen {
    SIGN_UP, INCIDENT
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf(Screen.SIGN_UP) }
            var signedUpUserId by remember { mutableStateOf<Long?>(null) }

            when (currentScreen) {
                Screen.SIGN_UP -> SignUpScreen { userId ->
                    signedUpUserId = userId
                    currentScreen = Screen.INCIDENT
                }
                Screen.INCIDENT -> {
                    signedUpUserId?.let { userId ->
                        IncidentScreen(userId)
                    }
                }
            }
        }
    }
}
