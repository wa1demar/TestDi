package ua.waldemar.customdi.main.view.di

import android.content.Context
import ua.waldemar.customdi.main.model.di.MainModelComponent

object MainDI {
    fun init(context: Context, userId: String) {
        MainModelComponent.create(context, userId)
    }
    fun get() = MainModelComponent.get()
    fun destroy() = MainModelComponent.clear()
}