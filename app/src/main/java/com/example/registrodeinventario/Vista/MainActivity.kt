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
import com.example.registrodeinventario.Constants // 🚨 Usa la constante global para uniformidad
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
    private var idUsuario: Int = 0

    // ⚠️ Se eliminó el companion object ya que ahora se usa la clase Constants.kt
    // Si aún no has migrado todas las referencias a Constants.EXTRA_USER_ID, usa la clave aquí.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) // Asegúrate de que el ID sea correcto

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. RECIBIR el ID del Intent del Login
        // Usando la constante global (asumiendo que la creaste)
        idUsuario = intent.getIntExtra(Constants.EXTRA_USER_ID, 0)

        // Manejo básico de ID nulo
        if (idUsuario <= 0) {
            Toast.makeText(this, "Sesión no válida. ID perdido.", Toast.LENGTH_SHORT).show()
        }

        // 2. 🚀 INICIALIZAR TODAS LAS VISTAS LATEINIT (CRÍTICO)
        btnLogin = findViewById(R.id.btnLogin)
        rcvLista = findViewById(R.id.rcvLista)
        playerView = findViewById(R.id.playerView) // 🚨 CORRECCIÓN: Inicializar playerView.
        // Asegúrate de que R.id.player_view sea el ID correcto en tu XML

        // 3. CONFIGURAR VISTAS
        rcvLista.layoutManager = LinearLayoutManager(this)

        // 4. INICIALIZAR PRESENTER
        presenter = MainPresenter(this)
        presenter.obtenerEquipos()

        // 5. CONFIGURAR NAVEGACIÓN
        btnLogin.setOnClickListener {
            val intentUsuario = Intent(this, Usuario::class.java).apply {
                // 🚨 USAR LA CONSTANTE GLOBAL
                putExtra(Constants.EXTRA_USER_ID, idUsuario)
            }
            startActivity(intentUsuario)
        }
    }


    // Inicializar el reproductor SIN cargar nada
    private fun startPlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer // 🛑 Ahora playerView está inicializada

        // Llamar a obtenerVideo() aquí para que el Presenter cargue el video
        presenter.caragarVide()
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
        // Mejor práctica para onStop: solo pausar
        exoPlayer?.playWhenReady = false
        exoPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayer() // Llamar a stopPlayer para liberar recursos
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