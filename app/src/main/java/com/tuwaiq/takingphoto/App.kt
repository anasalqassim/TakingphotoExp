package com.tuwaiq.takingphoto

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Repo.initialize(this)
    }
}