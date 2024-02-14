package com.example.proyectapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectapp.model.User

@Dao
interface UserDao {
    @Insert
    fun save(user: User): Long

    @Query("SELECT * FROM user WHERE user_name = :userName AND password = :password")
    fun loginUser(userName: String, password: String): User?

    @Query("SELECT * FROM user WHERE user_name = :userName LIMIT 1")
    fun getUserByUsername(userName: String): User?
}