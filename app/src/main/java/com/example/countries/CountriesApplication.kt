package com.example.countries

import android.app.Application
import com.example.countries.data.Container
import com.example.countries.data.DefaultContainer

class CountriesApplication:Application() {
    lateinit var container: Container
    override fun onCreate() {
        super.onCreate()
        container=DefaultContainer(this)
    }
}