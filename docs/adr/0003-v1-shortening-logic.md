# 3. V1 shortening logic

Date: 2024-10-27

## Status

Accepted

## Context

We need a pragmatic approach to generate a unique identifier for each URL that we shorten. Using the first 7 characters of the SHA-1 hash of the URL provides a simple and efficient way to create these identifiers. The shortened URLs will be stored in an internal HashMap.

## Decision

We will use the first 7 characters of the SHA-1 hash of the URL as the unique identifier for the shortened URL. The mapping between the original URL and the shortened URL will be stored in an internal HashMap.

## Consequences

- **Positive**:
    - **Simplicity**: The approach is straightforward and easy to implement.
    - **Efficiency**: Generating the SHA-1 hash and extracting the first 7 characters is computationally inexpensive.
    - **In-Memory Storage**: Using a HashMap allows for fast lookups and insertions.

- **Negative**:
    - **Risk of Collision**: There is a possibility of hash collisions, where different URLs produce the same shortened identifier. 
    - **No Persistence**: The internal HashMap does not provide persistence, so all data will be lost when the application is restarted. The persistence will have to be implemented using another persistence store or another suffix generation mechanism.

## Notes

Probability of Collision with 7-Character SHAs
Using the Birthday Paradox approximation, a collision might be expected when around:
$$\sqrt{2^{28}} ≈ 2^{14} ≈ 16,384$$

So, if the repository has around 16,000 unique urls, the probability of encountering a 7-character collision becomes more noticeable.
