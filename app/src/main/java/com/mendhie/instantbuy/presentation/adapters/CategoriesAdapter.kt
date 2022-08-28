package com.mendhie.instantbuy.presentation.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mendhie.instantbuy.R
import com.mendhie.instantbuy.databinding.ItemCategoryBinding
import com.mendhie.instantbuy.presentation.activities.CategoryActivity
import java.util.*

class CategoriesAdapter(): RecyclerView.Adapter<CategoriesAdapter.CategoryItemViewHolder>() {
    var categories = arrayOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bindView(categories[position], position)
    }

    override fun getItemCount(): Int = categories.size

    fun updateCategories(updatedList: Array<String>){
        categories = updatedList
        notifyDataSetChanged()
    }

    inner class CategoryItemViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bindView(name: String, position: Int){
            binding.root.setOnClickListener {
                val intent = Intent(it.context, CategoryActivity::class.java)
                intent.putExtra("CATEGORY", name)
                it.context.startActivity(intent)
            }
            setBgColor(position)
            setIconImage(name)
            binding.txtName.text = name.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault())
                else
                    it.toString()
            }
        }

        fun setIconImage(name: String){
            when(name){
                "electronics" -> binding.imgIcon.setImageResource(R.drawable.px_electronics)
                "jewelery" -> binding.imgIcon.setImageResource(R.drawable.px_jewelry)
                "men's clothing" -> binding.imgIcon.setImageResource(R.drawable.px_men_clothing)
                "women's clothing" -> binding.imgIcon.setImageResource(R.drawable.px_women_clothing)
            }
        }

        fun setBgColor(position: Int){
            when (position) {
                0 -> binding.root.setBackgroundResource(R.drawable.bg_category_5)
                1 -> binding.root.setBackgroundResource(R.drawable.bg_category_2)
                2 -> binding.root.setBackgroundResource(R.drawable.bg_category_3)
                3 -> binding.root.setBackgroundResource(R.drawable.bg_category_4)
                4 -> binding.root.setBackgroundResource(R.drawable.bg_category_1)
                5 -> binding.root.setBackgroundResource(R.drawable.bg_category_6)
                6 -> binding.root.setBackgroundResource(R.drawable.bg_category_7)
                else -> binding.root.setBackgroundResource(R.drawable.bg_category_8)
            }
        }
    }
}