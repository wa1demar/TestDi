package ua.waldemar.customdi.main.feature.withdraw.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ua.waldemar.customdi.core.feature.LocalNavController

@Composable
fun WithdrawResultScreen(
    navController: NavController = LocalNavController.current
) {
    Column(Modifier.padding(16.dp)) {
        Text("Withdraw successful!")
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            navController.popBackStack(WithdrawGraph, inclusive = true)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Home/Tab")
        }
    }
} 