package com.benja83.urlShortener.domain.exceptions

class NullUrlException : IllegalArgumentException("Long url and short url cannot be both blank")
