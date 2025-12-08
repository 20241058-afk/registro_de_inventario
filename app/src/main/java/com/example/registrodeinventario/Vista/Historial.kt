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

class Historial : AppCompatActivity(), HistorialContract {
    private lateinit var presenter: HistorialPresenter
    private lateinit var rcvHistorial: RecyclerView

    // Cambiar por el ID del usuario que inició sesión
    private var idUsuario = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        rcvHistorial = findViewById(R.id.rcvHistorial)
        rcvHistorial.layoutManager = LinearLayoutManager(this)

        presenter = HistorialPresenter(this)
        presenter.obtenerHistorial(idUsuario)
    }

    override fun mostrarHistorial(lista: List<clsHistorial>) {
        rcvHistorial.adapter = historialAdapter(this, lista)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}