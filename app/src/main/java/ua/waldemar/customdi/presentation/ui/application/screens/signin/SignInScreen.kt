package ua.waldemar.customdi.presentation.ui.application.screens.signin

import android.app.Activity
import androidx.activity.compose.LocalActivity
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.waldemar.customdi.domain.ExecStatus
import ua.waldemar.customdi.presentation.ui.application.common.collectLaunchEwaUiEvent
import ua.waldemar.customdi.presentation.ui.application.common.mainViewModel

@Composable
internal fun SignInScreen(
    viewModel: SignInViewModel = mainViewModel(),
    activity: Activity? = LocalActivity.current,
) {

    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val signInUIStatus by viewModel.signInUIStatus.collectAsStateWithLifecycle(ExecStatus.Idle)

    SignInContent(
        signInUIStatus = signInUIStatus,
        email = email,
        onEmailChanged = viewModel::onEmailChanges,
        password = password,
        onPasswordChanged = viewModel::onPasswordChanges,
        onLoginClicked = viewModel::onLoginClicked
    )

    LaunchedEffect(Unit) {
        activity?.let {
            viewModel.launchUiEvent.collectLaunchEwaUiEvent(activity)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignInContent(
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    signInUIStatus: ExecStatus
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text("Sign In")
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
            TextField(
                value = password,
                onValueChange = onPasswordChanged
            )
            if (signInUIStatus == ExecStatus.InProgress) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = onLoginClicked
                ) {
                    Text("Login")
                }
            }
        }
    }
}