package com.eslam.thedonutproject

import android.graphics.Color
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.adevinta.android.barista.assertion.BaristaBackgroundAssertions.assertHasBackground
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickBack
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import com.eslam.thedonutproject.ui.MainActivity
import com.eslam.thedonutproject.utils.DataBindingIdlingResource
import com.eslam.thedonutproject.utils.EspressoIdlingResource
import com.eslam.thedonutproject.utils.monitorActivity
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before


@RunWith(AndroidJUnit4::class)
@LargeTest
class ScoreDonutFeature {


    private lateinit var activityScenario: ActivityScenario<MainActivity>


    private val dataBindingIdlingResource = DataBindingIdlingResource()


    @Before
    fun initialize() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        IdlingRegistry.getInstance().register(dataBindingIdlingResource)




    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)


    }

    @Test
    fun checkFirstScreenTitle()
    {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        assertDisplayed("Score")

        activityScenario.close()
    }

    @Test
    fun checkIfDonutDisplayed()
    {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        assertDisplayed(R.id.score_donut)
        activityScenario.close()
    }

    @Test
    fun CheckDisplayingTheCorrectScore()
    {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        //end point checked on browser and returned score 514
        sleep(2000)
        assertDisplayed(R.id.score_value,"514")
        activityScenario.close()
    }


    @Test
    fun checkNavigationToDetailsScreen()
    {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        clickOn(R.id.score_donut)

        assertDisplayed("Details")

        activityScenario.close()
    }

    @Test
    fun checkNavigationBackFromDetailsScreen()
    {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        clickOn(R.id.score_donut)
        clickBack()

        assertDisplayed("Score")

        activityScenario.close()
    }
}