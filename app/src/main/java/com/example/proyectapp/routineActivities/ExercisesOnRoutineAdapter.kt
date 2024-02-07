package com.example.proyectapp.routineActivities

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
import com.example.proyectapp.databinding.ExercisesOnRoutineLayoutBinding
import com.example.proyectapp.model.Exercise


class ExercisesOnRoutineAdapter (
    var exercises: List<Exercise>,
    val context: Context,
    val db: AppDatabase

) :

    RecyclerView.Adapter<ExercisesOnRoutineAdapter.ItemViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.exercises_on_routine_layout, null)
        )
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val exercise = exercises[position]
        val binding = ExercisesOnRoutineLayoutBinding.bind(holder.itemView)

        binding.textViewName.text = exercise.name


        binding.textViewSets.text = exercise.sets.toString()

        binding.textViewReps.text = exercise.reps.toString()

        binding.textViewMuscle.text = exercise.muscle

        binding.texttViewNotes.text = exercise.notes

    }




}