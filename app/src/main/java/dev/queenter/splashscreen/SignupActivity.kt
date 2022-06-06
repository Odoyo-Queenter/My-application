package dev.queenter.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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


    override fun onCreate(savedInstanceState: Bundle?) {
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


        tvLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
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

        if (firstName.isBlank()){
            tilFirstname.error= "First name required"
        }
        if (lastName.isBlank()){
            tilLastname.error = "Last name required"
        }
        if (email.isBlank()){
            tilEmail.error = "Email is required"
        }
        if (pass.isBlank()){
            tilPass.error = "Password is required"
        }
        if (confirm.isBlank()){
            tilConfpass.error = "Confirm password"
        }
    }
}