package ua.waldemar.customdi.main.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ua.waldemar.customdi.core.feature.featureViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = featureViewModel(),
    goToUpdatePassword: () -> Unit
) {
    Column {
        Button(onClick = goToUpdatePassword) {
            Text("Update Password")
        }
    }
}