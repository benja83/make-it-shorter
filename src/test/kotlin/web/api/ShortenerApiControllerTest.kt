package web.api

import com.benja83.urlShortener.application.v1.UrlShortenerService
import web.api.v1.ShortenerApiController
import web.api.v1.UrlRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
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
        val shortUrl = URI("http://mis1/shortUrl").toURL()

        `when`(urlShortenerService.makeShort(urlToShorten)).thenReturn(shortUrl)

        val request = UrlRequest(urlToShorten)
        val response: ResponseEntity<String> = shortenerApiController.createShortUrl(request)

        assertEquals(shortUrl.toString(), response.body)
        assertEquals(200, response.statusCode.value())
    }
}
