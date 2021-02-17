package com.food.data.api

import com.food.data.di.ApiService
import com.food.data.repositories.FoodApiRepository

class DataSource {
    companion object {
        private val apiService = ApiService.getFoodApi()
        val repository = FoodApiRepository(apiService)
    }
}