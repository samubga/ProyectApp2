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


        /*db.exerciseDao().save(
            Exercise("Extensión de Pantorrilla", null, null, "Pantorrilla", "Trabaja los músculos de la pantorrilla. Realiza el ejercicio de pie, elevando los talones para contraer los músculos de la pantorrilla.")
        )

        db.exerciseDao().save(
            Exercise("Hip Thrust", null, null, "Glúteos", "Enfocado en los glúteos y los músculos de la parte posterior de los muslos. Realiza el ejercicio apoyando la espalda en un banco y elevando las caderas hacia arriba.")
        )

        db.exerciseDao().save(
            Exercise("Extensión de Cuádriceps", null, null, "Cuádriceps", "Aísla y trabaja los músculos del cuádriceps en la parte frontal del muslo. Realiza el ejercicio extendiendo las piernas contra resistencia.")
        )

        db.exerciseDao().save(
            Exercise("Extensión de Femoral", null, null, "Femoral", "Trabaja los músculos de la parte posterior del muslo. Realiza el ejercicio flexionando las piernas hacia atrás contra resistencia.")
        )

        db.exerciseDao().save(
            Exercise("Aductor en Máquina", null, null, "Piernas", "Aísla y trabaja los músculos aductores internos de las piernas. Realiza el ejercicio cerrando las piernas contra resistencia.")
        )

        db.exerciseDao().save(
            Exercise("Tríceps Polea Abajo", null, null, "Tríceps", "Ejercicio para los músculos tríceps. Utiliza una polea alta y tira hacia abajo con la cuerda para trabajar los tríceps.")
        )

        db.exerciseDao().save(
            Exercise("Curl Predicador", null, null, "Bíceps", "Aísla los músculos del bíceps. Realiza el ejercicio apoyando los brazos sobre el banco del predicador y levantando la barra o mancuernas.")
        )

        db.exerciseDao().save(
            Exercise("Press Militar", null, null, "Hombro", "Trabaja los músculos deltoides. Realiza el ejercicio levantando una barra o mancuernas desde los hombros hacia arriba.")
        )

        db.exerciseDao().save(
            Exercise("Pull Over", null, null, "Espalda y Pecho", "Trabaja músculos de la espalda y del pecho. Realiza el ejercicio con una barra o mancuerna, extendiendo los brazos hacia atrás sobre una banca.")
        )

        db.exerciseDao().save(
            Exercise("Face Pull", null, null, "Hombro y Trapecio", "Ejercicio para los músculos del hombro y trapecio. Utiliza una cuerda en una polea y jala hacia la cara para trabajar los músculos del hombro.")
        )

        db.exerciseDao().save(
            Exercise("Press Plano", null, null, "Pecho", "Ejercicio básico para el desarrollo del pectoral. Realiza el ejercicio acostado sobre un banco plano, bajando y elevando la barra o mancuernas.")
        )

        db.exerciseDao().save(
            Exercise("Peck Deck", null, null, "Pecho", "Máquina diseñada para aislar los músculos pectorales. Realiza el ejercicio sentado y empuja los brazos hacia adentro contra la resistencia.")
        )

        db.exerciseDao().save(
            Exercise("Jalón al Pecho", null, null, "Espalda", "Trabaja los músculos de la espalda, especialmente los dorsales. Realiza el ejercicio tirando de la barra hacia el pecho.")
        )

        db.exerciseDao().save(
            Exercise("Remo Gironda Cerrado", null, null, "Espalda", "Variante de remo para trabajar la espalda. Realiza el ejercicio con un agarre cerrado, tirando de la barra hacia la cintura.")
        )*/
    }
}