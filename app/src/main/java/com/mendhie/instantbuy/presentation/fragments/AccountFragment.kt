package com.mendhie.instantbuy.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.mendhie.instantbuy.R
import com.mendhie.instantbuy.databinding.FragmentAccountBinding
import com.mendhie.instantbuy.domain.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private val TAG = "AccountFrag"
    lateinit var binding: FragmentAccountBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.user.observe(this, {user -> Log.i(TAG, "onCreate: $user")})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}