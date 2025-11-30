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
    private lateinit var edtusuario: EditText
    private lateinit var edtPass: EditText
    private lateinit var edtCodigo: EditText
    private lateinit var spnRol: Spinner
    private lateinit var btnAgregar: Button
    private lateinit var btnEnviarCodigo: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encargado)

        // Inicializar el Presenter, pasando la Vista (this)
        presenter = EncargadoPresenter(this)

        // Asignación de Vistas por ID
        edtNombre = findViewById(R.id.edtNombreE)
        edtAPaterno = findViewById(R.id.edtAPaterno)
        edtAMaterno = findViewById(R.id.edtAMaterno)
        edtCorreo = findViewById(R.id.edtCorreoE)
        edtusuario = findViewById(R.id.edtUsuario)
        edtPass = findViewById(R.id.edtPass)
        edtCodigo = findViewById(R.id.edtCodigoV)
        spnRol = findViewById(R.id.spnrol)
        btnAgregar = findViewById(R.id.btnAgregarE)
        btnEnviarCodigo = findViewById(R.id.btnenviar)


        // Cargar los roles al iniciar la actividad
        presenter.cargarRoles()

        // Lógica del botón Enviar Código
        btnEnviarCodigo.setOnClickListener {
            val correo = edtCorreo.text.toString().trim()

            if (correo.isEmpty()) {
                mostrarMensaje("Ingresa un correo")
                return@setOnClickListener
            }

            presenter.enviarCodigo(correo)
        }

        // Lógica del botón Agregar (Registro de usuario)
        btnAgregar.setOnClickListener {

            // === VALIDACIÓN CRÍTICA DEL SPINNER ===
            // Asegura que selectedItem no sea null (vacío) antes de llamar a toString()
            val rolSeleccionado = spnRol.selectedItem?.toString()

            if (rolSeleccionado.isNullOrEmpty()) {
                mostrarMensaje("Error: El rol no está seleccionado o cargado.")
                return@setOnClickListener
            }
            // =====================================

            presenter.guardarEncargadoConCodigo(
                edtNombre.text.toString(),
                edtAPaterno.text.toString(),
                edtAMaterno.text.toString(),
                edtCorreo.text.toString(),
                edtusuario.text.toString(),
                edtPass.text.toString(),
                rolSeleccionado, // Usamos el valor ya validado
                edtCodigo.text.toString()
            )
        }
    }

    // Implementación de la función mostrarMensaje del contrato
    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    // Implementación de la función limpiarCampos del contrato
    override fun limpiarCampos() {
        edtNombre.setText("")
        edtAPaterno.setText("")
        edtAMaterno.setText("")
        edtCorreo.setText("")
        edtusuario.setText("")
        edtPass.setText("")
        edtCodigo.setText("")
    }

    // Implementación de la función cargarRoles del contrato
    override fun cargarRoles(lista: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnRol.adapter = adapter
    }
}