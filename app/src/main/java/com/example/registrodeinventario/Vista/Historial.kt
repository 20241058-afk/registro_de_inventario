package com.example.registrodeinventario.Vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeinventario.Modelo.clsHistorial
import com.example.registrodeinventario.Presentador.HistorialPresenter
import com.example.registrodeinventario.R
import com.example.registrodeinventario.Vista.Adaptador.historialAdapter
import com.example.registrodeinventario.Vista.Contracs.HistorialContract

// Archivo: com.example.registrodeinventario.Vista/Historial.kt

class Historial : AppCompatActivity(), HistorialContract {
    private lateinit var presenter: HistorialPresenter
    private lateinit var rcvHistorial: RecyclerView

    // Definir la clave para el Intent
    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }

    private var idUsuario: Int = 0 // Inicializar en 0 o -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        // 1. Obtener el ID del Intent
        idUsuario = intent.getIntExtra(EXTRA_USER_ID, 0)

        if (idUsuario <= 0) {
            // Manejar error si el ID no se pasó correctamente
            Toast.makeText(this, "Error: ID de usuario no válido.", Toast.LENGTH_LONG).show()
            finish() // Cierra la Activity
            return
        }

        rcvHistorial = findViewById(R.id.rcvHistorial)
        rcvHistorial.layoutManager = LinearLayoutManager(this)

        presenter = HistorialPresenter(this)
        // 2. Usar el ID obtenido del Login
        presenter.obtenerHistorial(idUsuario)
    }
    // ... (el resto del código sigue igual)
    override fun mostrarHistorial(lista: List<clsHistorial>) {
        rcvHistorial.adapter = historialAdapter(this, lista)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}

