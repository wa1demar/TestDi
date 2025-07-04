package ua.waldemar.customdi.main.publicapi.di

import android.util.Log
import ua.waldemar.customdi.main.public.data.di.PublicDataModule

class MainPublicComponent {

    private val dataModule = PublicDataModule()

    val useCaseModule = PublicUseCaseModule(
        dataModule.signInRepository,
        dataModule.signUpRepository
    )

    companion object {
        @Volatile
        private var instance: MainPublicComponent? = null

        fun create(): MainPublicComponent {
            Log.d("MainPublicComponent", "Creating Public DI scope")
            return instance ?: synchronized(this) {
                instance ?: MainPublicComponent().also {
                    instance = it
                    Log.d("MainPublicComponent", "Public DI scope created")
                }
            }
        }

        fun get(): MainPublicComponent = requireNotNull(instance) {
            "MainPublicComponent not initialized. Call create() first."
        }

        fun clear() {
            Log.d("MainPublicComponent", "Clearing Public DI scope")
            instance = null
        }
    }
}