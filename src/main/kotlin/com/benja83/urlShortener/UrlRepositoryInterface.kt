package com.benja83.urlShortener

import com.benja83.urlShortener.domain.Url

interface UrlRepositoryInterface {
    fun save(url: Url)
    fun findByLongUrl(longUrl: String): Url?
}
