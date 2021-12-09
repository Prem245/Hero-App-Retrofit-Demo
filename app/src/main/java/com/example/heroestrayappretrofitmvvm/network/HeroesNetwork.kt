package com.example.heroestrayappretrofitmvvm.network

import com.example.heroestrayappretrofitmvvm.network.model.HeroesModel
import retrofit2.Call

import retrofit2.http.GET

const val BASE_URL = "https://www.simplifiedcoding.net/demos/"

interface HeroesNetwork {

    @GET("marvel")
    fun getAllHeroesData() : Call<MutableList<HeroesModel>>
}