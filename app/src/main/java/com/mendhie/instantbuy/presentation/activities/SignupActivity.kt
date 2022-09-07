package com.mendhie.instantbuy.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.mendhie.instantbuy.data.models.*
import com.mendhie.instantbuy.databinding.ActivitySignup2Binding
import com.mendhie.instantbuy.databinding.ActivitySignupBinding
import com.mendhie.instantbuy.domain.viewmodels.UserViewModel
import com.mendhie.instantbuy.presentation.manager.IntroductionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private val TAG = "SignupAct"
    private var signup = false
    private lateinit var user: UserShort
    private lateinit var binding: ActivitySignupBinding
    private lateinit var binding2: ActivitySignup2Binding
    private val viewModel: UserViewModel by viewModels()
    @Inject lateinit var introManager: IntroductionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        binding2 = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {signup() }
        binding2.btnSubmit.setOnClickListener { submit() }

        viewModel.userResponse.observe(this, { user -> updateStatus(user) })
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
        viewModel.signup(userFull)
    }

    private fun updateStatus(user: UserResponse) {
        if(user.code == 200){
            introManager.setSignup(true)
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            Log.i(TAG, "updateStatus: $user")
            finish()
        }
        else{
            Log.i(TAG, "updateStatus: $user")
            Toast.makeText(this,
                "${user.message}",
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onBackPressed() {
        if(signup){}
        else {
            super.onBackPressed()
            finish()
        }
    }
}