package com.example.proyectapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.proyectapp.model.Exercise
import com.example.proyectapp.model.RoutineExerciseCrossRef

@Dao
interface RoutineExerciseCrossRefDao {
    @Insert
    fun save(routineExerciseCrossRef: RoutineExerciseCrossRef)

    @Insert
    fun saveAll(routineExerciseCrossRefs: List<RoutineExerciseCrossRef>)

    @Transaction
    @Query("DELETE FROM routine_exercise_cross_ref WHERE routineId = :routineId")
    fun deleteRoutineExercises(routineId: Int)

    @Transaction
    fun updateRoutineExercises(routineId: Int, updatedExercises: List<Exercise>) {
        // Eliminar las entradas existentes de la rutina en la tabla de relación
        deleteRoutineExercises(routineId)
        // Insertar las nuevas entradas de la rutina con la lista actualizada de ejercicios
        val routineExerciseCrossRefs = updatedExercises.map { exercise ->
            RoutineExerciseCrossRef(routineId.toLong(), exercise.id)
        }
        saveAll(routineExerciseCrossRefs)


    }
}