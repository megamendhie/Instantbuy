package com.mendhie.instantbuy.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.mendhie.instantbuy.data.models.LoginDetails
import com.mendhie.instantbuy.data.models.LoginResponse
import com.mendhie.instantbuy.databinding.ActivityLoginBinding
import com.mendhie.instantbuy.domain.viewmodels.UserViewModel
import com.mendhie.instantbuy.presentation.manager.IntroductionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val tag = "LoginAct"
    private val reqCode = 412
    private val viewModel: UserViewModel by viewModels()
    @Inject lateinit var introManager: IntroductionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.txtSignup.setOnClickListener {
            startActivityForResult(Intent(this, SignupActivity::class.java), reqCode)
        }

        viewModel.loginResponse.observe(this, { loginResponse -> updateStatus(loginResponse) })
    }

    private fun updateStatus(loginResponse: LoginResponse) {
        binding.btnLogin.isEnabled = true
        if(loginResponse.code==200){
            introManager.setLogin(true)
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            Log.i(tag, "onResponse:401 auth- $loginResponse")
            Toast.makeText(this, "${loginResponse.code}: ${loginResponse.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(){
        if(!introManager.signup()){
            Snackbar.make(binding.txtSignup, "Signup first", Snackbar.LENGTH_LONG).show()
            return
        }
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()

        if(username.isEmpty()){
            binding.edtUsername.error = "Enter username"
            return
        }
        if(password.isEmpty()){
            binding.edtPassword.error = "Enter password"
            return
        }
        binding.btnLogin.isEnabled = false
        viewModel.login(LoginDetails(username, password))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==reqCode&&resultCode== RESULT_OK)
            finish()
    }
}