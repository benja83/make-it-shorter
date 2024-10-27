package com.benja83.makeItShorter.application.v1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URI


class SeedProviderTest {
    @Test
    fun `should return first 7 caracters of the SHA-1 of the URL`() {
        val originalUrl = URI("https://example.org/example").toURL()
        val seedResult = SeedProvider().provideSeedFor(originalUrl)
        val expectedSeed = "504149e"

        assertEquals(expectedSeed, seedResult)
    }
}
