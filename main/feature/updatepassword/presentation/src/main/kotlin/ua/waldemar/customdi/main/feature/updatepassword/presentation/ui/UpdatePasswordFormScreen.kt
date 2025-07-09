package ua.waldemar.customdi.main.feature.updatepassword.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.waldemar.customdi.core.feature.LocalNavController
import ua.waldemar.customdi.main.feature.updatepassword.presentation.di.updatePasswordViewModel
import ua.waldemar.customdi.main.feature.updatepassword.presentation.navigateToUpdatePasswordResult

@Composable
fun UpdatePasswordFormScreen(
    viewModel: UpdatePasswordFormViewModel = updatePasswordViewModel(),
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val error by viewModel.error.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle()

// Якщо успіх — переходимо на result
    val navController = LocalNavController.current
    LaunchedEffect(success) {
        if (success == true) {
            navController.navigateToUpdatePasswordResult()
        }
    }

    UpdatePasswordContent(
        password = password,
        onPasswordChanged = { password = it },
        confirmPassword = confirmPassword,
        onConfirmPasswordChanged = { confirmPassword = it },
        onUpdatePasswordClicked = { viewModel.updatePassword(password, confirmPassword) },
        error = error
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePasswordContent(
    password: String,
    confirmPassword: String,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onUpdatePasswordClicked: () -> Unit,
    error: String?
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Update Password")
                }
            )
        }
    ) { innerPaddings ->
        Column(Modifier.fillMaxSize().padding(innerPaddings).padding(16.dp)) {
            OutlinedTextField(
                value = password,
                onValueChange = { onPasswordChanged(it) },
                label = { Text("New password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { onConfirmPasswordChanged(it) },
                label = { Text("Confirm password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { onUpdatePasswordClicked() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Password")
            }
            if (error != null) {
                Text(error, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}