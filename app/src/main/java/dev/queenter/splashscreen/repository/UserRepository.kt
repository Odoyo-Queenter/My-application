package dev.queenter.splashscreen.repository

import apiInterface.ApiClient
import apiInterface.ApiInterface
import dev.queenter.splashscreen.LoginRequest
import dev.queenter.splashscreen.models.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    val apiClient = ApiClient.buildApiClient((ApiInterface::class.java))

    suspend fun loginUser(loginRequest: LoginRequest)
    = withContext(Dispatchers.IO){
        val response = apiClient.login(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest: RegisterRequest)
    = withContext(Dispatchers.IO){
        val response  = apiClient.registerUser(registerRequest)
        return@withContext response
    }
}