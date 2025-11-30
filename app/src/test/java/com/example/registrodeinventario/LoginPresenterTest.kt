package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Vista.Contracs.LoginContract
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class LoginPresenterTest {

    private val mockView = mock(LoginContract::class.java)
    private val presenter = LoginPresenter(mockView)

    @Test
    fun testCamposVacios() {
        presenter.iniciarSesion("", "")
        verify(mockView).mostrarMensaje("Debe llenar todos los campos")
    }
}
