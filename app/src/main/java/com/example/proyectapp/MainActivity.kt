package com.example.proyectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val name = binding.txtName.text.toString()
            val sets = binding.txtSets.text.toString().toIntOrNull()
            val reps = binding.txtReps.text.toString().toIntOrNull()
            if(sets != null && reps != null && name != ""){
                val exercise = Exercise(name,sets,reps)
                Toast.makeText(this,name + " añadido como favorito", Toast.LENGTH_SHORT).show()

                val exerciseIntent = Intent(this, SecondActivity::class.java)
                exerciseIntent.putExtra(SecondActivity.Params.ExerciseName.name,
                    name)
                exerciseIntent.putExtra(SecondActivity.Params.ExerciseSets.name,
                    sets)
                exerciseIntent.putExtra(SecondActivity.Params.ExerciseReps.name,
                    reps)


                startActivity(exerciseIntent)
            }
            else{
                Toast.makeText(this,"Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }

        }
    }
}