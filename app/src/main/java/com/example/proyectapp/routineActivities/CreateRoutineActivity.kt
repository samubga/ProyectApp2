package com.example.proyectapp.routineActivities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.proyectapp.R
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityCreateRoutineBinding
import com.example.proyectapp.model.Routine
import com.example.proyectapp.model.RoutineExerciseCrossRef

class CreateRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRoutineBinding
    private lateinit var db: AppDatabase
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarBackRoutine)

        binding.buttonSaveRoutine.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Nombre de la rutina")

            val input = EditText(this)
            builder.setView(input)

            builder.setPositiveButton("Guardar") { _, _ ->
                val routineName = input.text.toString()

                // 1. Crear una nueva instancia de la entidad Routine con el nombre obtenido
                val newRoutine = Routine(routineName = routineName)

                // 2. Guardar la nueva rutina en la base de datos
                val routineId = db.routineDao().save(newRoutine)
                Log.d("TAG", "RutinaID: $routineId");

                // 3. Recuperar la lista de ejercicios seleccionados (exercisesForRoutine)
                val adapter = binding.exerciseRecyclerView.adapter as ExerciseRoutineAdapter
                val selectedExercises = adapter.exercisesForRoutine

                // 4. Para cada ejercicio en la lista, crear e insertar una instancia de RoutineExerciseCrossRef
                val routineExerciseCrossRefs = selectedExercises.map {
                    RoutineExerciseCrossRef(routineId, it.id)
                }
                db.routineExerciseCrossRefDao().saveAll(routineExerciseCrossRefs)
                val routineActivityIntent = Intent(this, RoutineActivity::class.java)
                startActivity(routineActivityIntent)
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }

            builder.show()
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
            db.exerciseDao().list(),this
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)
        binding.toolbarBackRoutine.setTitle("Seleccionar Ejercicios")
        binding.toolbarBackRoutine.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

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