package com.example.registrodeinventario.Vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registrodeinventario.Presentador.CategoriaPresenter
import com.example.registrodeinventario.Presentador.MarcaPresenter
import com.example.registrodeinventario.R
import com.example.registrodeinventario.Vista.Contracs.MarcaContract

class Marca : AppCompatActivity(), MarcaContract {
    private lateinit var edtNombreMarca: EditText
    private lateinit var btnGuardar: Button
    private lateinit var presenter: MarcaPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_marca)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtNombreMarca = findViewById(R.id.edtMarca)
        btnGuardar = findViewById(R.id.btnAgregarMarca)

        presenter = MarcaPresenter(this)

        btnGuardar.setOnClickListener {
            val nombre = edtNombreMarca.text.toString().trim()
            presenter.guardarMarca(nombre)
        }
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun limpiarCampos() {
        edtNombreMarca.setText("")
    }
}