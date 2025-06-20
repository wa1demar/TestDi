package ua.waldemar.customdi.main.view.main.application.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.waldemar.customdi.main.model.domain.UserInfoModel
import ua.waldemar.customdi.main.view.main.application.common.mainViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = mainViewModel(),
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    HomeContent(screenState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeContent(
    screenState: HomeScreenState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text("Home Screen")
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            when {
                screenState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                screenState.error != null -> {
                    ErrorContent(screenState.error)
                }
                screenState.userInfo != null -> {
                    Content(screenState.userInfo)
                }
                else -> {
                    EmptyScreenContent()
                }
            }
        }
    }
}

@Composable
fun ColumnScope.EmptyScreenContent() {
    Text(text = "User is empty", modifier = Modifier.align(Alignment.CenterHorizontally))
}

@Composable
fun ColumnScope.ErrorContent(message: String) {
    Text(text = "Error: $message", modifier = Modifier.align(Alignment.CenterHorizontally))
}

@Composable
fun ColumnScope.Content(userInfo: UserInfoModel) {
    Text("First name: ${userInfo.firstName}")
    Text("Middle name: ${userInfo.middleName}")
    Text("Last name: ${userInfo.lastName}")
}