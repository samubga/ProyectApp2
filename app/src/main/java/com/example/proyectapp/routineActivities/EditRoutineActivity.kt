package com.example.proyectapp.routineActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.proyectapp.R
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityEditRoutineBinding

class EditRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRoutineBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarEditBackRoutine)

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        val routineId = intent.getIntExtra("routine_id", -1)
        val exercisesForRoutine = db.exerciseDao()
            .getExercisesForRoutine(routineId)
            .map { e -> Pair(e.id, e) }
            .toMap()
        val nameRoutine = db.routineDao().getRoutineNameById(routineId)

        binding.editTextRoutineName.setText(nameRoutine)

        binding.rvEditRoutine.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.rvEditRoutine.adapter = EditRoutineAdapter(
            exercisesForRoutine, db.exerciseDao().list(),this
        )

        binding.buttonSaveChanges.setOnClickListener {
            val routineName = binding.editTextRoutineName.text.toString()
            val adapter = binding.rvEditRoutine.adapter as EditRoutineAdapter

            val updatedExercises = adapter.getUpdatedExerciseList()
            db.routineExerciseCrossRefDao().updateRoutineExercises(routineId, updatedExercises)
            db.routineDao().updateRoutineName(routineId, routineName)

            Toast.makeText(this, "Rutina actualizada correctamente", Toast.LENGTH_SHORT).show()

            val routineIntent = Intent(this, RoutineActivity::class.java)
            startActivity(routineIntent)
        }

    }



    override fun onResume() {
        super.onResume()

        val adapter = binding.rvEditRoutine.adapter as EditRoutineAdapter

        adapter.allExercises = db.exerciseDao().list()

        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)
        binding.toolbarEditBackRoutine.setTitle("Editar rutina")
        binding.toolbarEditBackRoutine.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionBarBack -> {
                val routineIntent = Intent(this, RoutineActivity::class.java)
                startActivity(routineIntent)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}