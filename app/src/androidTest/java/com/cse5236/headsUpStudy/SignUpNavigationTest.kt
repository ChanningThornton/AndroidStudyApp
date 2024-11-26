package com.cse5236.headsUpStudy

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.cse5236.headsUpStudy.ui.LoginActivity

import com.cse5236.headsUpStudy.ui.SignUpActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.Intents.intended


@RunWith(AndroidJUnit4::class)
class SignUpActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun SignUpNavigationSuccess() {
        Intents.init()
        onView(withId(R.id.signup_button)).perform(click())
        intended(hasComponent(SignUpActivity::class.java.name))
        Intents.release()
    }
}