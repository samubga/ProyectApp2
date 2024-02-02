package com.example.proyectapp.routineActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.proyectapp.allExerciseActivities.AddExerciseActivity
import com.example.proyectapp.allExerciseActivities.ExerciseAdapter
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityCreateRoutineBinding

class CreateRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRoutineBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarBackRoutine)

        binding.buttonAddNewExercise.setOnClickListener{
            val addExerciseIntent = Intent(this, AddExerciseActivity::class.java)
            startActivity(addExerciseIntent)

        }
        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()


        binding.exerciseRecyclerView.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.exerciseRecyclerView.adapter = ExerciseRoutineAdapter(
            db.exerciseDao().list(), this, db
        )


        val dividerItemDecoration = DividerItemDecoration(
            binding.exerciseRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )

        // Agregar el DividerItemDecoration al RecyclerView
        binding.exerciseRecyclerView.addItemDecoration(dividerItemDecoration)
    }
    override fun onResume() {
        super.onResume()

        val adapter = binding.exerciseRecyclerView.adapter as ExerciseRoutineAdapter

        adapter.exercises = db.exerciseDao().list()

        adapter.notifyDataSetChanged()
    }

}