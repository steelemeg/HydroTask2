package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HydroDao {
    @Query("SELECT db_date, SUM(db_ounces) AS db_ounces, id FROM water_table GROUP BY db_date ORDER BY db_date ASC")
    //@Query("SELECT * FROM water_table ORDER BY db_date ASC")
    fun getAll(): Flow<List<WaterEntity>>

    @Query("SELECT db_date, SUM(db_ounces) AS db_ounces, id FROM water_table GROUP BY db_date ORDER BY db_date ASC")
    fun getAllList(): List<WaterEntity>

    @Insert
    fun insertAll(waterList: List<WaterEntity>)

    @Insert
    fun insert(waterEntry: WaterEntity)

    @Query("DELETE FROM water_table")
    fun deleteAll()

    @Query("SELECT * FROM water_table GROUP BY db_date")
    fun getDaily(): Flow<List<WaterEntity>>

    @Query("SELECT COUNT(DISTINCT db_date) FROM water_table")
    fun getDayCount(): Int

    @Query("SELECT SUM(db_ounces) FROM water_table GROUP BY db_date ORDER BY SUM(db_ounces) ASC LIMIT 1")
    fun getMin(): Int

    @Query("SELECT SUM(db_ounces) FROM water_table GROUP BY db_date ORDER BY SUM(db_ounces) DESC LIMIT 1")
    fun getMax(): Int

    @Query("SELECT SUM(db_ounces) FROM water_table")
    fun getOunceTotal(): Int

    @Query("SELECT MIN(db_date) FROM water_table")
    fun getFirstDay(): String

}