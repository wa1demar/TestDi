package ua.waldemar.customdi.main.feature.history.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.waldemar.customdi.core.feature.featureViewModel
import ua.waldemar.customdi.main.feature.history.domain.HistoryItem
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
internal fun HistoryScreen(
    viewModel: HistoryViewModel = featureViewModel()
) {

    val items by viewModel.historyItems.collectAsStateWithLifecycle()

    HistoryContent(items)
}

@Composable
fun HistoryContent(items: List<HistoryItem>) {
    val listState = rememberLazyListState()
    Column {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                Text(text = item.title)
            }
        }
    }
}