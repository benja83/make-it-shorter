package domain

import domain.exceptions.NullUrlException
import java.net.URL

data class Url(val longUrl: URL?, val shortUrl: URL?) {
    init {
        if (longUrl == null && shortUrl == null) { throw NullUrlException() }
    }
}
