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
import com.example.registrodeinventario.Modelo.ItemSpinner
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
            val categoria = (spnCategoria.selectedItem as ItemSpinner).id
            val marca = (spnMarca.selectedItem as ItemSpinner).id
            val color = (spnColor.selectedItem as ItemSpinner).id


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

    override fun cargarColor(lista: List<ItemSpinner>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnColor.adapter = adapter
    }

    override fun cargarMarca(lista: List<ItemSpinner>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnMarca.adapter = adapter
    }

    override fun cargarCategoria(lista: List<ItemSpinner>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCategoria.adapter = adapter
    }

}
