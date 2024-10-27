package com.benja83.urlShortener.application.v1

import com.benja83.urlShortener.application.UrlShortenerServiceInterface
import com.benja83.urlShortener.domain.Url
import com.benja83.urlShortener.infrastructure.v1.InMemoryUrlRepository
import org.springframework.stereotype.Service
import java.net.URL

@Service
class UrlShortenerService(
    private val seedProvider: SeedProvider,
    private val inMemoryUrlRepository: InMemoryUrlRepository
) : UrlShortenerServiceInterface {
    override val version = "1"

    override fun makeShort(url: URL): String {
        return seedProvider.provideSeedFor(url).let {
            inMemoryUrlRepository.save(Url(url, "$version/$it"))
            "$version/$it"
        }
    }

    override fun retrieveLongUrlFrom(shortUrl: String): String? {
        return inMemoryUrlRepository.findByShortUrlSuffix(shortUrl)?.longUrl?.toString()
    }
}
