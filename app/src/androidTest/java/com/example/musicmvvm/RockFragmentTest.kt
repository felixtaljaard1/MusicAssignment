package com.example.musicmvvm

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.musicmvvm.ui.dashboard.ClassicFragment
import com.example.musicmvvm.ui.home.RockFragment
import com.example.musicmvvm.viewmodel.classiclist.ClassicAdapter
import org.junit.Before
import org.junit.Test

class RockFragmentTest {

    private val navController = TestNavHostController(getApplicationContext())

    @Before
    fun setUp(){
        launchFragmentInHiltContainer<RockFragment>(Bundle(), R.style.Theme_MusicMVVM) {
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun checkIfClassicRecyclerViewVisible(){
        onView(withId(R.id.recyclerViewRock))
            .check(matches(isDisplayed()))
    }
}