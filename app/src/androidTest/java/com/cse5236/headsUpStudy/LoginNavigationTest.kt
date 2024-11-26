package com.cse5236.headsUpStudy

import androidx.test.ext.junit.runners.AndroidJUnit4
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
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.Intents.intended


@RunWith(AndroidJUnit4::class)
class LoginNavigationTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun LoginNavigationSuccess() {
        Intents.init()
        onView(withId(R.id.login_button)).perform(click())
        intended(hasComponent(LoginActivity::class.java.name))
        Intents.release()
    }
}