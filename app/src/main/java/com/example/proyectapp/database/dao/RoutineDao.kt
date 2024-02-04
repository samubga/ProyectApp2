package com.example.proyectapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectapp.model.Routine

@Dao
interface RoutineDao {
    @Insert
    fun save(routine: Routine): Long

    @Query("SELECT * FROM routine")
    fun list(): List<Routine>

    @Query("DELETE FROM routine WHERE id=:id")
    fun delete(id: Int): Int

    @Query("SELECT routine_name FROM routine WHERE id=:id")
    fun getRoutineNameById(id: Int): String?
}
