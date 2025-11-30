package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.clsEquipos
import com.example.registrodeinventario.Vista.Contracs.MainContrac
import org.junit.Test

class MainPresenterTest {

    // Mock simple de la vista
    class MockView : MainContrac {
        var videoUrl: String? = null
        var equipos: List<clsEquipos>? = null
        var mensajeError: String? = null

        override fun mostrarvideo(video: String) {
            videoUrl = video
        }
        override fun mostrarEquipos(equipos: List<clsEquipos>) {
            this.equipos = equipos
        }
        override fun mostrarError(mensaje: String) {
            mensajeError = mensaje
        }
    }

    @Test
    fun testCargarVideo() {
        val mockView = MockView()
        val presenter = MainPresenter(mockView)

        presenter.caragarVide()

        assert(mockView.videoUrl == "https://inventariocomputo.grupoctic.com/video/video1.mp4")
    }

    @Test
    fun testMostrarEquiposConDatos() {
        val mockView = MockView()

        val lista = listOf(
            clsEquipos(
                id_equipo = "1",
                nombre = "PC1",
                descripcion = "Intel i5, 8GB RAM",
                ruta_imagen = "",
                codigo_qr = "",
                id_categoria = 1,
                estado = "Activo",
                fecha_registro = "2025-11-30"
            ),
            clsEquipos(
                id_equipo = "2",
                nombre = "PC2",
                descripcion = "AMD Ryzen, 16GB RAM",
                ruta_imagen = "",
                codigo_qr = "",
                id_categoria = 1,
                estado = "Activo",
                fecha_registro = "2025-11-30"
            )
        )

        mockView.mostrarEquipos(lista)

        assert(mockView.equipos?.size == 2)
        assert(mockView.equipos?.get(0)?.nombre == "PC1")
        assert(mockView.equipos?.get(1)?.nombre == "PC2")
    }

    @Test
    fun testMostrarEquiposVacios() {
        val mockView = MockView()
        val lista = emptyList<clsEquipos>()

        mockView.mostrarEquipos(lista)

        assert(mockView.equipos?.isEmpty() == true)
    }

    @Test
    fun testMostrarError() {
        val mockView = MockView()

        mockView.mostrarError("No hay equipos")

        assert(mockView.mensajeError == "No hay equipos")
    }
}
