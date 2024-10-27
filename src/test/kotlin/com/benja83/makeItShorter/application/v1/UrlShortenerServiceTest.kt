package com.benja83.makeItShorter.application.v1

import com.benja83.makeItShorter.application.UrlRepositoryInterface
import com.benja83.makeItShorter.domain.Url
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
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
    lateinit var inMemoryUrlRepository: UrlRepositoryInterface

    private lateinit var urlShortenerService: UrlShortenerService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        urlShortenerService = UrlShortenerService(seedProvider, inMemoryUrlRepository)
    }

    @Nested
    inner class MakeShortTest {
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

    @Nested
    inner class RetrieveLongUrlFromTest {
        @Test
        fun `should return the long url from the short url suffix`() {
            val shortUrlSuffix = "1/e52af45"
            val expectedLongUrl = "https://example.org/example"
            val urlInRepository = Url(URI(expectedLongUrl).toURL(), shortUrlSuffix)
            whenever(inMemoryUrlRepository.findByShortUrlSuffix(shortUrlSuffix)).thenReturn(urlInRepository)

            val longUrl = urlShortenerService.retrieveLongUrlFrom(shortUrlSuffix)

            assertEquals(expectedLongUrl, longUrl)
        }

        @Test
        fun `should return null when short url suffix does not exists in the repository`() {
            val shortUrlSuffix = "1/e52af45"
            whenever(inMemoryUrlRepository.findByShortUrlSuffix(shortUrlSuffix)).thenReturn(null)

            val longUrl = urlShortenerService.retrieveLongUrlFrom(shortUrlSuffix)

            assertNull(longUrl)
        }
    }
}
