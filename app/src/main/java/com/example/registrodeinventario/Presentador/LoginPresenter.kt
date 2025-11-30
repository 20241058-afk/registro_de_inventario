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
        model.iniciarSesion(email, password, { respuesta, error ->
            if (error != null) {
                vista.mostrarMensaje(error)
                return@iniciarSesion
            }
            if (respuesta != null && respuesta.Estado.lowercase() == "true") {

                val rol = respuesta.rol ?: 2

                if (rol == 1) {
                    // ADMIN
                    vista.navegarAAdmin()
                } else {
                    // USUARIO NORMAL
                    vista.navegarAMain()
                }
            } else {
                vista.mostrarMensaje(respuesta?.Salida ?: "Usuario o contraseña incorrectos")
            }
        } ) //{ callback ->
           // callback.invoke(clsDatosRespuesta("true", "Bienvenido"), null)
    }
}