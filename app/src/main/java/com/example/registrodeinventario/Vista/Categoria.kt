package com.example.registrodeinventario.Vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.registrodeinventario.Presentador.CategoriaPresenter
import com.example.registrodeinventario.R
import com.example.registrodeinventario.Vista.Contracs.CategoriaContract

class Categoria : AppCompatActivity(), CategoriaContract {

    private lateinit var edtNombreCategoria: EditText
    private lateinit var btnGuardar: Button
    private lateinit var presenter: CategoriaPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)

        edtNombreCategoria = findViewById(R.id.edtCategoria)
        btnGuardar = findViewById(R.id.btnACategoria)

        presenter = CategoriaPresenter(this)

        btnGuardar.setOnClickListener {
            val nombre = edtNombreCategoria.text.toString().trim()
            presenter.guardarCategoria(nombre)
        }
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun limpiarCampos() {
        edtNombreCategoria.setText("")
    }
}
