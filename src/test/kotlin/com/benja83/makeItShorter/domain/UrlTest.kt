package com.benja83.makeItShorter.domain

import com.benja83.makeItShorter.domain.exceptions.UrlSuffixBlankException
import com.benja83.makeItShorter.domain.exceptions.UrlSuffixValidationException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.net.URI

class UrlTest {
    @Test
    fun `should throw a url suffix blank exception when short urlSuffix is blank`(){
        val longUrl = URI("https://example.org/example").toURL()
        assertThrows<UrlSuffixBlankException> { Url(longUrl, "") }
    }

    @Test
    fun `should throw a nullUrlException when short urlSuffix is blank`(){
        val longUrl = URI("https://example.org/example").toURL()
        assertThrows<UrlSuffixValidationException> { Url(longUrl, null).validate() }
    }
}
