package com.example.registrodeinventario

import com.example.registrodeinventario.Modelo.ifaceApiService
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    val retrofit= Retrofit.Builder()
        .baseUrl("https://inventariocomputo.grupoctic.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(ifaceApiService::class.java)

    @Test
    fun obtenerEquipos(){
        val response=api.obtenerEquipos().execute()
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Equipos: ${response.body()}")
    }

    @Test
    fun obtenerMarca() {
        val response = api.obtenerMarca().execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Marcas: ${response.body()}")
    }

    @Test
    fun guardarCategoria() {
        val response = api.guardarCategoria(nombre = "PruebaCategoria").execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Guardar categoría: ${response.body()}")
    }

    @Test
    fun guardarMarca() {
        val response = api.guardarMarca(nombre = "MarcaPrueba").execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Guardar marca: ${response.body()}")
    }

    @Test
    fun loginIncorrecto() {
        val response = api.iniciarSesion(
            action = "login",
            email = "20241000@uthh.edu.mx",
            password = "9999"
        ).execute()

        assertTrue(response.isSuccessful)
        println("Login incorrecto: ${response.body()}")
    }

    @Test
    fun obtenerRoles() {
        val response = api.obtenerRoles().execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Roles: ${response.body()}")
    }

    @Test
    fun enviarCodigo() {
        val response = api.enviarCodigo(
            correo = "20241000@uthh.edu.mx"
        ).execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Enviar código: ${response.body()}")
    }

    @Test
    fun registrarUsuario() {
        val response = api.registrarUsuario(
            nombre = "Juan",
            apPaterno = "Pérez",
            apMaterno = "López",
            correo = "20244852@uthh.edu.mx",
            usuario = "usuarioPrueba",
            password = "1234",
            rol = "Administrador",
            codigoVerificacion = "0000"
        ).execute()

        assertTrue(response.isSuccessful)
        println("Registrar usuario: ${response.body()}")
    }

    @Test
    fun obtenerColor() {
        val response = api.obtenerColor().execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Colores: ${response.body()}")
    }

    @Test
    fun obtenerCategoria() {
        val response = api.obtenerCategoria().execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Categorías: ${response.body()}")
    }

    @Test
    fun guardarInventario() {
        val response = api.guardarInventario(
            numInv = "INV001",
            numSerie = "SER123",
            categoria = "Laptop",
            marca = "Dell",
            color = "Negro"
        ).execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        println("Guardar inventario: ${response.body()}")
    }

}