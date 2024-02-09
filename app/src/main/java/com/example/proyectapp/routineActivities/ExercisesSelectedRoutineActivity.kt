package com.example.proyectapp.routineActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.proyectapp.R
import com.example.proyectapp.allExerciseActivities.ExerciseAdapter
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityExercisesSelectedRoutineBinding

class ExercisesSelectedRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExercisesSelectedRoutineBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercisesSelectedRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar3)
        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()


        binding.rvSelectedExercises.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        val routineId = intent.getIntExtra("routine_id", -1)
        val exercisesForRoutine = db.exerciseDao().getExercisesForRoutine(routineId)
        binding.rvSelectedExercises.adapter = ExercisesOnRoutineAdapter(
            exercisesForRoutine, this, db
        )

        val dividerItemDecoration = DividerItemDecoration(
                binding.rvSelectedExercises.context,
        LinearLayoutManager.VERTICAL
        )

        // Agregar el DividerItemDecoration al RecyclerView
        binding.rvSelectedExercises.addItemDecoration(dividerItemDecoration)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)

        val routineId = intent.getIntExtra("routine_id", -1)
        val nameRoutine = db.routineDao().getRoutineNameById(routineId)
        binding.toolbar3.setTitle("$nameRoutine")
        binding.toolbar3.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionBarBack -> {
                val routineActivityIntent = Intent(this, RoutineActivity::class.java)
                startActivity(routineActivityIntent)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}