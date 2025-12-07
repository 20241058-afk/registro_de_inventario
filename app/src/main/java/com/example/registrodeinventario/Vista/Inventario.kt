package com.example.registrodeinventario.Vista

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.registrodeinventario.Modelo.Color
import com.example.registrodeinventario.Modelo.Categoria
import com.example.registrodeinventario.Modelo.Marca
import com.example.registrodeinventario.Presentador.InventarioPresenter
import com.example.registrodeinventario.R
import com.example.registrodeinventario.Vista.Contracs.InventarioContract

class Inventario : AppCompatActivity(), InventarioContract {

    private lateinit var edtNumInventario: EditText
    private lateinit var edtNumSerie: EditText
    private lateinit var spnCategoria: Spinner
    private lateinit var spnMarca: Spinner
    private lateinit var spnColor: Spinner
    private lateinit var btnGuardar: Button
    private lateinit var presenter: InventarioPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inventario)

        presenter = InventarioPresenter(this)

        edtNumInventario = findViewById(R.id.edtNumInventario)
        edtNumSerie = findViewById(R.id.edtNumSerie)
        spnCategoria = findViewById(R.id.spnCategoria)
        spnMarca = findViewById(R.id.spnMarca)
        spnColor = findViewById(R.id.spnColor)
        btnGuardar = findViewById(R.id.btnAgregarI)

        // Cargar datos en los Spinners
        presenter.cargarColor()
        presenter.cargarMarca()
        presenter.cargarCategoria()

        btnGuardar.setOnClickListener {
            val numInv = edtNumInventario.text.toString()
            val serie = edtNumSerie.text.toString()

            val categoriaSeleccionada = spnCategoria.selectedItem as Categoria
            val marcaSeleccionada = spnMarca.selectedItem as Marca
            val colorSeleccionado = spnColor.selectedItem as Color

            // VALIDACIONES INDIVIDUALES
            when {
                categoriaSeleccionada.id == 0 -> {
                    mostrarMensaje("Debes seleccionar una categoría")
                    return@setOnClickListener
                }
                marcaSeleccionada.id == 0 -> {
                    mostrarMensaje("Debes seleccionar una marca")
                    return@setOnClickListener
                }
                colorSeleccionado.id == 0 -> {
                    mostrarMensaje("Debes seleccionar un color")
                    return@setOnClickListener
                }
            }

            // Si todo está bien → Guardar
            presenter.guardarInventario(
                this@Inventario,
                numInv,
                serie,
                categoriaSeleccionada.id.toString(),
                marcaSeleccionada.id.toString(),
                colorSeleccionado.id.toString()
            )
        }
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun limpiarCampos() {
        edtNumInventario.setText("")
        edtNumSerie.setText("")
        spnColor.setSelection(0)
        spnMarca.setSelection(0)
        spnCategoria.setSelection(0)
    }

    override fun cargarColor(lista: List<Color>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnColor.adapter = adapter
    }

    override fun cargarMarca(lista: List<Marca>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnMarca.adapter = adapter
    }

    override fun cargarCategoria(lista: List<Categoria>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCategoria.adapter = adapter
    }
}
