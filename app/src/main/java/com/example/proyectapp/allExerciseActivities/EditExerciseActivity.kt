package com.example.proyectapp.allExerciseActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.proyectapp.R
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityEditExerciseBinding
import com.example.proyectapp.model.Exercise

class EditExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditExerciseBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarBack)

        val exerciseId = intent.getIntExtra("exercise_id", -1)
        Log.d("ID", "Es $exerciseId")

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        if (exerciseId != -1) {
            // Obtener el ejercicio correspondiente al ID desde la base de datos
            val exercise = db.exerciseDao().getExerciseById(exerciseId)

            // Verificar si el ejercicio no es nulo
            if (exercise != null) {
                // Aquí puedes establecer los datos del ejercicio en los campos de texto para edición
                binding.editTextName.setText(exercise.name)
                binding.editTextSets.setText(exercise.sets?.toString() ?: "")
                binding.editTextReps.setText(exercise.reps?.toString() ?: "")
                binding.editTextMuscle.setText(exercise.muscle)
                binding.editTextNotes.setText(exercise.notes)
            } else {

            }
        } else {

        }

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val sets = binding.editTextSets.text.toString().toIntOrNull()
            val reps = binding.editTextReps.text.toString().toIntOrNull()
            val muscle = binding.editTextMuscle.text.toString()
            val notes = binding.editTextNotes.text.toString()


            if (name.isNotBlank() && sets != null && reps != null && muscle.isNotBlank() && notes.isNotBlank()) {
                val updatedExercise = Exercise(exerciseId, name, sets, reps, muscle, notes)


                db.exerciseDao().update(updatedExercise)


                Toast.makeText(this, "Ejercicio actualizado correctamente", Toast.LENGTH_SHORT).show()


                finish()
            } else {

                Toast.makeText(this, "Por favor, introduce valores válidos en todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)
        binding.toolbarBack.setTitle("Edittar ejercicio")
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