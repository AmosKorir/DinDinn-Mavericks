package com.food.data.responses

import com.google.gson.annotations.SerializedName

data class PromotionResponse(
    @SerializedName("image")
    val image: String,
)