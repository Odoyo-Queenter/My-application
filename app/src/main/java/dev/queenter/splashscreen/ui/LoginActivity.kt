package dev.queenter.splashscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.queenter.splashscreen.LoginRequest
import dev.queenter.splashscreen.LoginResponse
import dev.queenter.splashscreen.R
import retrofit.ApiClient
import retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    lateinit var tilEmail:TextInputLayout
    lateinit var etEmail:TextInputEditText
    lateinit var tilPassword:TextInputLayout
    lateinit var etPassword:TextInputEditText
    lateinit var btnSignup:Button
    lateinit var tvSignup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPrefs = getSharedPreferences("SPLASH SCREEN_PREFS", MODE_PRIVATE)
        tilEmail=findViewById(R.id.tilEmail)
        etEmail=findViewById(R.id.etEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etPassword=findViewById(R.id.etPassword)
        btnSignup=findViewById(R.id.btnSignup)
        tilEmail=findViewById(R.id.tilEmail)
        tvSignup=findViewById(R.id.tvSignup)
        tvSignup.setOnClickListener {
            val intent=Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        btnSignup.setOnClickListener {
            validate()
        }
//        btnSignup.setOnClickListener {
////            val intent = Intent (this,HomeActivity::class.java)
////            startActivity(intent)
//        }


        tvSignup.setOnClickListener {
            val intent=Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }



    }
    fun validate(){
        var email=etEmail.text.toString()
        var password=etPassword.text.toString()
        var error = false
        if (email.isBlank()){
            tilEmail.error="Email is required"

        }
        if (password.isBlank()){
            tilPassword.error="Password required"
            error = true
        }
        if (!error){
            var loginRequest = LoginRequest(email,password)
//            binding.pbLogin.visibility = View.GONE

            makeLoginRequest(loginRequest)
        }


    }
    fun makeLoginRequest(loginRequest: LoginRequest){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.login(loginRequest)

        request.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
              if (response.isSuccessful){
                  val loginResponse = response.body()
                  saveLoginDetails(loginResponse!!)
               Toast.makeText(baseContext,loginResponse?.message, Toast.LENGTH_LONG).show()
                  startActivity(Intent(baseContext,HomeActivity::class.java))
                  finish()

              }
                else{
                    val error = response.errorBody()?.string()
                  Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()


              }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                binding.pbLogin.visibility = View.Gone
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()

    }
}





