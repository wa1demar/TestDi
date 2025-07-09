package ua.waldemar.customdi.main.feature.withdraw.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ua.waldemar.customdi.core.feature.LocalNavController
import ua.waldemar.customdi.main.feature.withdraw.presentation.di.withdrawViewModel
import ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step3.WithdrawFormConfirmViewModel

@Composable
fun WithdrawFormConfirmScreen(
    viewModel: WithdrawFormConfirmViewModel = withdrawViewModel(),
    navController: NavController = LocalNavController.current
) {
    val success by viewModel.success.collectAsStateWithLifecycle()
    val data by viewModel.data.collectAsStateWithLifecycle()

    Column(Modifier.padding(16.dp)) {
        Text("Confirm your withdraw data")
        Text("Step1: ${data.first}")
        Text("Step2: ${data.second}")
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.confirmWithdraw() }, modifier = Modifier.fillMaxWidth()) {
            Text("Confirm Withdraw")
        }
        if (success == true) {
            // Переходимо на результат при успіху
            LaunchedEffect(Unit) {
                navController.navigateToResult()
            }
        }
    }
} 