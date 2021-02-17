package com.food.data.repositories

import com.food.domain.datamodels.Food
import com.food.domain.repositories.FoodRepository
import io.reactivex.Single

class FoodApiRepository : FoodRepository {
    override fun getFood(): Single<List<Food>> {
        val food = Food(
            "Test",
            20.0,
            "https://st2.depositphotos.com/1001069/8071/i/950/depositphotos_80712882-stock-photo-italian-pizza-with-pepperoni.jpg",
            "this pizza "
        )
        return Single.just(listOf(food, food, food, food, food))
    }
}