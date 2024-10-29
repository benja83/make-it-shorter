package com.benja83.makeItShorter.infrastructure.v1

import com.benja83.makeItShorter.domain.Url
import com.benja83.makeItShorter.domain.exceptions.UrlSuffixValidationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.net.URI
import kotlin.test.assertNull

class InMemoryUrlRepositoryTest {
    private lateinit var inMemoryUrlRepository: InMemoryUrlRepository

    @BeforeEach
    fun setUp() {
        inMemoryUrlRepository = InMemoryUrlRepository()
    }

    @Nested
    inner class SaveTest {
        @Test
        fun `should save the url and find by long url in the repository`() {
            val longUrl = "https://example.org/example"
            val url = Url(URI(longUrl).toURL(), "1/e52af45")
            inMemoryUrlRepository.save(url)
            val result = inMemoryUrlRepository.findByLongUrl(longUrl)

            assertEquals(url, result)
        }

        @Test
        fun `should not raise any error when saving the same url twice`() {
            val longUrl = "https://example.org/example"
            val shortUrlSuffix = "1/e52af45"
            val url = Url(URI(longUrl).toURL(), shortUrlSuffix)

            inMemoryUrlRepository.save(url)
            assertDoesNotThrow { inMemoryUrlRepository.save(url) }
        }

        @Test
        fun `should raise an error when saving 2 url with same url short suffix and distinct long url`() {
            val longUrl1 = "https://example.org/example"
            val longUrl2 = "https://example.org/example/example"
            val shortUrlSuffix = "1/e52af45"
            val url1 = Url(URI(longUrl1).toURL(), shortUrlSuffix)
            val url2 = Url(URI(longUrl2).toURL(), shortUrlSuffix)

            inMemoryUrlRepository.save(url1)
            assertThrows<UrlCollisionException>  { inMemoryUrlRepository.save(url2) }
        }

        @Test
        fun `should raise an validation exception when url is not valid`() {
            val longUrl = "https://example.org/example"
            val url = Url(URI(longUrl).toURL(), null)

            assertThrows<UrlSuffixValidationException>  { inMemoryUrlRepository.save(url) }
        }
    }

    @Nested
    inner class FindTest {
        @Test
        fun `should return null when find by long url not present in the repository`() {
            val longUrl = "https://example.org/example"

            val result = inMemoryUrlRepository.findByLongUrl(longUrl)

            assertNull(result)
        }
    }

    @Test
    fun `should save the url and find by short url suffix in the repository`() {
        val longUrl = "https://example.org/example"
        val shortUrlSuffix = "1/e52af45"
        val url = Url(URI(longUrl).toURL(), shortUrlSuffix)
        inMemoryUrlRepository.save(url)
        val result = inMemoryUrlRepository.findByShortUrlSuffix(shortUrlSuffix)

        assertEquals(url, result)
    }

    @Test
    fun `should return null when find by short url suffix not present in the repository`() {
        val shortUrlSuffix = "not present in the repository"

        val result = inMemoryUrlRepository.findByShortUrlSuffix(shortUrlSuffix)

        assertNull(result)
    }
}
