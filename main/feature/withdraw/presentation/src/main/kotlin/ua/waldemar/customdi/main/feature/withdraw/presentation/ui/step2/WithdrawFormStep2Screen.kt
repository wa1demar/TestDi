package ua.waldemar.customdi.main.feature.withdraw.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ua.waldemar.customdi.core.feature.LocalNavController
import ua.waldemar.customdi.main.feature.withdraw.presentation.di.withdrawViewModel
import ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step2.WithdrawFormStep2ViewModel

@Composable
fun WithdrawFormStep2Screen(
    viewModel: WithdrawFormStep2ViewModel = withdrawViewModel(),
    navController: NavController = LocalNavController.current
) {
    var input by remember { mutableStateOf(viewModel.input ?: "") }
    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Step 2 Data") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            viewModel.input = input
            navController.navigateToConfirm()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Next")
        }
    }
} 