package com.food.data.repositories

import com.food.data.api.FoodApi
import com.food.data.mapper.FoodMapper
import com.food.domain.datamodels.Food
import com.food.domain.datamodels.Promotion
import com.food.domain.repositories.FoodRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FoodApiRepository(private val foodApi: FoodApi) : FoodRepository {
    override fun getFood(): Single<List<Food>> {
        return foodApi.getFood()
            .subscribeOn(Schedulers.io())
            .flatMapPublisher { Flowable.fromIterable(it) }
            .map {FoodMapper.transform(it)  }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun getPromotion(): Single<List<Promotion>> {
        return foodApi.getPromotion()
            .subscribeOn(Schedulers.io())
            .flatMapPublisher { Flowable.fromIterable(it) }
            .map {FoodMapper.transform(it)  }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
    }
}