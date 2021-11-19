package com.eslam.thedonutproject

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import com.eslam.thedonutproject.ui.MainActivity
import com.eslam.thedonutproject.utils.DataBindingIdlingResource
import com.eslam.thedonutproject.utils.EspressoIdlingResource
import com.eslam.thedonutproject.utils.monitorActivity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ScoreDetailsFeature {

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
    fun checkCorrectDetailsDisplayedOnScreen()
    {

        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        sleep(2000)
        BaristaClickInteractions.clickOn(R.id.score_donut)

        assertDisplayed(R.id.score_detail,"Score : 514")
        assertDisplayed(R.id.max_score_detail,"Max Score Value : 700")
        assertDisplayed(R.id.account_IDV,"Account IDV Status : PASS")
        assertDisplayed(R.id.personal_type,"Personal Type : INEXPERIENCED")
        assertDisplayed(R.id.dashboard,"DashBoard Status : PASS")

        activityScenario.close()
    }



}