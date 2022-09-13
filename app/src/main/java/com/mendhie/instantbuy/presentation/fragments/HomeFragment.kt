package com.mendhie.instantbuy.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mendhie.instantbuy.data.models.Category
import com.mendhie.instantbuy.databinding.FragmentHomeBinding
import com.mendhie.instantbuy.domain.viewmodels.ProductViewModel
import com.mendhie.instantbuy.presentation.adapters.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val _tag = "HomeFrag"
    lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CategoriesAdapter
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(_tag, "onCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoriesAdapter()
        binding.lstCategory.layoutManager = GridLayoutManager(context, 2)
        binding.lstCategory.adapter = adapter

        viewModel.getCategories().observe(this, {categories ->
            updateCategories(categories)
        })
    }

    private fun updateCategories(categories: List<Category>) {
        adapter.updateCategories(categories)
    }
}