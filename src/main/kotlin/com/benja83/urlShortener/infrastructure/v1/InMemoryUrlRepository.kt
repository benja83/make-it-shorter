package com.benja83.urlShortener.infrastructure.v1

import com.benja83.urlShortener.UrlRepositoryInterface
import com.benja83.urlShortener.domain.Url
import org.springframework.stereotype.Repository

typealias LongUrl = String
typealias ShortUrlSuffix = String

@Repository
class InMemoryUrlRepository : UrlRepositoryInterface {
    private val mapLongToShortUrlSuffix = HashMap<LongUrl, Url>()
    private val mapShortUrlSuffixToLongUrl = HashMap<ShortUrlSuffix, Url>()

    override fun save(url: Url) {
        mapLongToShortUrlSuffix[url.longUrl.toString()] = url
        mapShortUrlSuffixToLongUrl[url.shortUrlSuffix] = url
    }

    override fun findByLongUrl(longUrl: String): Url? {
        return mapLongToShortUrlSuffix[longUrl]
    }

    override fun findByShortUrlSuffix(shortUrlSuffix: String): Url? {
        return mapShortUrlSuffixToLongUrl[shortUrlSuffix]
    }
}
