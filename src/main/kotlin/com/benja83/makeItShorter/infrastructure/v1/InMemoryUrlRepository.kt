package com.benja83.makeItShorter.infrastructure.v1

import com.benja83.makeItShorter.application.UrlRepositoryInterface
import com.benja83.makeItShorter.domain.Url
import org.springframework.stereotype.Repository

typealias LongUrl = String
typealias ShortUrlSuffix = String

@Repository
class InMemoryUrlRepository : UrlRepositoryInterface {
    private val mapLongToShortUrlSuffix = HashMap<LongUrl, Url>()
    private val mapShortUrlSuffixToLongUrl = HashMap<ShortUrlSuffix, Url>()

    override fun save(url: Url) {
        url.validate()
        findByShortUrlSuffix(url.shortUrlSuffix!!)
            ?.takeIf { it != url }
            ?.let { throw UrlCollisionException() }
            ?: run {
                mapLongToShortUrlSuffix[url.longUrl.toString()] = url
                mapShortUrlSuffixToLongUrl[url.shortUrlSuffix] = url
            }
    }

    override fun findByLongUrl(longUrl: String): Url? {
        return mapLongToShortUrlSuffix[longUrl]
    }

    override fun findByShortUrlSuffix(shortUrlSuffix: String): Url? {
        return mapShortUrlSuffixToLongUrl[shortUrlSuffix]
    }
}
