package com.example.heroestrayappretrofitmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroestrayappretrofitmvvm.R
import com.example.heroestrayappretrofitmvvm.adapter.HeroesAdapter
import com.example.heroestrayappretrofitmvvm.databinding.ActivityHeroesBinding
import com.example.heroestrayappretrofitmvvm.viewmodel.HeroesActivityViewModel

class HeroesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesBinding
    lateinit var viewModel: HeroesActivityViewModel
    private lateinit var adapter: HeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = HeroesAdapter(this)

        val modelProvider = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, modelProvider)[HeroesActivityViewModel::class.java]

        viewModel.progressBarRV.observe(this, Observer {
            if (it)
                binding.progressBarRV.visibility = View.VISIBLE
            else
                binding.progressBarRV.visibility = View.GONE
        })

        viewModel.heroesResponse.observe(this, Observer {
            if (it != null) {
                adapter.setHeroList(it)
            }
        })

        binding.apply {
            viewModel.changeStateVM()
            heroesRV.adapter = adapter
            heroesRV.layoutManager = LinearLayoutManager(this@HeroesActivity)
        }
    }
}