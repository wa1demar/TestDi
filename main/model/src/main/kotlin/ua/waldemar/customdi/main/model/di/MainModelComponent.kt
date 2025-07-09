package ua.waldemar.customdi.main.model.di

import android.content.Context
import android.util.Log
import ua.waldemar.customdi.main.model.di.modules.DomainModule
import ua.waldemar.customdi.main.model.di.modules.AnalyticsModule

class MainModelComponent(
    val domainModule: DomainModule,
    val analyticsModule: AnalyticsModule
) {
    companion object {
        @Volatile
        private var instance: MainModelComponent? = null

        fun create(context: Context, userId: String): MainModelComponent {
            Log.d("MainModelComponent", "Creating DI scope for userId=$userId")
            return instance ?: synchronized(this) {
                instance ?: MainModelComponentFactory.create(context, userId).also {
                    instance = it
                    Log.d("MainModelComponent", "DI scope created")
                }
            }
        }

        fun get(): MainModelComponent = requireNotNull(instance) {
            "MainModelComponent not initialized. Call create() first."
        }

        fun clear() {
            instance = null
            Log.d("MainModelComponent", "Clearing DI scope")
        }
    }
}