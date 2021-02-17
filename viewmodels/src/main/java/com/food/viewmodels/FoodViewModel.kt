package com.food.viewmodels

import com.airbnb.mvrx.BaseMvRxViewModel
import com.food.data.repositories.FoodApiRepository
import com.food.domain.datamodels.Food
import com.food.viewmodels.modelstate.MainFoodState
import io.reactivex.Single

class FoodViewModel(private val mainFoodState: MainFoodState) :
    BaseMvRxViewModel<MainFoodState>(mainFoodState) {

    private val foodRepository = FoodApiRepository()

    fun getFood() {
        foodRepository.getFood().execute {
            copy(food = it)
        }
    }

    fun addToCart(food: Food) {
        withState { state ->
            Single.just(food)
                .execute {
                    copy(cartsFood =
                    state.cartsFood.toMutableList().apply {
                        add(food)
                    })
                }

        }

    }

    fun removeFoodFromCart(food: Food) {
        withState { state ->
            Single.just(food)
                .execute {
                    copy(cartsFood = state.cartsFood.toMutableList().apply {
                        remove(food)
                    })
                }
        }
    }
}