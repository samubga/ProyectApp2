package com.example.proyectapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.proyectapp.MainActivity
import com.example.proyectapp.database.AppDatabase
import com.example.proyectapp.databinding.ActivityLoginBinding
import com.example.proyectapp.model.User

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        binding.buttonLogin.setOnClickListener{
            val userName = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()


            val user = db.userDao().getUserByUsername(userName)


            if (user != null) {

                //Comprobar el hash de la contraseña
                val hashedPassword = PasswordUtils.generateHash(password, user.salt)

                if (hashedPassword == user.password) {

                    User.currentUserID = user.id
                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)

                } else {

                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }

        }

        binding.textCreateAnAccount.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}