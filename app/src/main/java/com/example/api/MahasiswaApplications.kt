package com.example.api

import android.app.Application
import com.example.api.dependenciesinjection.AppContainer
import com.example.api.dependenciesinjection.MahasiswaContainer

class MahasiswaApplications : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}