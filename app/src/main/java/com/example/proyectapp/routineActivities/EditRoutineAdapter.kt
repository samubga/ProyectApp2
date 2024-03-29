package com.example.proyectapp.routineActivities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectapp.R
import com.example.proyectapp.databinding.AddExcrsToRtnLayoutBinding
import com.example.proyectapp.model.Exercise

class EditRoutineAdapter (
    var exercises: Map<Int, Exercise>,
    var allExercises: List<Exercise>,
    val context: Context

) :
    RecyclerView.Adapter<EditRoutineAdapter.ItemViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    var exercisesForRoutine: List<Exercise> = exercises.values.toList()

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.add_excrs_to_rtn_layout, null)
        )
    }

    override fun getItemCount(): Int {
        return allExercises.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val exercise = allExercises[position]
        val binding = AddExcrsToRtnLayoutBinding.bind(holder.itemView)

        binding.textViewName.text = exercise.name


        binding.textViewSets.text = exercise.sets.toString()

        binding.textViewReps.text = exercise.reps.toString()

        binding.textViewMuscle.text = exercise.muscle

        binding.texttViewNotes.text = exercise.notes

        if(exercisesForRoutine.contains(exercise)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))

        } else {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.backgroundDefault
                )
            )
        }

        binding.buttonAddToRoutine.setOnClickListener {
            if (!exercisesForRoutine.contains(exercise)) {

                exercisesForRoutine = exercisesForRoutine.toMutableList().apply {
                    add(exercise)

                }
                notifyDataSetChanged()
            }
        }

        binding.buttonRemoveFromRoutine.setOnClickListener {


            val selectedExercise = exercises[exercise.id]
            if (exercisesForRoutine.contains(selectedExercise)) {


                exercisesForRoutine = exercisesForRoutine.toMutableList().apply {
                    remove(selectedExercise)

                }
                notifyDataSetChanged()
            }

        }
    }
    fun getUpdatedExerciseList(): List<Exercise>{
        return exercisesForRoutine

    }
}