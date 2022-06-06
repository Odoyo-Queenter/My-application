package dev.queenter.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var tilEmail:TextInputLayout
    lateinit var etEmail:TextInputEditText
    lateinit var tilPassword:TextInputLayout
    lateinit var etPassword:TextInputEditText
    lateinit var btnSignup:Button
    lateinit var tvSignup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tilEmail=findViewById(R.id.tilEmail)
        etEmail=findViewById(R.id.etEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etPassword=findViewById(R.id.etPassword)
        btnSignup=findViewById(R.id.btnSignup)
        tilEmail=findViewById(R.id.tilEmail)
        tvSignup=findViewById(R.id.tvSignup)
        tvSignup.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        btnSignup.setOnClickListener {
            validate()
        }

        tvSignup.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }


    }
    fun validate(){
        var email=etEmail.text.toString()
        var password=etPassword.text.toString()
        if (email.isBlank()){
            tilEmail.error="Email is required"

        }
        if (password.isBlank()){
            tilPassword.error="Password required"
        }
    }
}