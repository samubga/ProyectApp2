package com.example.proyectapp

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.proyectapp.allExerciseActivities.AddExerciseActivity
import com.example.proyectapp.allExerciseActivities.SecondActivity
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityMainBinding
import com.example.proyectapp.login.LoginActivity
import com.example.proyectapp.model.Exercise
import com.example.proyectapp.model.User
import com.example.proyectapp.routineActivities.RoutineActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        //Subrayar texto
        //binding.textNext.paintFlags = binding.textNext.paintFlags or Paint.UNDERLINE_TEXT_FLAG


        binding.cardViewExercises.setOnClickListener {
            val exerciseIntent = Intent(this, SecondActivity::class.java)
            startActivity(exerciseIntent)
        }

        binding.cardViewRoutines.setOnClickListener {
            val routineIntent = Intent(this, RoutineActivity::class.java)
            startActivity(routineIntent)
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        binding.toolbar2.setTitle("Inicio")
        binding.toolbar2.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuAdd -> {
                val addExerciseActivityIntent = Intent(this, AddExerciseActivity::class.java)
                startActivity(addExerciseActivityIntent)
                true
            }
            R.id.menuHome ->{
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                startActivity(mainActivityIntent)
                true
            }
            R.id.menuRoutines->{
                val routineActivityIntent = Intent(this, RoutineActivity::class.java)
                startActivity(routineActivityIntent)
                true
            }
            R.id.menuLogOut->{
                User.currentUserID = -1
                val loginActivityIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginActivityIntent)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }

        }

    }





}