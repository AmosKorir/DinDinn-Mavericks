package com.food.domain.repositories

import com.food.domain.datamodels.Food
import io.reactivex.Single

interface FoodRepository {
    fun getFood(): Single<List<Food>>
}