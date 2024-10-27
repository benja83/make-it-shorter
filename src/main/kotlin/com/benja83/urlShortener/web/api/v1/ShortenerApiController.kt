package com.benja83.urlShortener.web.api.v1

import com.benja83.urlShortener.application.UrlShortenerServiceInterface
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.net.URL


@RestController
@RequestMapping("/api/v1")
class ShortenerApiController(private val urlShortenerService: UrlShortenerServiceInterface) {

    @PostMapping("/shorten")
    @ResponseBody
    fun createShortUrl(@RequestBody bodyRequest: UrlRequest, request: HttpServletRequest): ResponseEntity<String> {
        val shortUrl = request
            .let { "${it.scheme}://${it.serverName}:${it.serverPort}" }
            .let { host: String ->
                "$host/"+ urlShortenerService.makeShort(bodyRequest.url)
            }
        return ResponseEntity.ok(shortUrl)
    }

}

data class UrlRequest(val url: URL)
