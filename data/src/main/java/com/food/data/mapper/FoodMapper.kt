package com.food.data.mapper

import com.food.data.responses.FoodResponse
import com.food.data.responses.PromotionResponse
import com.food.domain.datamodels.Food
import com.food.domain.datamodels.Promotion

class FoodMapper {
    companion object {
        fun transform(foodResponse: FoodResponse): Food {
            return Food(
                foodResponse.id,
                foodResponse.name,
                foodResponse.price.toDouble(),
                foodResponse.image,
                foodResponse.description
            )
        }

        fun transform(promotionResponse: PromotionResponse):Promotion{
            return Promotion(promotionResponse.image)
        }
    }
}