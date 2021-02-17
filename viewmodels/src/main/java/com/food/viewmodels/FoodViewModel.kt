package com.food.viewmodels

import com.airbnb.mvrx.BaseMvRxViewModel
import com.food.data.api.DataSource
import com.food.domain.datamodels.Food
import com.food.viewmodels.modelstate.MainFoodState
import io.reactivex.Single

class FoodViewModel(
    private val mainFoodState: MainFoodState,
) : BaseMvRxViewModel<MainFoodState>(mainFoodState) {

    private val foodRepository = DataSource.repository

    init {
        getFood()
        getPromotion()
    }
    fun getFood() {
        foodRepository.getFood().execute {
            copy(food = it)
        }
    }

    fun getPromotion(){
        foodRepository.getPromotion().execute {
            copy(promotion = it)
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