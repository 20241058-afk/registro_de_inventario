package com.example.registrodeinventario.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeinventario.Modelo.clsEquipos
import com.example.registrodeinventario.Presentador.MainPresenter
import com.example.registrodeinventario.R
import com.example.registrodeinventario.Vista.Adaptador.equiposAdapter
import com.example.registrodeinventario.Vista.Contracs.MainContrac
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class MainActivity : AppCompatActivity(), MainContrac {

    private lateinit var btnLogin: Button
    private lateinit var rcvLista: RecyclerView
    private lateinit var presenter: MainPresenter
    private lateinit var playerView: StyledPlayerView

    private var exoPlayer: ExoPlayer? = null

    // 1. Declarar la variable para el ID del usuario y la clave
    private var idUsuario: Int = 0
    companion object {
        // Usamos la misma constante definida en Historial.kt para uniformidad
        const val EXTRA_USER_ID = "extra_user_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ... (código ViewCompat y setContentView)

        // 2. RECIBIR el ID del Intent del Login
        idUsuario = intent.getIntExtra(EXTRA_USER_ID, 0)

        // Manejo básico de ID nulo
        if (idUsuario <= 0) {
            Toast.makeText(this, "Sesión no válida. ID perdido.", Toast.LENGTH_SHORT).show()
            // Podrías forzar el cierre y volver al login si el ID es crítico
        }

        // Referencias de UI
        btnLogin = findViewById(R.id.btnLogin)
        rcvLista = findViewById(R.id.rcvLista)
        rcvLista.layoutManager = LinearLayoutManager(this)

        // ... (código del video y Presenter)

        btnLogin.setOnClickListener {
            // 3. PASAR el ID a la Activity Usuario
            val intentUsuario = Intent(this, Usuario::class.java).apply {
                putExtra(EXTRA_USER_ID, idUsuario)
            }
            startActivity(intentUsuario)
        }
    }


    // Inicializar el reproductor SIN cargar nada
    private fun startPlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer
    }

    // Detener y liberar memoria
    private fun stopPlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }

    override fun onStart() {
        super.onStart()
        if (exoPlayer == null) startPlayer()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer?.playWhenReady = false   // Pausa
        exoPlayer?.pause()
        // NO lo destruyas
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }


    override fun mostrarEquipos(equipos: List<clsEquipos>) {
        if (equipos.isEmpty()) {
            Toast.makeText(this, "Sin equipos registrados", Toast.LENGTH_SHORT).show()
            rcvLista.adapter = equiposAdapter(this, emptyList())
        } else {
            rcvLista.adapter = equiposAdapter(this, equipos)
        }
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun mostrarvideo(video: String) {
        val mediaItem = MediaItem.fromUri(video)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.playWhenReady = true
    }
}
