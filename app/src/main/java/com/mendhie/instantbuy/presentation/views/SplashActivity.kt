package com.mendhie.instantbuy.presentation.views

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
        if(introManager.check())
            startActivity(Intent(this, MainActivity::class.java))
        else
            startActivity(Intent(this, LandingActivity::class.java))
        finish()
    }
}