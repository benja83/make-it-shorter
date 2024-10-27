package com.benja83.urlShortener.web.api.v1

import com.benja83.urlShortener.application.v1.UrlShortenerService
import org.junit.jupiter.api.Assertions.assertEquals
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
}
