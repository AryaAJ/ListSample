package com.sample.assignment.ui

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.sample.assignment.base.BaseIT
import com.sample.assignment.utils.waitForAdapterChange
import com.sample.assignment.R
import com.sample.assignment.view.activity.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

/**
 * Created by Ajay Arya on 31/08/20
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainInstrumentTest : BaseIT() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @get:Rule
    var executorRule = CountingTaskExecutorRule()

    // OVERRIDE ---
    override fun isMockServerEnabled() = true

    @Before
    override fun setUp() {
        super.setUp()
        activityRule.launchActivity(null)
    }

    // UI TESTS ---

    @Test
    fun when_list_is_empty() {
        onView(withId(R.id.tool_title)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tool_title), withText("Title"))).check(matches(isDisplayed()))
        onView(withId(R.id.tv_error)).check(matches(isDisplayed()))
    }

    @Test
    fun when_list_is_populated() {
        mockHttpResponse("list_data.json", HttpURLConnection.HTTP_OK)

        onView(withId(R.id.tool_title)).check(matches(isDisplayed()))
        waitForAdapterChange(getRecyclerView(), executorRule, 1)

        onView(allOf(withId(R.id.tv_title), withText("Beavers"))).check(matches(isDisplayed()))
    }

    @Test
    fun when_list_is_not_populated_with_wrongtext() {
        mockHttpResponse("list_data.json", HttpURLConnection.HTTP_OK)

        onView(withId(R.id.tool_title)).check(matches(isDisplayed()))
        waitForAdapterChange(getRecyclerView(), executorRule, 1)

        onView(
            allOf(
                withId(R.id.tv_title),
                not(
                    withText("Beaverssss") //wrong text
                )
            )
        )
    }

    // UTILS
    /**
     * Convenient access to [VenueListFragment]'s RecyclerView
     */
    private fun getRecyclerView() =
        activityRule.activity.findViewById<RecyclerView>(R.id.list)
}