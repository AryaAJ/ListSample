package com.sample.assignment.base

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

/**
 * Created by Ajay Arya on 31/08/20
 */

abstract class BaseTest {

    // FOR DATA ---
    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        configureMockServer()
    }

    @After
    open fun tearDown() {
        stopMockServer()
    }

    // MOCK SERVER ---
    abstract fun isMockServerEnabled(): Boolean // Because we don't want it always enabled on all tests

    private fun configureMockServer() {
        if (isMockServerEnabled()) {
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    private fun stopMockServer() {
        if (isMockServerEnabled()) {
            mockServer.shutdown()
        }
    }

    fun getMockUrl() = mockServer.url("/").toString()

}