package com.benja83.makeItShorter.application

import com.benja83.makeItShorter.domain.Url

interface UrlShortenerServiceInterface {
    val version: String

    fun makeShort(url: Url): String

    fun retrieveLongUrlFrom(shortUrl: String): String?
}
