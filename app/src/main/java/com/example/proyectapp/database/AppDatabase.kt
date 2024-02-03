package com.example.proyectapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectapp.model.Exercise
import com.example.proyectapp.database.dao.ExerciseDao
import com.example.proyectapp.database.dao.RoutineDao
import com.example.proyectapp.database.dao.RoutineExerciseCrossRefDao
import com.example.proyectapp.model.Routine
import com.example.proyectapp.model.RoutineExerciseCrossRef

@Database(entities = [Exercise::class, Routine::class, RoutineExerciseCrossRef::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        val DATABASE_NAME = "Routine"
    }
    abstract fun exerciseDao(): ExerciseDao
    abstract fun routineDao(): RoutineDao
    abstract fun routineExerciseCrossRefDao(): RoutineExerciseCrossRefDao
}