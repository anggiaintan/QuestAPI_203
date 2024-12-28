package com.example.api

import android.app.Application
import com.example.api.ui.AppContainer
import com.example.api.ui.MahasiswaContainer

class MahasiswaApplications : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}