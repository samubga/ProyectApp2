package com.example.proyectapp.routineActivities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectapp.R
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.RoutineLayoutBinding
import com.example.proyectapp.model.Routine

class RoutineAdapter (
    var routines : List<Routine>,
    val context: Context,
    val db: AppDatabase

) :
    RecyclerView.Adapter<RoutineAdapter.ItemViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private val expandedStates = MutableList(routines.size) { false }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.routine_layout, null)
        )
    }

    override fun getItemCount(): Int {
        return routines.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val routine = routines[position]
        val binding = RoutineLayoutBinding.bind(holder.itemView)



        binding.textViewName.text = routine.routineName


        binding.buttonDeleteRoutine.setOnClickListener{
            mostrarDialogoConfirmacion { confirmacion ->
                var deletedRows = 0
                if(confirmacion){
                    deletedRows = db.routineDao().delete(routine.id)
                    routines = db.routineDao().list()
                    notifyDataSetChanged()
                }

                if(deletedRows == 0) {
                    Toast.makeText(context, "No se ha eliminado la rutina", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Se ha eliminado la rutina", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.buttonViewExercises.setOnClickListener {
            val esIntent = Intent(context, ExercisesSelectedRoutineActivity::class.java)
            esIntent.putExtra("routineId", routine.id)
            context.startActivity(esIntent)
        }



        /*val isExpanded = expandedStates[position]  // Usar el estado de expansión correspondiente
        val rr = routine.id
        val exercisesForRoutine = db.exerciseDao().getExercisesForRoutine(routine.id)
        Log.d("TAG", "Id de routine$rr");
        Log.d("TAG", "Id de routine$exercisesForRoutine");
        Log.d("TAG", "Id de routine$isExpanded");
        // Configura la vista según el estado de expansión
        if (isExpanded) {
            // Muestra el segundo RecyclerView con los ejercicios
            binding.exerciseRecyclerView.visibility = View.VISIBLE
            Log.d("TAG", "Abrir$isExpanded");
            binding.exerciseRecyclerView.layoutManager = GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)

            binding.exerciseRecyclerView.adapter = ExerciseAdapter(exercisesForRoutine,context, db)
        } else {
            // Oculta el segundo RecyclerView
            Log.d("TAG", "cerrar$isExpanded");
            binding.exerciseRecyclerView.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            // Cambia el estado de expansión al hacer clic
            expandedStates[position] = !isExpanded
            notifyItemChanged(position)
        }*/

    }


    private fun mostrarDialogoConfirmacion(callback: (Boolean) -> Unit) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Confirmar eliminación")
        builder.setMessage("¿Estás seguro de que quieres realizar esta acción?")

        builder.setPositiveButton("Sí") { dialogInterface: DialogInterface, _: Int ->
            callback(true)
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
            callback(false)
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
