package com.food.data.repositories

import com.food.domain.datamodels.Food
import com.food.domain.repositories.FoodRepository

class FoodApiRepository: FoodRepository {
    override fun getFood(): List<Food> {
        val food=Food("Test",20.0)
        return listOf(food,food,food,food,food)
    }
}