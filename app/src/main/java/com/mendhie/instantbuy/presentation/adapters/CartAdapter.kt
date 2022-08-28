package com.mendhie.instantbuy.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mendhie.instantbuy.data.models.Cart
import com.mendhie.instantbuy.data.models.Prod
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.data.remote.StoreApi
import com.mendhie.instantbuy.databinding.ItemCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class CartAdapter(): RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {
    private val TAG = "CartAdapt"
    var carts = listOf<Prod>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bindView(carts.get(position), position)
    }

    override fun getItemCount(): Int = carts.size

    fun updateCarts(updatedList: List<Prod>){
        carts = updatedList
        notifyDataSetChanged()
    }

    inner class CartItemViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root){
        var price = 0.0

        fun bindView(cart: Prod, position: Int){
            binding.txtQuantity.text = cart.quantity.toString()
            getProductDetail(cart.productId, cart.quantity)

            binding.imgAdd.setOnClickListener{
                cart.quantity += 1
                val amount = cart.quantity* price
                binding.txtQuantity.text = cart.quantity.toString()
                binding.txtPrice.text = String.format("\$%.2f", amount)

            }

            binding.imgRemove.setOnClickListener{
                cart.quantity -= 1
                val amount = cart.quantity* price
                binding.txtQuantity.text = cart.quantity.toString()
                binding.txtPrice.text = String.format("\$%.2f", amount)

            }
        }

        fun getProductDetail(id: Int, quantity: Int){
            StoreApi.storeApi.getProduct(id.toString()).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    Log.i(TAG, "onResponse: ${response.body()}")
                    val product = response.body()
                    price = product!!.price
                    val amount = quantity* price
                    binding.txtName.text = product.title
                    binding.txtPrice.text = String.format("\$%.2f", amount)
                    Glide.with(binding.root.context)
                        .load(product?.image)
                        .into(binding.imgIcon)
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.localizedMessage}")
                }
            })
        }
    }
}