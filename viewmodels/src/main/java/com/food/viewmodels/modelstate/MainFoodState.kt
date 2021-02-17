package com.food.viewmodels.modelstate

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.food.domain.datamodels.Food

data class MainFoodState(
    val food: Async<List<Food>> = Uninitialized,
    val cartsFood: List<Food> = ArrayList()
) : MavericksState {
    val cartTotalPrice = cartsFood.map { it.price }.sum()
}
