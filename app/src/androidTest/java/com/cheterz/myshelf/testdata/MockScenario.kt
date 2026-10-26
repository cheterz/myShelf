package com.cheterz.myshelf.testdata

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse

enum class MockScenario(
    val code: Int,
    val bodyFile: String?,
) {
    ITEMS_SUCCESS(200, "mock_items.json"),
    ITEMS_EMPTY(200, "mock_items_empty.json"),
    ITEMS_SERVER_ERROR(500, null),
    ITEMS_NOT_FOUND(404, "mock_error_404.json");

    fun toResponse(): MockResponse {
        return MockResponse().apply {
            setResponseCode(code)
            bodyFile?.let { setBody(MockResponses.read(it)) }
        }
    }

    object MockResponses {
        fun read(fileName: String): String {
            return InstrumentationRegistry.getInstrumentation()
                .context
                .assets
                .open(fileName)
                .bufferedReader()
                .use { it.readText() }
        }
    }
}