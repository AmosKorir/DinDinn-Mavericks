package com.food.viewmodels

import com.airbnb.mvrx.MavericksViewModel
import com.food.data.repositories.FoodApiRepository
import com.food.viewmodels.modelstate.MainFoodState

class FoodViewModel(mainFoodState: MainFoodState) :
    MavericksViewModel<MainFoodState>(mainFoodState) {

    private val foodRepository = FoodApiRepository()

    fun getFood() {
        setState {
            copy(food = foodRepository.getFood())
        }
    }
}