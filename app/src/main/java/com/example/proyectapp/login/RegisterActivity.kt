package com.example.proyectapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.proyectapp.R
import com.example.proyectapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonRegister.isEnabled = false
        binding.buttonRegister.setOnClickListener{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }


        binding.editTextConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No necesitas implementar nada aquí
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No necesitas implementar nada aquí
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

    }
}