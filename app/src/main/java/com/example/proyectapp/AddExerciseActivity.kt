package com.example.proyectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.proyectapp.databinding.ActivityAddExerciseBinding

class AddExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarBack)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionBarBack -> {
                val secondActivityIntent = Intent(this, SecondActivity::class.java)
                startActivity(secondActivityIntent)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}