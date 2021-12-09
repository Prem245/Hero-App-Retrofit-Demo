package com.example.heroestrayappretrofitmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.heroestrayappretrofitmvvm.network.model.HeroesModel
import com.example.heroestrayappretrofitmvvm.repository.HeroesActivityRepository

class HeroesActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HeroesActivityRepository(application)
    val progressBarRV : LiveData<Boolean>
    val heroesResponse : LiveData<MutableList<HeroesModel>?>

    init {
        this.progressBarRV = repository.progressBarRV
        this.heroesResponse = repository.heroesResponse
    }

    fun changeStateVM(){
        repository.changeState()
    }

}