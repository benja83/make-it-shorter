package com.benja83.urlShortener.domain

import com.benja83.urlShortener.domain.exceptions.NullUrlException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.net.URI

class UrlTest {
    @Test
    fun `should throw a nullUrlException when short urlSuffix is blank`(){
        val longUrl = URI("https://example.org/example").toURL()
        assertThrows<NullUrlException> { Url(longUrl, "") }
    }
}
