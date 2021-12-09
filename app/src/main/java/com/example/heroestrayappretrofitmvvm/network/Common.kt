package com.example.heroestrayappretrofitmvvm.network

object Common {

    val heroesApi : HeroesNetwork
    get() = Retrofit.getRetrofitClient(BASE_URL).create(HeroesNetwork::class.java)

}