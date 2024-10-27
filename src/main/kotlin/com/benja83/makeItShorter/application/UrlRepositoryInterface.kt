package com.benja83.makeItShorter.application

import com.benja83.makeItShorter.domain.Url

interface UrlRepositoryInterface {
    fun save(url: Url)
    fun findByLongUrl(longUrl: String): Url?
    fun findByShortUrlSuffix(shortUrlSuffix: String): Url?
}
