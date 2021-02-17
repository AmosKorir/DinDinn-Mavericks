package com.food.domain.repositories

import com.food.domain.datamodels.Food
import com.food.domain.datamodels.Promotion
import io.reactivex.Single

interface FoodRepository {
    fun getFood(): Single<List<Food>>

    fun getPromotion(): Single<List<Promotion>>
}