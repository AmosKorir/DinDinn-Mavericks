package com.food.data.di

import com.food.data.repositories.FoodApiRepository
import com.food.domain.repositories.FoodRepository
import org.koin.dsl.module

class RepositoryModule {
    companion object{
        val repositoryModule= module {
           single { FoodApiRepository(get()) as FoodRepository }
        }
    }
}