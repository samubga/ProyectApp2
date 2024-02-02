package com.example.proyectapp.allExerciseActivities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectapp.R
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ExerciseLayoutBinding
import com.example.proyectapp.model.Exercise


class ExerciseAdapter (
    var exercises: List<Exercise>,
    val context: Context,
    val db: AppDatabase

) :

    RecyclerView.Adapter<ExerciseAdapter.ItemViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.exercise_layout, null)
        )
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val exercise = exercises[position]
        val binding = ExerciseLayoutBinding.bind(holder.itemView)

        binding.textViewName.text = exercise.name


        binding.textViewSets.text = exercise.sets.toString()

        binding.textViewReps.text = exercise.reps.toString()

        binding.textViewMuscle.text = exercise.muscle

        binding.texttViewNotes.text = exercise.notes
        binding.buttonDelete.setOnClickListener{
            mostrarDialogoConfirmacion { confirmacion ->
                var deletedRows = 0
                if(confirmacion){
                    deletedRows = db.exerciseDao().delete(exercise.id)
                    exercises = db.exerciseDao().list()
                    notifyDataSetChanged()
                }

                if(deletedRows == 0) {
                    Toast.makeText(context, "No se ha eliminado ningún ejercicio", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Se ha eliminado el ejercicio", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.buttonEdit.setOnClickListener{


        }
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