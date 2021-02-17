package com.food.data.api

import com.food.data.responses.FoodResponse
import com.food.data.responses.PromotionResponse
import io.reactivex.Single
import retrofit2.http.GET

interface FoodApi {
    @GET("food")
    fun getFood(): Single<List<FoodResponse>>

    @GET("promotion")
    fun getPromotion(): Single<List<PromotionResponse>>
}