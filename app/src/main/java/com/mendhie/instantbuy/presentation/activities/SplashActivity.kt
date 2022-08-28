package com.mendhie.instantbuy.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mendhie.instantbuy.databinding.ActivitySplashBinding
import com.mendhie.instantbuy.presentation.manager.IntroductionManager

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val introManager = IntroductionManager(this)
        if(introManager.signup() && introManager.login())
            startActivity(Intent(this, MainActivity::class.java))
        else if(introManager.check())
            startActivity(Intent(this, LoginActivity::class.java))
        else {
            introManager.setFirst(true)
            startActivity(Intent(this, LandingActivity::class.java))
        }
        finish()
    }
}