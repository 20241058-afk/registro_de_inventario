package com.example.registrodeinventario.Vista

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.registrodeinventario.Presentador.EncargadoPresenter
import com.example.registrodeinventario.R
import com.example.registrodeinventario.Vista.Contracs.EncargadoContract

class Encargado : AppCompatActivity(), EncargadoContract {

    private lateinit var presenter: EncargadoPresenter

    private lateinit var edtNombre: EditText
    private lateinit var edtAPaterno: EditText
    private lateinit var edtAMaterno: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtMatricula: EditText
    private lateinit var edtPass: EditText
    private lateinit var edtCodigo: EditText
    private lateinit var spnRol: Spinner
    private lateinit var btnAgregar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encargado)

        presenter = EncargadoPresenter(this)

        edtNombre = findViewById(R.id.edtNombreE)
        edtAPaterno = findViewById(R.id.edtAPaterno)
        edtAMaterno = findViewById(R.id.edtAMaterno)
        edtCorreo = findViewById(R.id.edtCorreoE)
        edtMatricula = findViewById(R.id.edtUsuario)
        edtPass = findViewById(R.id.edtPass)
        edtCodigo = findViewById(R.id.edtCodigoV)
        spnRol = findViewById(R.id.spnrol)
        btnAgregar = findViewById(R.id.btnAgregarE)

        presenter.cargarRoles()

        btnAgregar.setOnClickListener {
            presenter.guardarEncargado(
                edtNombre.text.toString(),
                edtAPaterno.text.toString(),
                edtAMaterno.text.toString(),
                edtCorreo.text.toString(),
                edtMatricula.text.toString(),
                edtPass.text.toString(),
                edtCodigo.text.toString(),
                spnRol.selectedItem.toString()
            )
        }
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun limpiarCampos() {
        edtNombre.setText("")
        edtAPaterno.setText("")
        edtAMaterno.setText("")
        edtCorreo.setText("")
        edtMatricula.setText("")
        edtPass.setText("")
        edtCodigo.setText("")
    }

    override fun cargarRoles(lista: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnRol.adapter = adapter
    }
}
