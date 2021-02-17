package com.food.data.repositories

import com.food.domain.datamodels.Food
import com.food.domain.repositories.FoodRepository
import io.reactivex.Single

class FoodApiRepository: FoodRepository {
    override fun getFood(): Single<List<Food>> {
        val food=Food("Test",20.0)
        return Single.just( listOf(food,food,food,food,food))
    }
}