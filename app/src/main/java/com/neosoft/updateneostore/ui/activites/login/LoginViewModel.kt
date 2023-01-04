package com.neosoft.updateneostore.ui.activites.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.updateneostore.data.pojo.RegisterResponse
import com.neosoft.updateneostore.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {
    val loginLiveData = MutableLiveData<RegisterResponse>()


    fun getlogin(email: String,password:String) {
        viewModelScope.launch {
            val response = repository.login(email, password)
            if (response != null) {
                loginLiveData.postValue(response.body())
            }
        }

    }

}