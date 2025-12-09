package com.example.registrodeinventario.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registrodeinventario.R

class Usuario : AppCompatActivity() {
    private lateinit var btnHistorial: Button

    // 1. Declarar la variable para el ID y la clave
    private var idUsuario: Int = 0
    private val USER_ID_KEY = Historial.EXTRA_USER_ID // Reutilizar la clave

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. RECIBIR el ID del Intent de MainActivity
        idUsuario = intent.getIntExtra(USER_ID_KEY, 0)

        if (idUsuario <= 0) {
            Toast.makeText(this, "Error de sesión: ID no encontrado.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        btnHistorial = findViewById(R.id.btnHistorial)
        btnHistorial.setOnClickListener {

            // 3. PASAR el ID a la Activity Historial
            val intentHistorial = Intent(this, Historial::class.java).apply {
                putExtra(USER_ID_KEY, idUsuario)
            }
            startActivity(intentHistorial)
        }
    }
}