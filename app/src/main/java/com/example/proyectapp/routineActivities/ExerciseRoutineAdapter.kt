package com.example.proyectapp.routineActivities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectapp.R
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ExerciseRoutineLayoutBinding
import com.example.proyectapp.model.Exercise

class ExerciseRoutineAdapter (

    var exercises: List<Exercise>,
    var exercisesForRoutine: List<Exercise>,
    val context: Context

) :

    RecyclerView.Adapter<ExerciseRoutineAdapter.ItemViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.exercise_routine_layout, null)
        )
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val exercise = exercises[position]
        val binding = ExerciseRoutineLayoutBinding.bind(holder.itemView)

        binding.textViewName.text = exercise.name


        binding.textViewSets.text = exercise.sets.toString()

        binding.textViewReps.text = exercise.reps.toString()

        binding.textViewMuscle.text = exercise.muscle

        binding.texttViewNotes.text = exercise.notes

        binding.buttonAddToRoutine.setOnClickListener {
            binding.background.setBackgroundColor(ContextCompat.getColor(context, R.color.green))

            val selectedExercise = exercises[position]

            if (!exercisesForRoutine.contains(selectedExercise)) {
                exercisesForRoutine = exercisesForRoutine.toMutableList().apply {
                    add(selectedExercise)
                }
                notifyDataSetChanged()
            }
        }

        binding.buttonRemoveFromRoutine.setOnClickListener {
            binding.background.setBackgroundColor(ContextCompat.getColor(context, R.color.backgroundDefault))

            val selectedExercise = exercises[position]
            if (exercisesForRoutine.contains(selectedExercise)) {
                exercisesForRoutine = exercisesForRoutine.toMutableList().apply {
                    remove(selectedExercise)
                }
                notifyDataSetChanged()
            }
        }


    }
}