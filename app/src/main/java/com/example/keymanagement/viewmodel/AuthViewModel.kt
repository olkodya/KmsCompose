package com.example.keymanagement.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keymanagement.network.dto.LoginDto
import com.example.keymanagement.network.repository.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    val isSuccessLoading = mutableStateOf(value = false)
    val imageErrorAuth = mutableStateOf(value = false)
    val progressBar = mutableStateOf(value = false)
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressBar.value = true
                val authService = RetrofitHelper().getAuthService()
                val responseService =
                    authService.getLogin(LoginDto(username = username, password = password))

                if (responseService.isSuccessful) {
                    delay(5500L)
                    progressBar.value = false
                    isSuccessLoading.value = true
                    responseService.body()?.let { tokenDto ->
                        Log.d("Logging", "Response TokenDto: $tokenDto")
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        progressBar.value = false
                        imageErrorAuth.value = true
                        delay(5500L)
                        imageErrorAuth.value = false
                        error.close()
                    }
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)
                progressBar.value = false
            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
                progressBar.value = false
            }
        }
    }
}