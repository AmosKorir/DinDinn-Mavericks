package com.food.viewmodels.modelstate

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.food.domain.datamodels.Food
import com.food.domain.datamodels.Promotion

data class MainFoodState(
    val food: Async<List<Food>> = Uninitialized,
    val cartsFood: List<Food> = ArrayList(),
    val promotion: Async<List<Promotion>> = Uninitialized
) : MavericksState {
    val cartTotalPrice = cartsFood.map { it.price }.sum()
}
