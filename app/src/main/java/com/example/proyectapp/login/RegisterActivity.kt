package com.example.proyectapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.room.Room
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityRegisterBinding
import com.example.proyectapp.model.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()

        binding.buttonRegister.isEnabled = false



        binding.editTextConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val password = binding.editTextRegPassword.text.toString()
                val confirmPassword = s.toString()

                if (password != confirmPassword) {
                    binding.editTextConfirmPassword.error = "Las contraseñas no coinciden"
                    binding.buttonRegister.isEnabled = false
                } else {
                    binding.editTextConfirmPassword.error = null
                    binding.buttonRegister.isEnabled = true
                }
            }
        })

        binding.buttonRegister.setOnClickListener{
            val userName = binding.editTextRegUsername.text.toString()
            val password = binding.editTextRegPassword.text.toString()



            if (db.userDao().getUserByUsername(userName) != null) {
                Toast.makeText(this, "El nombre de usuario ya está en uso", Toast.LENGTH_SHORT).show()
            } else {

                val salt = PasswordUtils.generateRandomSalt();

                // Generar el hash de la contraseña
                val hashedPassword = PasswordUtils.generateHash(password, salt)

                val user = User(userName = userName, password = hashedPassword, salt = salt)
                db.userDao().save(user)

                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
            }
        }


    }


}