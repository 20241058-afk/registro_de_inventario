package com.example.registrodeinventario.Vista

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.registrodeinventario.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(Login::class.java)

    @Test
    fun testLoginCamposVacios() {
        // Presiona el botón sin escribir nada
        onView(withId(R.id.btnIniciar)).perform(click())

    }

    @Test
    fun testLoginUsuarioNormal() {
        // Escribe email y contraseña
        onView(withId(R.id.edtUsuario)).perform(typeText("usuario@example.com"), closeSoftKeyboard())
        onView(withId(R.id.edtPass)).perform(typeText("1234"), closeSoftKeyboard())

        // Presiona el botón Iniciar
        onView(withId(R.id.btnIniciar)).perform(click())
    }

    @Test
    fun testLoginAdmin() {
        onView(withId(R.id.edtUsuario)).perform(typeText("admin@example.com"), closeSoftKeyboard())
        onView(withId(R.id.edtPass)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.btnIniciar)).perform(click())

    }

    // prueba interacción con campos
    @Test
    fun testEscribirYBorrarCampos() {
        onView(withId(R.id.edtUsuario)).perform(typeText("usuario@example.com"), closeSoftKeyboard())
        onView(withId(R.id.edtUsuario)).perform(clearText())
        onView(withId(R.id.edtPass)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.edtPass)).perform(clearText())
    }
}

