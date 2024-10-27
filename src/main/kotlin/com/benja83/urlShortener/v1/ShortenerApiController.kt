package com.benja83.urlShortener.v1

import com.benja83.urlShortener.application.v1.UrlShortenerService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URL


@RestController
@RequestMapping("/api/v1")
class ShortenerApiController(private val urlShortenerService: UrlShortenerService) {

    @PostMapping("/shorten", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createShortUrl(@RequestBody request: UrlRequest): ResponseEntity<String> {
        val shortUrl = urlShortenerService.makeShort(request.url)
        return ResponseEntity.ok(shortUrl.toString())
    }

}

data class UrlRequest(val url: URL)
