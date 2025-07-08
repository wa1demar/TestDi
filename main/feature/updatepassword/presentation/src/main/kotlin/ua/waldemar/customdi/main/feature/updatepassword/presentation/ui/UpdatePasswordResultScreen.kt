package ua.waldemar.customdi.main.feature.updatepassword.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ua.waldemar.customdi.core.feature.LocalNavController
import ua.waldemar.customdi.main.feature.updatepassword.presentation.di.updatePasswordViewModel

@Composable
fun UpdatePasswordResultScreen(
    viewModel: UpdatePasswordResultViewModel = updatePasswordViewModel(),
    onBackToSettings: () -> Unit
) {
    Text("Password updated successfully!")

    BackHandler {
        onBackToSettings()
    }
} 