package com.benja83.urlShortener.application

import java.net.URL

interface UrlShortenerServiceInterface {
    val version: String

    fun makeShort(url: URL): String

    fun retrieveLongUrlFrom(shortUrl: String): String?
}
