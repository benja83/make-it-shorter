package com.benja83.makeItShorter.application.v1

import com.benja83.makeItShorter.application.UrlRepositoryInterface
import com.benja83.makeItShorter.application.UrlShortenerServiceInterface
import com.benja83.makeItShorter.domain.Url
import org.springframework.stereotype.Service
import java.net.URL

@Service
class UrlShortenerService(
    private val seedProvider: SeedProvider,
    private val inMemoryUrlRepository: UrlRepositoryInterface
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
