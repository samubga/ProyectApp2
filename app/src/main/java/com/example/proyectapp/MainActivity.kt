package com.example.proyectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.proyectapp.allExerciseActivities.AddExerciseActivity
import com.example.proyectapp.allExerciseActivities.SecondActivity
import com.example.proyectapp.databinding.ActivityMainBinding
import com.example.proyectapp.routineActivities.RoutineActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        binding.buttonAdd.setOnClickListener {
            val routineIntent = Intent(this, RoutineActivity::class.java)
            startActivity(routineIntent)


        }

        binding.textNext.setOnClickListener {
            val exerciseIntent = Intent(this, SecondActivity::class.java)
            startActivity(exerciseIntent)
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
            else -> {
                return super.onOptionsItemSelected(item)
            }

        }

    }
}