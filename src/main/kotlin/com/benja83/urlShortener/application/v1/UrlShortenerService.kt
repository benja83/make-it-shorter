package com.benja83.urlShortener.application.v1

import com.benja83.urlShortener.application.UrlShortenerServiceInterface
import com.benja83.urlShortener.domain.Url
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URL

@Service
class UrlShortenerService(private val seedProvider: SeedProvider) : UrlShortenerServiceInterface {
    override val version = "1"

    override fun makeShort(url: URL): String {
        return seedProvider.provideSeedFor(url).let {
            "$version/$it"
        }
    }
}
