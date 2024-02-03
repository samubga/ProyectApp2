package com.example.proyectapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="routine")
data class Routine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("routine_name") val routineName: String
)
