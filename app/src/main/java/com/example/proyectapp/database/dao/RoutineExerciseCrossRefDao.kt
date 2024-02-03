package com.example.proyectapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.proyectapp.model.RoutineExerciseCrossRef

@Dao
interface RoutineExerciseCrossRefDao {
    @Insert
    fun save(routineExerciseCrossRef: RoutineExerciseCrossRef)

    @Insert
    fun saveAll(routineExerciseCrossRefs: List<RoutineExerciseCrossRef>)
}