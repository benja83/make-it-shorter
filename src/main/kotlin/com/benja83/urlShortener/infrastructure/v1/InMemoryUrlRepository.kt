package com.benja83.urlShortener.infrastructure.v1

import com.benja83.urlShortener.UrlRepositoryInterface
import com.benja83.urlShortener.domain.Url
import java.net.URI

typealias LongUrl = String
typealias ShortUrlSuffix = String

class InMemoryUrlRepository : UrlRepositoryInterface {
    private val mapLongToShortUrlSuffix = HashMap<LongUrl, ShortUrlSuffix>()

    override fun save(url: Url) {
        mapLongToShortUrlSuffix[url.longUrl.toString()] = url.shortUrlSuffix
    }

    override fun findByLongUrl(longUrl: String): Url? {
        return mapLongToShortUrlSuffix[longUrl]?.let {
            Url(URI(longUrl).toURL(), it)
        }
    }
}