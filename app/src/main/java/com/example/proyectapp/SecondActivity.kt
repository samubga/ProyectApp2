package com.example.proyectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val exerciseName = intent.getStringExtra(Params.ExerciseName.name)
        val exerciseSets = intent.getIntExtra(Params.ExerciseSets.name, 0)
        val exerciseReps = intent.getIntExtra(Params.ExerciseReps.name, 0)

        binding.txtNameExercise.text = "Nombre: $exerciseName"
        binding.txtSetsExercise.setText("Sets: $exerciseSets")
        binding.txtRepsExercise.setText("Reps: $exerciseReps")

    }
}