package com.food.domain.repositories

import com.food.domain.datamodels.Food

interface FoodRepository {
    fun getFood():List<Food>
}