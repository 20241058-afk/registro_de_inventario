package com.example.registrodeinventario.Vista

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.registrodeinventario.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Verifica que el video se muestra
    @Test
    fun testVideoVisible() {
        onView(withId(R.id.playerView)).check(matches(isDisplayed()))
    }

    // Verifica que la lista de equipos se muestra
    @Test
    fun testRecyclerViewVisible() {
        onView(withId(R.id.rcvLista)).check(matches(isDisplayed()))
    }

    // Verifica que el botón Login funciona
    @Test
    fun testBotonLogin() {
        onView(withId(R.id.btnLogin)).perform(click())
    }
}
