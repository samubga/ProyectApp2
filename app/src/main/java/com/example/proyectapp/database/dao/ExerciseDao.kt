package com.example.proyectapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectapp.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun list(): List<Exercise>

    @Insert
    fun save(exercise: Exercise)
}