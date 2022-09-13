package com.mendhie.instantbuy.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mendhie.instantbuy.data.models.CategoryResponse
import com.mendhie.instantbuy.domain.repositories.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val repo: StoreRepository): ViewModel() {

    fun getCategories() = repo.getCategories()

    fun getProducts(category: String) = repo.getProducts(category)
}