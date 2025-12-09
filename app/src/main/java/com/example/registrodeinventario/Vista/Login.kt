package com.example.registrodeinventario.Vista

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registrodeinventario.Constants
import com.example.registrodeinventario.Presentador.LoginPresenter
import com.example.registrodeinventario.R
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

    // 🚀 NUEVA IMPLEMENTACIÓN DE NAVEGACIÓN (usa el ID)
    override fun navegarPorRol(userId: Int, rolId: Int) {
        // Decide a qué pantalla navegar y pasa el ID del usuario

        val destinoActivity = when (rolId) {
            1 -> administrador::class.java
            else -> MainActivity::class.java // O tu Activity principal
        }

        // CRÍTICO: Creamos el Intent y adjuntamos el ID del usuario usando la constante global.
        val intent = Intent(this, destinoActivity).apply {
            // USAMOS LA CONSTANTE GLOBAL SEGURA
            putExtra(Constants.EXTRA_USER_ID, userId)
        }

        startActivity(intent)
        finish()
    }

    override fun navegarAMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun navegarAAdmin() {
        startActivity(Intent(this, administrador::class.java))

    }
}
