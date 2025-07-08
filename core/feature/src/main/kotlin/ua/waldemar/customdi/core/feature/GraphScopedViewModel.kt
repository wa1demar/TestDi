package ua.waldemar.customdi.core.feature

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified HolderVM : ViewModel, reified ScreenVM : ViewModel> graphScopedViewModel(
    crossinline holderFactory: (context: Context) -> HolderVM,
    crossinline screenVmFactory: (holder: HolderVM) -> ScreenVM,
): ScreenVM {
    val context = LocalContext.current.applicationContext
    val navController = LocalNavController.current

    // 1. Знаходимо батьківський граф навігації (ваша реалізація).
    val entry = navController.currentBackStackEntry
    val navGraphRoute = entry?.destination?.parent?.route
        ?: error("Не вдалося знайти батьківський граф навігації.")
    val parentEntry = remember(entry) {
        navController.getBackStackEntry(navGraphRoute)
    }

    // 2. Створюємо ViewModel-холдер, прив'язаний до графа, використовуючи передану фабрику.
    val featureHolder: HolderVM = viewModel(
        viewModelStoreOwner = parentEntry,
        key = HolderVM::class.java.name, // Унікальний ключ, щоб уникнути конфліктів
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return holderFactory(context) as T
            }
        }
    )

    // 3. Створюємо ViewModel для екрана, використовуючи холдер як залежність.
    return viewModel(
        key = ScreenVM::class.java.name, // Унікальний ключ для ViewModel екрана
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return screenVmFactory(featureHolder) as T
            }
        }
    )
}