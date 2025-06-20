package ua.waldemar.customdi.main.view.helpers

import ua.waldemar.customdi.main.model.di.MainModelComponent

internal inline fun <reified T> mainHelper(): Lazy<T> = lazy {
    when (T::class) {
        UiSignInHelper::class -> with(MainModelComponent.appDomainModule) {
            UiSignInHelper(
                signInInteractor
            ) as T
        }
        UiSignUpHelper::class -> with(MainModelComponent.appDomainModule) {
            UiSignUpHelper(
                signUpInteractor
            ) as T
        }
        else -> throw _root_ide_package_.kotlin.IllegalStateException("Failed to create instance of ${T::class.java}")
    }
}