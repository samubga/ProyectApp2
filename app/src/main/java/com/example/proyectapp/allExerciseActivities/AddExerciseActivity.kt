package com.example.proyectapp.allExerciseActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.proyectapp.R
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityAddExerciseBinding
import com.example.proyectapp.model.Exercise

class AddExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExerciseBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarBack)
        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        binding.buttonSave.setOnClickListener{
            val name = binding.editTextName.text.toString()
            val sets = binding.editTextSets.text.toString().toIntOrNull()
            val reps = binding.editTextReps.text.toString().toIntOrNull()
            val muscle = binding.editTextMuscle.text.toString()
            val notes = binding.editTextNotes.text.toString()


            val exercise = Exercise(
                name = name,
                sets = sets,
                reps = reps,
                muscle = muscle,
                notes = notes
            )

            db
                .exerciseDao()
                .save(exercise)


            finish()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)
        binding.toolbarBack.setTitle("Añadir ejercicio")
        binding.toolbarBack.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionBarBack -> {
                val secondActivityIntent = Intent(this, SecondActivity::class.java)
                startActivity(secondActivityIntent)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}