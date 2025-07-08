package ua.waldemar.customdi.main.feature.updatepassword.data.di

import ua.waldemar.customdi.main.feature.updatepassword.data.UpdatePasswordRepositoryImpl
import ua.waldemar.customdi.main.feature.updatepassword.domain.UpdatePasswordRepository

class UpdatePasswordDataContainer {
    val updatePasswordRepository: UpdatePasswordRepository by lazy {
        UpdatePasswordRepositoryImpl()
    }
}