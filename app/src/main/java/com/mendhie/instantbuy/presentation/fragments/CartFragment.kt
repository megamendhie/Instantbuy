package com.mendhie.instantbuy.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mendhie.instantbuy.data.models.Cart
import com.mendhie.instantbuy.data.models.Prod
import com.mendhie.instantbuy.data.remote.StoreApi
import com.mendhie.instantbuy.databinding.FragmentCartBinding
import com.mendhie.instantbuy.presentation.adapters.CartAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        StoreApi.storeApi.getCart().enqueue(object : Callback<List<Cart>> {
            override fun onResponse(call: Call<List<Cart>>, response: Response<List<Cart>>) {
                Log.i(TAG, "onResponse: ${response.body()}")
                updateCart(response.body()!!)
            }

            override fun onFailure(call: Call<List<Cart>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })

    }

    fun updateCart(carts: List<Cart>){
        val allCarts = mutableListOf<Prod>()
        carts.forEach { cart:Cart ->
            cart.products.forEach { prod:Prod ->
                allCarts.add(prod)
            }
        }
        adapter.updateCarts(allCarts)
    }
}