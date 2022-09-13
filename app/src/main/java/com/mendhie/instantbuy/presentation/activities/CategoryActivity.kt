package com.mendhie.instantbuy.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mendhie.instantbuy.R
import com.mendhie.instantbuy.data.models.Product
import com.mendhie.instantbuy.databinding.ActivityCategoryBinding
import com.mendhie.instantbuy.domain.viewmodels.ProductViewModel
import com.mendhie.instantbuy.presentation.adapters.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var adapter: ProductsAdapter
    private val _tag = "CategoryAct"
    private val viewModel: ProductViewModel by viewModels()

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

    private fun getProducts(){
        val category: String = intent.getStringExtra("CATEGORY").toString()
        Log.i(_tag, "getProducts: category- $category")
        viewModel.getProducts(category).observe(this, {products -> updateProducts(products)})
    }

    private fun updateProducts(products: List<Product>){
        adapter.updateProducts(products)
    }

    private fun setIconImage(name: String){
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