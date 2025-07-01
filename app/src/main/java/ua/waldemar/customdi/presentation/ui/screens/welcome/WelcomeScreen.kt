package ua.waldemar.customdi.presentation.ui.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun WelcomeScreen(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
) {
    WelcomeContent(
        onLoginClicked = onLoginClicked,
        onRegisterClicked = onRegisterClicked,
    )
}

@Composable
internal fun WelcomeContent(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Welcome")

        Button(onClick = onLoginClicked) {
            Text("Sign In")
        }
        TextButton(onClick = onRegisterClicked) {
            Text("Sign Up")
        }
    }
}