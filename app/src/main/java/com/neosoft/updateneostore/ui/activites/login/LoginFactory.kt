package com.neosoft.updateneostore.ui.activites.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neosoft.updateneostore.repository.MainRepository
import javax.inject.Inject

class LoginFactory @Inject constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
