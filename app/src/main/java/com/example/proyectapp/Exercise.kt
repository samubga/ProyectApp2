package com.example.proyectapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("sets") val sets: Int?,
    @ColumnInfo("reps") val reps: Int?,
    @ColumnInfo("muscle") val muscle: String,
    @ColumnInfo("notes") val notes: String

)
