# CustomDI: Як підключати фічі

## 1. Фіча з nested graph (flow)

### Структура
```
main/feature/yourfeature/
  data/
  domain/
  presentation/
    YourFeatureFormScreen.kt
    YourFeatureResultScreen.kt
    YourFeatureFeatureHolder.kt
    YourFeatureNavigation.kt
  di/
    YourFeatureComponent.kt
```

### Кроки підключення
1. **Створи DI-компонент** (YourFeatureComponent), який реалізує HasViewModelCreators і містить фабрики для всіх ViewModel flow.
2. **Створи FeatureHolder** (наслідує FeatureHolder<YourFeatureComponent> з core/feature).
3. **Створи extension-функцію для ViewModel**:
   ```kotlin
   @Composable
   inline fun <reified VM : ViewModel> yourFeatureViewModel(): VM = featureFlowViewModel {
       context -> YourFeatureFeatureHolder(context)
   }
   ```
4. **Створи YourFeatureNavigation.kt** з navigation(...) для flow:
   ```kotlin
   fun NavGraphBuilder.yourFeatureGraph(onExit: () -> Unit) {
       navigation(startDestination = ..., route = YourFeatureGraphRoute) {
           composable(YourFeatureFormRoute) { YourFeatureFormScreen() }
           composable(YourFeatureResultRoute) { YourFeatureResultScreen() }
       }
   }
   ```
5. **У всіх екранах flow отримуй ViewModel через yourFeatureViewModel()**.
6. **У головному NavHost додай flow через yourFeatureGraph** і TrackGraphExit для clean-up:
   ```kotlin
   yourFeatureGraph(onExit = { /* очищення DI-scope */ })
   ```
7. **Для переходу між екранами flow** використовуй navigate(route) без popUpTo!
8. **Для виходу з flow** використовуй popUpTo(YourFeatureGraphRoute, inclusive = true) + launchSingleTop = true.
9. **Для скоупу DI-компонента на рівні flow** (nested graph) або таби — використовуй DiScopeHost з параметром owner:
   ```kotlin
   val owner = navController.getBackStackEntry(YourFeatureGraphRoute)
   DiScopeHost(
       componentFactory = { YourFeatureComponent() },
       viewModelCreatorsProvider = { it.viewModelCreators },
       owner = owner
   ) {
       // ... твій flow
   }
   ```

---

## 2. Фіча з одним екраном

### Структура
```
main/feature/featurex/
  data/
  domain/
  presentation/
    FeatureXScreen.kt
  di/
    FeatureXComponent.kt
```

### Кроки підключення
1. **Створи DI-компонент** (FeatureXComponent), який реалізує HasViewModelCreators і містить фабрику для ViewModel.
2. **Використовуй DiScopeHost** у navigation (без owner, якщо scope не потрібен):
   ```kotlin
   fun NavGraphBuilder.featureXScreen() {
       composable("featureX") {
           DiScopeHost(
               componentFactory = { FeatureXComponent() },
               viewModelCreatorsProvider = { it.viewModelCreators }
           ) {
               FeatureXScreen()
           }
       }
   }
   ```
3. **У FeatureXScreen отримуй ViewModel через featureViewModel() або аналогічний extension.**

---

## Best practices
- Для flow завжди використовуй FeatureHolder + featureFlowViewModel + navigation(...).
- Для простих екранів — DiScopeHost.
- Для скоупу DI-компонента на рівні route/tab/activity — передавай owner у DiScopeHost (owner = navController.getBackStackEntry(route)).
- Не використовуй popUpTo всередині flow для переходу між екранами.
- Очищення DI-scope — тільки при виході з flow (TrackGraphExit або popUpTo).
- Всі DI-інтерфейси (FeatureHolder, HasViewModelCreators) мають бути у core/feature. 