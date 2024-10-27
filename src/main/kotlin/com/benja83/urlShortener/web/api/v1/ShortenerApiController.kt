package com.benja83.urlShortener.web.api.v1

import com.benja83.urlShortener.application.v1.UrlShortenerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.net.URL


@RestController
@RequestMapping("/api/v1")
class ShortenerApiController(private val urlShortenerService: UrlShortenerService) {

    @PostMapping("/shorten")
    @ResponseBody
    fun createShortUrl(@RequestBody request: UrlRequest): ResponseEntity<String> {
        val shortUrl = urlShortenerService.makeShort(request.url)
        return ResponseEntity.ok(shortUrl.toString())
    }

}

data class UrlRequest(val url: URL)
