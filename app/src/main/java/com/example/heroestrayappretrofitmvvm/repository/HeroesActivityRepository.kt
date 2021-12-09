package com.example.heroestrayappretrofitmvvm.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.heroestrayappretrofitmvvm.network.Common
import com.example.heroestrayappretrofitmvvm.network.HeroesNetwork
import com.example.heroestrayappretrofitmvvm.network.model.HeroesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroesActivityRepository(val application: Application) {

    private val TAG = "HeroRepo"
    private val heroesApi: HeroesNetwork = Common.heroesApi

    val progressBarRV: MutableLiveData<Boolean> = MutableLiveData()
    val heroesResponse: MutableLiveData<MutableList<HeroesModel>?>
        get() {
            val data: MutableLiveData<MutableList<HeroesModel>?> =
                MutableLiveData<MutableList<HeroesModel>?>()
            heroesApi.getAllHeroesData().enqueue(object : Callback<MutableList<HeroesModel>> {
                override fun onResponse(
                    call: Call<MutableList<HeroesModel>>,
                    response: Response<MutableList<HeroesModel>>
                ) {
                    progressBarRV.value = false
                    Log.d(TAG, "getAllHeroes: Successful")
                    if (response.isSuccessful) {
                        data.value = response.body()!!
                    } else {
                        data.value = null
                    }
                }

                override fun onFailure(call: Call<MutableList<HeroesModel>>, t: Throwable) {
                    progressBarRV.value = false
                    Log.d(TAG, "getAllHeroes: Failed loading!!! ${t.message}")
                    Toast.makeText(application, "HeroRepo : Failed", Toast.LENGTH_SHORT).show()
                }
            })
            return data
        }

    fun changeState() {
        progressBarRV.value = !(progressBarRV.value != null && progressBarRV.value!!)
    }

    /* fun getAllHeroes() {
        progressBarRV.value = true
        // Network Call

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(HeroesNetwork::class.java)
        service.getAllHeroesData().enqueue(object : Callback<MutableList<HeroesModel>> {
            override fun onResponse(
                call: Call<MutableList<HeroesModel>>,
                response: Response<MutableList<HeroesModel>>
            ) {
                progressBarRV.value = false
                Log.d(TAG, "getAllHeroes: Successful")
                heroesResponse.value = response.body()!!
            }

            override fun onFailure(call: Call<MutableList<HeroesModel>>, t: Throwable) {
                progressBarRV.value = false
                Log.d(TAG, "getAllHeroes: Failed loading!!!")
                Toast.makeText(application, "HeroRepo : Failed", Toast.LENGTH_SHORT).show()
            }

        }) */

    /* if (service.isSuccessful && service.body() != null){
        progressBarRV.value = false
        Log.d(TAG, "getAllHeroes: Successful")
        heroesResponse.value = service.body()!!
    } else {
        progressBarRV.value = false
        Log.d(TAG, "getAllHeroes: Failed loading!!!")
        Toast.makeText(application, "HeroRepo : Failed", Toast.LENGTH_SHORT).show()
    } */
}
