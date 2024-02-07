package com.example.proyectapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName="exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("sets") val sets: Int?,
    @ColumnInfo("reps") val reps: Int?,
    @ColumnInfo("muscle") val muscle: String,

    @ColumnInfo("notes") val notes: String

){
    @Ignore
    constructor(name: String, sets: Int?, reps: Int?, muscle: String, notes: String) :
            this(0, name, sets, reps, muscle, notes)
}
