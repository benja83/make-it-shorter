package com.benja83.makeItShorter.domain

import com.benja83.makeItShorter.domain.exceptions.NullUrlException
import java.net.URL

data class Url(val longUrl: URL, val shortUrlSuffix: String) {
    init {
        if (shortUrlSuffix.isBlank()) {
            throw NullUrlException()
        }
    }
}
