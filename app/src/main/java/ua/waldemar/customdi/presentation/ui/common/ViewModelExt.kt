package ua.waldemar.customdi.presentation.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.waldemar.customdi.main.App
import ua.waldemar.customdi.main.di.AppContainer

internal class GenericViewModelFactory<VM : ViewModel>(
    private val creator: () -> VM
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = creator()
        if (!modelClass.isInstance(viewModel)) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        return viewModel as T
    }
}

@Composable
internal inline fun <reified VM : ViewModel> mainViewModel(): VM {
    val application = LocalContext.current.applicationContext as App
    val factory = createFactory(application.appContainer, VM::class.java)
    return viewModel(factory = factory)
}

@Suppress("UNCHECKED_CAST")
internal fun <VM : ViewModel> createFactory(
    appContainer: AppContainer,
    modelClass: Class<VM>
): ViewModelProvider.Factory {
    val creator = appContainer.viewModelCreators[modelClass]
        ?: throw IllegalStateException("Unknown ViewModel class: $modelClass")

    return GenericViewModelFactory {
        creator() as VM
    }
}