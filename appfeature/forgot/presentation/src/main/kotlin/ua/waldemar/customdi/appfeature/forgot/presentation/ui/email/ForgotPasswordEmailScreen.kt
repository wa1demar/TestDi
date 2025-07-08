package ua.waldemar.customdi.appfeature.forgot.presentation.ui.email

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.waldemar.customdi.appfeature.common.EventConsumer
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.code.navigateToForgotPasswordCode
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.forgotPasswordViewModel
import ua.waldemar.customdi.core.feature.LocalNavController

@Composable
internal fun ForgotPasswordEmailScreen(
    viewModel: ForgotPasswordEmailViewModel = forgotPasswordViewModel()
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    ForgotPasswordEmailContent(
        email = email,
        isLoading = isLoading,
        onEmailChanged = viewModel::onEmailChanged,
        onContinueClicked = viewModel::onContinueClicked
    )

    val navController = LocalNavController.current
    EventConsumer(viewModel.nextEvent) {
        navController.navigateToForgotPasswordCode()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ForgotPasswordEmailContent(
    email: String,
    isLoading: Boolean,
    onEmailChanged: (String) -> Unit,
    onContinueClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text("Forgot password")
            })
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            TextField(
                value = email,
                onValueChange = onEmailChanged
            )
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = onContinueClicked
                ) {
                    Text("Next")
                }
            }
        }
    }
}