package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.LoginModel
import com.example.registrodeinventario.Vista.Contracs.LoginContract

class LoginPresenter(private val vista: LoginContract) {
    private val model = LoginModel()

    fun iniciarSesion(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            vista.mostrarMensaje("Debe llenar todos los campos")
            return
        }

        model.iniciarSesion(email, password) { respuesta, error ->
            if (error != null) {
                vista.mostrarMensaje(error)
                return@iniciarSesion
            }

            if (respuesta != null && respuesta.Estado.lowercase() == "true") {

                val userId = respuesta.user_id // ⬅️ OBTENER el ID del usuario
                val rol = respuesta.rol ?: 2

                // ⚠️ Verificación de ID. Si es nulo, no podemos navegar.
                if (userId != null && userId > 0) {

                    // LLAMADA CORREGIDA: Usar la nueva función que pasa ambos IDs
                    // Ahora, la Activity de Login (Vista) decide a dónde navegar
                    // y cómo usar el userId.
                    vista.navegarPorRol(userId, rol)

                } else {
                    // Si el servidor confirma el login pero no da el ID, es un error de sistema
                    vista.mostrarMensaje("Error interno: ID de usuario no disponible.")
                }

            } else {
                vista.mostrarMensaje(respuesta?.Salida ?: "Usuario o contraseña incorrectos")
            }
        }
    }
}