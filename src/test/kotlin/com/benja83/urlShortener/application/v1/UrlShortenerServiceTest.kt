package com.benja83.urlShortener.application.v1

import com.benja83.urlShortener.domain.Url
import com.benja83.urlShortener.infrastructure.v1.InMemoryUrlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.net.URI

class UrlShortenerServiceTest {
    @Mock
    lateinit var seedProvider: SeedProvider

    @Mock
    lateinit var inMemoryUrlRepository: InMemoryUrlRepository

    private lateinit var urlShortenerService: UrlShortenerService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        urlShortenerService = UrlShortenerService(seedProvider, inMemoryUrlRepository)
    }

    @Test
    fun `should return the short url suffix starting with version 1`() {
        val originalUrl = URI("https://example.org/example").toURL()
        val randomSeed = "e52af45"
        whenever(seedProvider.provideSeedFor(originalUrl)).thenReturn(randomSeed)
        val expectedSuffixUrl = "1/$randomSeed"

        val url = urlShortenerService.makeShort(originalUrl)

        assertEquals(expectedSuffixUrl, url)
    }

    @Test
    fun `should save url and its short url suffix in the repository`() {
        val originalUrl = URI("https://example.org/example").toURL()
        val randomSeed = "e52af45"
        whenever(seedProvider.provideSeedFor(originalUrl)).thenReturn(randomSeed)
        val expectedSuffixUrl = "1/$randomSeed"

        urlShortenerService.makeShort(originalUrl)

        verify(inMemoryUrlRepository).save(Url(originalUrl, expectedSuffixUrl))
    }
}
