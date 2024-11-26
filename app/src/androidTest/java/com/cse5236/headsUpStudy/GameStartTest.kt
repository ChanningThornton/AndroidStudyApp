
package com.cse5236.headsUpStudy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.cse5236.headsUpStudy.ui.GameActivity
import com.cse5236.headsUpStudy.ui.LoginActivity
import com.cse5236.headsUpStudy.ui.NewGameActivity
import org.hamcrest.Matchers.anything

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewGameActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testGameStartsCorrectly() {
        Intents.init()

        // Log in first
        onView(withId(R.id.editTextUsername))
            .perform(typeText("mhgoub@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextTextPassword))
            .perform(typeText("testing123"), closeSoftKeyboard())
        onView(withId(R.id.login_button)).perform(click())


        // Navigate to NewGameActivity
        onView(withId(R.id.newGame_button)).perform(click())

        // Simulate selecting the first category and time limit
        onView(withId(R.id.category_dropdown)).perform(click())
        val firstCategory = onData(anything()).atPosition(0).toString()
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.time_dropdown)).perform(click())
        onView(withText("1:00")).perform(click())

        // Click the start button
        onView(withId(R.id.start_button)).perform(click())

        // Verify that GameActivity is started with the correct extras
        Intents.intended(
            hasComponent(GameActivity::class.java.name),

        )

        Intents.release()
    }
}