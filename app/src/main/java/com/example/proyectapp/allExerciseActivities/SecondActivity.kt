package com.example.proyectapp.allExerciseActivities

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
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivitySecondBinding
import com.example.proyectapp.model.Exercise
import com.example.proyectapp.routineActivities.RoutineActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        //Método para meter datos

        //createInitialData()


        binding.exerciseRecyclerView.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.exerciseRecyclerView.adapter = ExerciseAdapter(
            db.exerciseDao().list(), this, db
        )

        binding.addButton.setOnClickListener{
            val addExerciseIntent = Intent(
                this, AddExerciseActivity::class.java
            )

            startActivity(addExerciseIntent)
        }
        val dividerItemDecoration = DividerItemDecoration(
            binding.exerciseRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )

        // Agregar el DividerItemDecoration al RecyclerView
        binding.exerciseRecyclerView.addItemDecoration(dividerItemDecoration)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        binding.toolbar.setTitle("Ejercicios")
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()

        val adapter = binding.exerciseRecyclerView.adapter as ExerciseAdapter

        adapter.exercises = db.exerciseDao().list()

        adapter.notifyDataSetChanged()
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

    fun createInitialData() {
        db.exerciseDao().save(
            Exercise("Curl martillo",4 ,10, "Biceps", " Un ejercicio efectivo para aislar y trabajar los músculos del bíceps. El agarre neutral en forma de martillo proporciona un estímulo adicional.")
        )
        db.exerciseDao().save(
            Exercise("Press inclinado",3 ,8, "Pecho", "Ideal para desarrollar la porción superior del músculo pectoral (pecho). Asegúrate de ajustar el banco a un ángulo inclinado para enfocarte en esta área específica.")
        )
        db.exerciseDao().save(
            Exercise("Sentadilla haka",3 ,8, "Cuadriceps y femoral", "Una variante de la sentadilla que involucra los músculos de las piernas y glúteos. Mantén una postura amplia y los pies girados hacia afuera.")
        )
        db.exerciseDao().save(
            Exercise("Laterales",4 ,12, "Hombro", "Ejercicio de aislamiento para trabajar los músculos laterales del hombro (deltoides lateral). Ayuda a desarrollar el ancho de los hombros.")
        )
        db.exerciseDao().save(
            Exercise("Extensión de triceps",3 ,12, "Triceps", "Trabajo específico en los músculos del tríceps. Puedes realizarlo con barra o mancuernas.")
        )
        db.exerciseDao().save(
            Exercise("Dominadas",3 ,10, "Espalda", "Un ejercicio efectivo para trabajar la espalda, especialmente los músculos dorsales y los brazos. Puedes variar el agarre para enfocarte en diferentes áreas.")
        )
        db.exerciseDao().save(
            Exercise("Remo en máquina",2 ,10, "Espalda", "Ejercicio de remo que trabaja los músculos de la espalda. Utiliza una máquina de remo para una ejecución controlada.")
        )
    }
}