package com.example.registrodeinventario.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registrodeinventario.Presentador.LoginPresenter
import com.example.registrodeinventario.R
import com.example.registrodeinventario.Test
import com.example.registrodeinventario.Vista.Contracs.LoginContract

class Login: AppCompatActivity(), LoginContract {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnIniciar: Button
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        var test = Test
        test.probarMarca()
        test.probarLogin("20240996@uthh.edu.mx","123123")
        test.probarEnviarCodigo("20241000@uthh.edu.mx")
        test.probarGuardarMarca("DELL")
        test.probarGuardarCategoria("Ejemplo")
        test.probarGuardarInventario("10","25487999","Ejemplo","DELL","Negro")
        test.probarObtenerCategoriaInv()
        test.probarObtenerEquipos()
        test.probarObtenerRoles()
        test.probarObtenerColor()
        test.probarRegistrarUsuario("Flor","Olivares","Cortes","20241028@uthh.edu.mx","Itzel","123456","1","2025")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar vistas
        etEmail = findViewById(R.id.edtUsuario)
        etPassword = findViewById(R.id.edtPass)
        btnIniciar = findViewById(R.id.btnIniciar)


        presenter = LoginPresenter(this)

        btnIniciar.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            presenter.iniciarSesion(email, pass)
        }

    }

    override fun mostrarMensaje(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    override fun navegarAMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun navegarAAdmin() {
        startActivity(Intent(this, administrador::class.java))
        finish()
    }
}
