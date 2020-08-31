package com.sample.assignment.base

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import java.io.InputStream

/**
 * Created by Ajay Arya on 31/08/20
 */

abstract class BaseIT : BaseTest() {

    fun mockHttpResponse(fileName: String, responseCode: Int) {
        val json = getJson(fileName)
        return mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(json)
        )
    }

    private fun getJson(path: String): String {
        val inputStream: InputStream =
            InstrumentationRegistry.getInstrumentation().context.resources.assets.open(path)
        return inputStream.bufferedReader().use { it.readText() }
    }
}