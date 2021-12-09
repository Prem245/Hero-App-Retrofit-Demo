package com.example.heroestrayappretrofitmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.heroestrayappretrofitmvvm.R
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            delay(2000L)
            Intent(this@MainActivity, HeroesActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}