package com.mendhie.instantbuy.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mendhie.instantbuy.data.models.*
import com.mendhie.instantbuy.data.remote.StoreApi
import com.mendhie.instantbuy.databinding.ActivitySignup2Binding
import com.mendhie.instantbuy.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private val TAG = "SignupAct"
    private var signup = false
    private lateinit var user: UserShort
    private lateinit var binding: ActivitySignupBinding
    private lateinit var binding2: ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        binding2 = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {signup() }
        binding2.btnSubmit.setOnClickListener { submit() }
    }

    fun signup(){
        val fName = binding.edtFname.text.toString()
        val lName = binding.edtLname.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val username = binding.edtUsername.text.toString()

        if(fName.isEmpty()){
            binding.edtFname.error = "Enter First name"
            return
        }
        if(lName.isEmpty()){
            binding.edtLname.error = "Enter last name"
            return
        }
        if(email.isEmpty()){
            binding.edtEmail.error = "Enter email"
            return
        }
        if(password.isEmpty()){
            binding.edtPassword.error = "provide password"
            return
        }
        if(username.isEmpty()){
            binding.edtUsername.error = "provide username"
            return
        }

        val name = Name(fName, lName)
        user = UserShort(name = name, email = email, password = password, username = username)
        signup = true
        setResult(RESULT_OK)
        setContentView(binding2.root)
    }

    fun submit(){
        val phone = binding2.edtPhone.text.toString()
        val city = binding2.edtCity.text.toString()
        val street = binding2.edtStreet.text.toString()
        val number = binding2.edtHouseNo.text.toString()
        val zip = binding2.edtZipcode.text.toString()

        if(phone.isEmpty()){
            binding2.edtPhone.error = "provide city"
            return
        }
        if(city.isEmpty()){
            binding2.edtCity.error = "provide city"
            return
        }
        if(street.isEmpty()){
            binding2.edtStreet.error = "provide street"
            return
        }
        if(number.isEmpty()){
            binding2.edtHouseNo.error = "provide number"
            return
        }
        if(zip.isEmpty()){
            binding2.edtZipcode.error = "provide zip code"
            return
        }

        val address = Address(Geolocation("0","0"), city, street, number.toInt(), zip)
        val userFull = UserFull(address, user.email, user.username, user.password, user.name, phone)

        StoreApi.storeApi.createUser(userFull).enqueue(object :
            Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                //binding.btnLogin.isEnabled = true
                if(response.code()==200){
                    Log.i(TAG, "onResponse:200 auth- ${response.body()}")
                    startActivity(Intent(this@SignupActivity, MainActivity::class.java))
                    finish()
                }

                else if(response.code()==401){
                    Log.i(TAG, "onResponse:401 auth- ${response.body()}")
                    Toast.makeText(
                        this@SignupActivity,
                        "Incorrect username or password",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                //binding.btnLogin.isEnabled = true
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
                Toast.makeText(
                    this@SignupActivity,
                    "Error occured: ${t.localizedMessage}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onBackPressed() {
        if(signup){}
        else {
            super.onBackPressed()
            finish()
        }
    }
}