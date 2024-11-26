package com.cse5236.headsUpStudy

import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.cse5236.headsUpStudy.ui.LoginActivity
import com.cse5236.headsUpStudy.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent

@RunWith(AndroidJUnit4::class)
class InstructionsTextViewTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)


    @Test
    fun testLoginSuccess() {
        Intents.init()
        // Enter valid username

        onView(withId(R.id.editTextUsername))
            .perform(typeText("mhgoub@gmail.com"), closeSoftKeyboard())

        // Enter valid password
        onView(withId(R.id.editTextTextPassword))
            .perform(typeText("testing123"), closeSoftKeyboard())

        // Click login button
        onView(withId(R.id.login_button)).perform(click())

        // Verify that an Intent was sent to launch MainActivity
        intended(hasComponent(MainActivity::class.java.name))

        // Release Intents
        Intents.release()


        // Verify that the login button now says "Logout"
        onView(withId(R.id.login_button)).check(matches(withText("Logout")))
    }

}