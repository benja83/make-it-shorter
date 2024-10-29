package com.benja83.makeItShorter.domain

import com.benja83.makeItShorter.domain.exceptions.UrlSuffixBlankException
import com.benja83.makeItShorter.domain.exceptions.UrlSuffixValidationException
import java.net.URL

data class Url(val longUrl: URL, val shortUrlSuffix: String?) {
    init {
        shortUrlSuffix?.let {
            if(shortUrlSuffix.isBlank()) {
                throw UrlSuffixBlankException()
            }
        }
    }

    fun validate() {
        shortUrlSuffix ?: throw UrlSuffixValidationException()
    }
}
