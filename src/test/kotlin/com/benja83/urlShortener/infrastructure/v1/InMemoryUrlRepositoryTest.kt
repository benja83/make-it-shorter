package com.benja83.urlShortener.infrastructure.v1

import com.benja83.urlShortener.domain.Url
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI
import kotlin.test.assertNull

class InMemoryUrlRepositoryTest {
    private lateinit var inMemoryUrlRepository: InMemoryUrlRepository

    @BeforeEach
    fun setUp() {
        inMemoryUrlRepository = InMemoryUrlRepository()
    }

    @Test
    fun `should save the url and find by long url in the repository`() {
        val longUrl = "https://example.org/example"
        val url = Url(URI(longUrl).toURL(), "1/e52af45")
        inMemoryUrlRepository.save(url)
        val result = inMemoryUrlRepository.findByLongUrl(longUrl)

        assertEquals(url, result)
    }

    @Test
    fun `should return null when find by long url not present in the repository`() {
        val longUrl = "https://example.org/example"

        val result = inMemoryUrlRepository.findByLongUrl(longUrl)

        assertNull(result)
    }
}
