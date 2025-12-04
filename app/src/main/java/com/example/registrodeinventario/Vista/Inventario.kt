package com.example.registrodeinventario.Vista

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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


        presenter.cargarColor()
        presenter.cargarMarca()
        presenter.cargarCategoria()

        btnGuardar.setOnClickListener {
            val numInv = edtNumInventario.text.toString()
            val serie = edtNumSerie.text.toString()

            // 1. Casteo al tipo de objeto REAL que está en el Spinner
            val categoriaSeleccionada = spnCategoria.selectedItem as com.example.registrodeinventario.Modelo.Categoria
            val marcaSeleccionada = spnMarca.selectedItem as com.example.registrodeinventario.Modelo.Marca
            val colorSeleccionado = spnColor.selectedItem as com.example.registrodeinventario.Modelo.Color

            // 2. Obtener el ID de la propiedad 'id' (como lo requiere el PHP para el INSERT)
            val categoria = categoriaSeleccionada.id.toString()
            val marca = marcaSeleccionada.id.toString()
            val color = colorSeleccionado.id.toString()

            // 3. ¡VALIDACIÓN CRÍTICA!: Evitar enviar 0 o IDs inválidos
            if (categoria.toInt() <= 0 || marca.toInt() <= 0 || color.toInt() <= 0) {
                mostrarMensaje("Selecciona opciones válidas (no 'id=0, nombre=null').")
                return@setOnClickListener // Detiene el proceso de guardado
            }

            presenter.guardarInventario(this@Inventario, numInv, serie, categoria, marca, color)
        }

    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun limpiarCampos() {
        edtNumInventario.setText("")
        edtNumSerie.setText("")
    }

    override fun cargarColor(lista: List<Color>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnColor.adapter = adapter
    }

    override fun cargarMarca(lista: List<com.example.registrodeinventario.Modelo.Marca>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnMarca.adapter = adapter
    }

    override fun cargarCategoria(lista: List<com.example.registrodeinventario.Modelo.Categoria>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCategoria.adapter = adapter
    }




}
