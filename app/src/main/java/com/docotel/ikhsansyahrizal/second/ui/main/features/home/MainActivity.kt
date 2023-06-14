package com.docotel.ikhsansyahrizal.second.ui.main.features.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.docotel.ikhsansyahrizal.second.R
import com.docotel.ikhsansyahrizal.second.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}