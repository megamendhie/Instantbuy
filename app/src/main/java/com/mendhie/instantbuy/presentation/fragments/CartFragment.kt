package com.mendhie.instantbuy.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mendhie.instantbuy.data.models.Cart
import com.mendhie.instantbuy.databinding.FragmentCartBinding
import com.mendhie.instantbuy.presentation.adapters.CartAdapter

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    private val TAG = "CartFrag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CartAdapter()

        binding.lstCart.layoutManager = LinearLayoutManager(context)
        binding.lstCart.adapter = adapter
    }

    fun updateCart(carts: List<Cart>) = adapter.updateCarts(carts)
}