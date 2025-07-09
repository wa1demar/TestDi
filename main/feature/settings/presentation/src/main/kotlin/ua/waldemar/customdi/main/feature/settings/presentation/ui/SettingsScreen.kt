package ua.waldemar.customdi.main.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.waldemar.customdi.core.feature.featureViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = featureViewModel(),
    goToUpdatePassword: () -> Unit
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Settings")
                }
            )
        }
    ) { innerPaddings ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPaddings)) {
            Text(text = settings.id)
            Button(onClick = goToUpdatePassword) {
                Text("Update Password")
            }
        }
    }
}