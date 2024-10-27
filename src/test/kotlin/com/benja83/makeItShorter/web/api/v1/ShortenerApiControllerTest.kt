package com.benja83.makeItShorter.web.api.v1

import com.benja83.makeItShorter.application.v1.UrlShortenerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import org.springframework.mock.web.MockHttpServletRequest
import java.net.URI

class ShortenerApiControllerTest {

    private lateinit var shortenerApiController: ShortenerApiController

    @Mock
    private lateinit var urlShortenerService: UrlShortenerService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        shortenerApiController = ShortenerApiController(urlShortenerService)
    }

    @Test
    fun `should return short url when valid url is posted`() {
        val urlToShorten = URI("http://example.com").toURL()
        val shortUrlSuffix = "1/shortUrl"

        `when`(urlShortenerService.makeShort(urlToShorten)).thenReturn(shortUrlSuffix)

        val requestBody = UrlRequest(urlToShorten)
        val request = MockHttpServletRequest().let {
            it.scheme = "http"
            it.serverName = "localhost"
            it.serverPort = 8080
            it.requestURI = "/api/v1/shorten"
            it
        }
        val expectedShortUrl = "http://localhost:8080/$shortUrlSuffix"

        val response: ResponseEntity<String> = shortenerApiController.createShortUrl(requestBody, request)

        assertEquals(expectedShortUrl, response.body)
        assertEquals(200, response.statusCode.value())
    }

    @Test
    fun `should return long url when short belongs to a long url`() {
        val shortUrlSuffix = "shortUrl"
        val fullShortUrlSuffix = "1/shortUrl"
        val longUrl = "http://example.com"

        `when`(urlShortenerService.retrieveLongUrlFrom(fullShortUrlSuffix)).thenReturn(longUrl)

        val response: ResponseEntity<String> = shortenerApiController.retrieveLongUrl(shortUrlSuffix)

        assertEquals(longUrl, response.body)
        assertEquals(200, response.statusCode.value())
    }

    @Test
    fun `should return a 404 when short url suffix does not correspond to any long url`() {
        val shortUrlSuffix = "shortUrl"
        val fullShortUrlSuffix = "1/shortUrl"

        `when`(urlShortenerService.retrieveLongUrlFrom(fullShortUrlSuffix)).thenReturn(null)

        val response: ResponseEntity<String> = shortenerApiController.retrieveLongUrl(shortUrlSuffix)

        assertNull(response.body)
        assertEquals(404, response.statusCode.value())
    }
}
