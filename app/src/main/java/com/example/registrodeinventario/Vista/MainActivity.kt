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
    lateinit var presenter: MainPresenter
    private lateinit var playerView: StyledPlayerView

    // ÚNICO reproductor
    private var exoPlayer: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Referencias de UI
        btnLogin = findViewById(R.id.btnLogin)
        rcvLista = findViewById(R.id.rcvLista)
        rcvLista.layoutManager = LinearLayoutManager(this)

        // VIDEO
        playerView = findViewById(R.id.playerView)
        startPlayer() // <-- IMPORTANTE inicializar aquí

        // Presenter
        presenter = MainPresenter(this)
        presenter.caragarVide()      // <-- YA ES SEGURO llamar el video
        presenter.obtenerEquipos()   // <-- Cargar equipos de BD

        btnLogin.setOnClickListener {
            startActivity(Intent(this, Usuario::class.java))
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




    // -------------------- MVP -----------------------

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
