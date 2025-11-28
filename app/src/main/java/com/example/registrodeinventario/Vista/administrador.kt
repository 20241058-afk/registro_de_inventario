package com.example.registrodeinventario.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registrodeinventario.R

class administrador : AppCompatActivity() {
    private lateinit var btnInventario: Button
    private lateinit var btnEncargado: Button
    private lateinit var btnCategoria: Button
    private lateinit var btnMarca: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_administrador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnInventario= findViewById(R.id.btnInventario)
        btnEncargado= findViewById(R.id.btnEncargado)
        btnCategoria= findViewById(R.id.btnCategoria)
        btnMarca= findViewById(R.id.btnMarca)

        btnInventario.setOnClickListener {
            val intent = Intent(this, Inventario::class.java)
            startActivity(intent)
        }

        btnEncargado.setOnClickListener {
            val intent = Intent(this, Encargado::class.java)
            startActivity(intent)
        }

        btnCategoria.setOnClickListener {
            val intent = Intent(this, Categoria::class.java)
            startActivity(intent)
        }
        btnMarca.setOnClickListener {
            val intent = Intent(this, Marca::class.java)
            startActivity(intent)
        }

    }
}