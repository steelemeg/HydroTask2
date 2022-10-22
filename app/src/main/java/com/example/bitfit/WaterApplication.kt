package com.example.bitfit

import android.app.Application
import androidx.room.Room

class WaterApplication : Application() {
    val database: WaterDatabase by lazy {
        Room.databaseBuilder(
            this,
            WaterDatabase::class.java,
            "database.db"
        ).allowMainThreadQueries().build()
    }
}