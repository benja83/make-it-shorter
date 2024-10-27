package application.v1

import application.UrlShortenerServiceInterface
import domain.Url
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URL

@Service
class UrlShortenerService(private val seedProvider: SeedProvider) : UrlShortenerServiceInterface {
    override val misHost = "https://mis"
    override val version = "1"

    override fun makeShort(url: URL): URL {
        return seedProvider.provideSeedFor(url).let {
            val shortUrl = URI("$misHost$version/$it").toURL()
            Url(longUrl = url, shortUrl = shortUrl).shortUrl!!
        }
    }
}
