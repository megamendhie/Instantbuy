package com.mendhie.instantbuy.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mendhie.instantbuy.databinding.FragmentHomeBinding
import com.mendhie.instantbuy.presentation.adapters.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoriesAdapter()
        binding.lstCategory.layoutManager = GridLayoutManager(context, 2)
        binding.lstCategory.adapter = adapter
        adapter.updateCategories(arrayOf("electronics", "jewelery", "men's clothing", "women's clothing"))

    }
}