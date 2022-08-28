package com.mendhie.instantbuy.presentation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mendhie.instantbuy.data.models.LoginAuth
import com.mendhie.instantbuy.data.models.LoginDetails
import com.mendhie.instantbuy.data.remote.StoreApi
import com.mendhie.instantbuy.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val TAG = "LoginAct"
    private val REQ_CODE = 412

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener { login() }
        binding.txtSignup.setOnClickListener {
            startActivityForResult(Intent(this, SignupActivity::class.java), REQ_CODE)
        }
    }

    fun login(){
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

        StoreApi.storeApi.login(LoginDetails(username, password)).enqueue(object :
            Callback<LoginAuth> {
            override fun onResponse(call: Call<LoginAuth>, response: Response<LoginAuth>) {
                binding.btnLogin.isEnabled = true
                if(response.code()==200 && !response.body()!!.token.isEmpty()){
                    Log.i(TAG, "onResponse:200 auth- ${response.body()}")
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                else if(response.code()==401){
                    Log.i(TAG, "onResponse:401 auth- ${response.body()}")
                    Toast.makeText(
                        this@LoginActivity,
                        "Incorrect username or password",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<LoginAuth>, t: Throwable) {
                binding.btnLogin.isEnabled = true
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
                Toast.makeText(
                    this@LoginActivity,
                    "Error occured: ${t.localizedMessage}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQ_CODE&&resultCode== RESULT_OK)
            finish()
    }
}