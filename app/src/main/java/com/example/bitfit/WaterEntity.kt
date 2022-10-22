package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "water_table")
data class WaterEntity(
    @ColumnInfo(name = "db_date") var dbDate: String,
    @ColumnInfo(name = "db_ounces") var dbOunces: Int,
    @PrimaryKey(autoGenerate = true) var id: Int =0,
)
