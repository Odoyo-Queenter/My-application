package retrofit

import dev.queenter.splashscreen.LoginRequest
import dev.queenter.splashscreen.LoginResponse
import dev.queenter.splashscreen.models.RegisterRequest
import dev.queenter.splashscreen.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest):Call<LoginResponse>



}