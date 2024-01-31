package com.example.proyectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.proyectapp.databinding.ActivityMainBinding
import com.example.proyectapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    enum class Params {
        ExerciseName,
        ExerciseSets,
        ExerciseReps
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val exerciseName = intent.getStringExtra(Params.ExerciseName.name)
        val exerciseSets = intent.getIntExtra(Params.ExerciseSets.name, 0)
        val exerciseReps = intent.getIntExtra(Params.ExerciseReps.name, 0)

        binding.txtNameExercise.text = "Nombre: $exerciseName"
        binding.txtSetsExercise.setText("Sets: $exerciseSets")
        binding.txtRepsExercise.setText("Reps: $exerciseReps")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuAdd -> {
                val addExerciseActivityIntent = Intent(this, AddExerciseActivity::class.java)
                startActivity(addExerciseActivityIntent)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}