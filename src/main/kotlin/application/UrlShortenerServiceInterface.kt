package application

import java.net.URL

interface UrlShortenerServiceInterface {
    val misHost: String
    val version: String

    fun makeShort(url: URL): URL
}
