package application.v1

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.net.URI
import kotlin.test.assertEquals

class UrlShortenerServiceTest {
    @Mock
    lateinit var seedProvider: SeedProvider

    private lateinit var urlShortenerService: UrlShortenerService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        urlShortenerService = UrlShortenerService(seedProvider)
    }

    @Test
    fun `should return an url with a short url with 'mis' hostname and version 1`(){
        val originalUrl = URI("https://example.org/example").toURL()
        val randomSeed = "e52af45"
        whenever(seedProvider.provideSeedFor(originalUrl)).thenReturn(randomSeed)
        val expectedUrl = URI("https://mis1/$randomSeed").toURL()

        val url = urlShortenerService.makeShort(originalUrl)

        assertEquals(expectedUrl, url)
    }
}
