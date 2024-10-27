package domain

import com.benja83.urlShortener.domain.Url
import com.benja83.urlShortener.domain.exceptions.NullUrlException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UrlTest {
    @Test
    fun `should throw a nullUrlException when both long and short url are null`(){
        assertThrows<NullUrlException> { Url(null, null) }
    }
}
