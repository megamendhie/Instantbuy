package com.mendhie.instantbuy.presentation.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.databinding.ItemProductBinding
import com.mendhie.instantbuy.presentation.activities.ProductActivity
import java.util.*

class ProductsAdapter(): RecyclerView.Adapter<ProductsAdapter.ProductItemViewHolder>() {
    var products = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bindView(products.get(position), position)
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(updatedList: List<Product>){
        products = updatedList
        notifyDataSetChanged()
    }

    inner class ProductItemViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root){
        fun bindView(product:Product, position: Int){
            binding.root.setOnClickListener {
                val intent = Intent(it.context, ProductActivity::class.java)
                //intent.putExtra("CATEGORY", name)
                it.context.startActivity(intent)
            }
            binding.txtName.text = product.title.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault())
                else
                    it.toString()
            }
            binding.txtPrice.text = "\u0024${product.price}"
            Glide.with(binding.root.context)
                .load(product.image)
                .into(binding.imgIcon)
        }
    }
}