package com.example.proyectapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName="routine")
data class Routine(
    @ColumnInfo("exercise") val exercise: Exercise,
)
