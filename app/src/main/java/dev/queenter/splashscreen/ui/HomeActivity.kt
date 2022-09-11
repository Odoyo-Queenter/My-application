package dev.queenter.splashscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.queenter.splashscreen.LoginResponse
import dev.queenter.splashscreen.R
import dev.queenter.splashscreen.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    lateinit var binding: ActivityHomeBinding
    lateinit var tvLogout:TextView
    lateinit var fcHome:FragmentContainerView
    lateinit var bmvHome:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tvLogout = findViewById(R.id.tvLogout)
        tvLogout.setOnClickListener {
            val editor = sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN", " ")
            editor.putString("USER_ID","")
            editor.putString("PROFILE_ID","")
            editor.apply()
            startActivity(Intent(this,LoginActivity::class.java))
            logoutRequest()
        }
        castViews()
        setBottomNav()


    }

    fun castViews() {
        binding.fcvHome
        binding.bmvHome
    }
    fun setBottomNav() {
        binding.bmvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    val transaction = supportFragmentManager.beginTransaction().replace(R.id.fcvHome, planFragment())
                    true
                }

                R.id.track -> {
                    val transaction = supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment())
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(     R.id.fcvHome,
                        ProfileFragment()
                    ).commit()

                    true

                }
                else->false

            }

        }

    }
    fun logoutRequest(){
        sharedPrefs.edit().clear().commit()
    }
}

