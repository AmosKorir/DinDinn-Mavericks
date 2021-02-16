package com.food.viewmodels.modelstate

import com.airbnb.mvrx.MavericksState
import com.food.domain.datamodels.Food

data class MainFoodState(val food:List<Food> = ArrayList()):MavericksState
