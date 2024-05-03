package com.example.myapplication

import android.os.Bundle
import android.provider.Settings.Global
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication22.R
import com.example.myapplication22.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl("https://api.ipify.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(Service::class.java)

        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val data = withContext(Dispatchers.IO) {
                    return@withContext service.getdate()
                }
                binding.ip.text = data.ip
            }
        }
    }
}