package com.mehyo.githubrepostask.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mehyo.githubrepostask.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get: Rule
    val activityRule=ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isMainActivityVisible_onAppLaunch(){
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }
    @Test
    fun test_isRecyclerViewVisible_onAppLaunch(){
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_recyclerViewItem_scrollToPositionNumber20(){
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(20))
    }
    @Test
    fun test_recyclerViewItem_scrollToPositionNumber100(){
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(100))
    }
}