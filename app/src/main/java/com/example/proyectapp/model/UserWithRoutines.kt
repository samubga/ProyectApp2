package com.example.proyectapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithRoutines(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val routines: List<Routine>
)
