# make-it-shorter

## Description

`make-it-shorter` is a URL shortening service built with Kotlin and Spring Boot. It provides functionality to shorten
long URLs and retrieve the original long URLs from the shortened versions.

## Features

- **Shorten URL**: Converts a long URL into a shorter version.
- **Retrieve Long URL**: Retrieves the original long URL from a shortened URL.

## Endpoints

### POST /api/v1/shorten

<details>
    <summary>Creates a shortened URL from a given long URL.</summary>
    <strong>Request:</strong>
    <ul>
      <li>Method: POST</li>
      <li>URL: <code>/api/v1/shorten</code></li>
      <li>Body:
<pre><code>{
  "url": "http://example.com"
}
</code></pre>
      </li>
    </ul>
    <strong>Response:</strong>
    <ul>
      <li>Status: 200 OK</li>
      Body:
<pre><code>{
  "shortUrl": "http://localhost:8080/1/shortUrlSuffix"
}
</code></pre>
        <li>Status: 400 BAD REQUEST</li>
        when the request body is not a valid Url.
    </ul>
</details>

### GET /1/{shortUrlSuffix}

<details>
    <summary>Retrieves the original long URL from the given short URL suffix.</summary>
    <strong>Request:</strong>
    <ul>
      <li>Method: GET</li>
      <li>URL: /1/{shortUrlSuffix}</li>
    </ul>
    <strong>Response:</strong>
    <ul>
      <li>Status: 200 OK</li>
    Body:
<pre><code>{
  "longUrl": "http://example.com"
}
</code></pre>
        <li>Status: 404 Not Found (if the short URL suffix does not exist)</li>
    </ul>
</details>

## Running the Application
To run the application, use the following command:

```bash
./gradlew bootRun
```

## Build and test the application
To build and test the application, use the following command:

```bash
./gradlew clean build
```
**Notes:** the build run detekt to scan the code for code smells and style violations. More info https://detekt.dev/

## Docs

### Architecture Decision Records (ADRs)

Architectural Decision Records (ADRs) are stored in [this directory](./docs/adr).

### Diagrams

Diagrams are stored in [this directory](./docs/diagrams).

We use diagram.io connected to github to create diagrams. More info https://app.diagrams.net/

### Tradeoffs

- By choosing the current architecture, we have made the following tradeoffs:
    - adding initial complexity and learning curve for developers to understand the architecture and how to work with it.
    - the system don't persist the data after the application is stopped.
    - the system is limited to a maximum of urls to ensure no risk of short url collision.
- By adding versioning:
    - we increase the complexity of the codebase.
    - we make easy to evolve the solution (adding an external database, change the short url generation algorithm, etc).
- By using in memory database:
    - we are not validating that a short url suffix is unique.

### What if I have to start again

- Simpler architecture: I would start with a simpler architecture, without versioning, and with a simple in memory database to store the urls.
- Store full sha1 hash: I would store the full sha1 hash of the long url in the database to ensure that the short url suffix is unique.
- Generate short url suffix with base 62 conversion of uuid: generate a UUID for each long url and convert it to base 62 to generate the short url suffix.
