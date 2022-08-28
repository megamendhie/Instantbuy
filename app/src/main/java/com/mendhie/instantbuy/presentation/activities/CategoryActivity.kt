package com.mendhie.instantbuy.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.mendhie.instantbuy.R
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.data.remote.StoreApi
import com.mendhie.instantbuy.databinding.ActivityCategoryBinding
import com.mendhie.instantbuy.presentation.adapters.ProductsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var adapter: ProductsAdapter
    private val TAG = "CategoryAct"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        val actionBar = supportActionBar
        val category: String = intent.getStringExtra("CATEGORY").toString()
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            binding.txtActionBar.text = category.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault())
                else
                    it.toString()
            }
        }
        setIconImage(category)

        adapter = ProductsAdapter()
        binding.lstProduct.layoutManager = GridLayoutManager(this, 2)
        binding.lstProduct.adapter = adapter
        getProducts()
    }


    fun getProducts(){
        var category: String = intent.getStringExtra("CATEGORY").toString()
        Log.i(TAG, "getProducts: category- $category")
        StoreApi.storeApi.getProducts(category).enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.i(TAG, "onResponse: ${response.body()}")
                updateProducts(response.body()!!)
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
        category = category.replace(" ", "%20")
        Log.i(TAG, "getProducts: category- $category")
    }

    fun updateProducts(products: List<Product>){
        adapter.updateProducts(products)
    }

    fun setIconImage(name: String){
        when(name){
            "electronics" -> binding.imgHome.setImageResource(R.drawable.px_electronics)
            "jewelery" -> binding.imgHome.setImageResource(R.drawable.px_jewelry)
            "men's clothing" -> binding.imgHome.setImageResource(R.drawable.px_men_clothing)
            "women's clothing" -> binding.imgHome.setImageResource(R.drawable.px_women_clothing)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}