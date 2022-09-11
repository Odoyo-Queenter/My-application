package dev.queenter.splashscreen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.queenter.splashscreen.R
import dev.queenter.splashscreen.models.RegisterRequest
import dev.queenter.splashscreen.models.RegisterResponse
import apiInterface.ApiClient
import apiInterface.ApiInterface
import dev.queenter.splashscreen.viewModel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    lateinit var tilFirstname:TextInputLayout
    lateinit var  tilLastname :TextInputLayout
    lateinit var tilEmail:TextInputLayout
    lateinit var  tilPass:TextInputLayout
    lateinit var tilConfpass:TextInputLayout
    lateinit var etFirstname:TextInputEditText
    lateinit var etLastname:TextInputEditText
    lateinit var etEmail:TextInputEditText
    lateinit var etPass:TextInputEditText
    lateinit var etConfpass:TextInputEditText
    lateinit var btnSignin:Button
    lateinit var tvLogin:TextView
    lateinit var tilPhoneNumber: TextInputLayout
    lateinit var etPhoneNumber: TextInputEditText
    lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        val userViewModel: UserViewModel by viewModels()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        tilFirstname = findViewById(R.id.tilFirstname)
        tilLastname = findViewById(R.id.tilLastname)
        tilEmail = findViewById(R.id.tilEmail)
        tilPass = findViewById(R.id.tilPass)
        tilConfpass = findViewById(R.id.tilConfpass)
        etFirstname = findViewById(R.id.etFirstname)
        etLastname = findViewById(R.id.etLastname)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)
        etConfpass = findViewById(R.id.etConfpass)
        btnSignin = findViewById(R.id.btnSignin)
        tvLogin = findViewById(R.id.tvLogin)
        tilPhoneNumber = findViewById(R.id.tilPhoneNumber)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)


        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(Intent())
        }
        btnSignin.setOnClickListener {
            validatesignin()

        }


    }
    fun validatesignin(){
        var firstName = etFirstname.text.toString()
        var lastName = etLastname.text.toString()
        var email = etEmail.text.toString()
        var pass = etPass.text.toString()
        var confirm = etConfpass.text.toString()
        var phonenumber = etPhoneNumber.text.toString()
        var error=false

        if (firstName.isBlank()){
            var error=true
            tilFirstname.error= "First name required"
        }
        if (lastName.isBlank()){
            var error=true
            tilLastname.error = "Last name required"
        }
        if (email.isBlank()){
            var error=true
            tilEmail.error = "Email is required"
        }
        if (pass.isBlank()){
            var error=true
            tilPass.error = "Password is required"
        }

        if (pass.length<8){
            var error=true
            tilPass.error = "password is too short"
        }
        if (pass.length>16){
            var error=true
            tilPass.error = "password is too long"
        }
        if (confirm.isBlank()) {
            var error = true
            tilConfpass.error = "Confirm password"
        }
        if (phonenumber.isBlank()){
            var error=true
            tilPhoneNumber.error = "Phone number required"
        }
        if (!error){
       val registerResponse = RegisterRequest(firstName,lastName,phonenumber,email,pass)
            makeRegistrationRequest(registerResponse)

            startActivity(Intent(this,LoginActivity::class.java))
            userViewModel.registerUser(registerResponse)

        }


    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer { registerResponse->
            Toast.makeText(baseContext, registerResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        })

        userViewModel.registerErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,LoginActivity::class.java))
        })
    }
    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)

        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                    //intent to login
                }else{
                    val  error = response.errorBody()?.string()

                }

            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }

        })
    }

}
