package com.benja83.urlShortener.application.v1

import org.springframework.stereotype.Component
import java.net.URL
import java.security.MessageDigest

private const val SEED_LENGTH = 7

@Component
class SeedProvider {
    fun provideSeedFor(url: URL): String {
        return sha1(url.toString()).take(SEED_LENGTH)
    }

    private fun sha1(input: String): String {
        val bytes = input.toByteArray()
        val messageDigest = MessageDigest.getInstance("SHA-1")
        val digestedBytes = messageDigest.digest(bytes)
        return digestedBytes.joinToString("") { "%02x".format(it) } // Convert to hex string
    }
}
