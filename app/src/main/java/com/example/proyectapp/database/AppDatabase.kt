package com.example.proyectapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectapp.model.Exercise
import com.example.proyectapp.database.dao.ExerciseDao

@Database(entities = [Exercise::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        val DATABASE_NAME = "Routine"
    }
    abstract fun exerciseDao(): ExerciseDao
}