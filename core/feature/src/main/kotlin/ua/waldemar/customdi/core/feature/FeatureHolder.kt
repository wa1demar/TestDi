package ua.waldemar.customdi.core.feature

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class FeatureHolder<C : Any> : ViewModel() {
    abstract val component: C

    init {
        Log.d("DI_SCOPE", "${this::class.simpleName} created. Component scope is created")
    }

    override fun onCleared() {
        Log.d("DI_SCOPE", "${this::class.simpleName} cleared. Component scope is destroyed.")
        super.onCleared()
    }
} 