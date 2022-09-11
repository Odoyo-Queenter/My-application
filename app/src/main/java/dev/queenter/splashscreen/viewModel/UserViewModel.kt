package dev.queenter.splashscreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.queenter.splashscreen.LoginRequest
import dev.queenter.splashscreen.LoginResponse
import dev.queenter.splashscreen.models.RegisterRequest
import dev.queenter.splashscreen.models.RegisterResponse
import dev.queenter.splashscreen.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel(){
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val loginErrorLiveData = MutableLiveData<String?>()
    val registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData= MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }

    }

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }
}