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
import com.example.proyectapp.MainActivity
import com.example.proyectapp.R
import com.example.proyectapp.allExerciseActivities.AddExerciseActivity
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityRoutineBinding

class RoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoutineBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarRoutine)

        binding.buttonAddRoutine.setOnClickListener{
            val createRoutineIntent = Intent(this, CreateRoutineActivity::class.java)
            startActivity(createRoutineIntent)
        }

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()


        binding.recyclerViewRoutines.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.recyclerViewRoutines.adapter = RoutineAdapter(
            db.routineDao().list(),this, db
        )




        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewRoutines.context,
            LinearLayoutManager.VERTICAL
        )

        // Agregar el DividerItemDecoration al RecyclerView
        binding.recyclerViewRoutines.addItemDecoration(dividerItemDecoration)
    }
    override fun onResume() {
        super.onResume()

        val adapter = binding.recyclerViewRoutines.adapter as RoutineAdapter

        adapter.routines = db.routineDao().list()

        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        binding.toolbarRoutine.setTitle("Rutinas")
        binding.toolbarRoutine.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

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
            else -> {
                return super.onOptionsItemSelected(item)
            }

        }

    }
}