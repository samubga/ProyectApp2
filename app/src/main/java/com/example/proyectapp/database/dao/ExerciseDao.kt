package com.example.proyectapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyectapp.model.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun list(): List<Exercise>

    @Query("SELECT * FROM exercise WHERE id =:id")
    fun getExerciseById(id: Int): Exercise?

    @Query("SELECT * FROM exercise WHERE id IN (SELECT exerciseId FROM routine_exercise_cross_ref WHERE routineId=:routineId)")
    fun getExercisesForRoutine(routineId: Int): List<Exercise>


    @Insert
    fun save(exercise: Exercise)

    @Query("DELETE FROM exercise WHERE id=:id")
    fun delete(id: Int): Int

    @Update
    fun update(exercise: Exercise)




}