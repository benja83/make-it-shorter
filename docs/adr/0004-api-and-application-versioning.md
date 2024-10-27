# 4. API and Application versioning

Date: 2024-10-27

## Status

Accepted

## Context

Given the iterative building of the application and the pragmatical approach taken (see the adr [0003](./0003-v1-shortening-logic.md)), it is important to have a versioning strategy in place to manage changes and ensure backward compatibility.

This is especially important for the API, as clients may rely on specific endpoints and behaviors. Additionally, the application logic may evolve over time, requiring a way to manage different versions of the codebase.
## Decision

We will implement versioning for both the API and the Application layer. This involves:

- **API Versioning**: Each version of the API will be accessible via a versioned URL path (e.g., `/api/v1/` and `/api/v2/`). This allows clients to specify which version of the API they are using.
- **Application Layer Versioning**: The application logic for each version will be separated into different packages or modules. This ensures that changes in one version do not affect the other.

## Consequences

- **Positive**:
    - **Backward Compatibility**: Users can continue using the old version while new features are added to the new version.
    - **Incremental Upgrades**: Allows for gradual migration of users to the new version.
    - **Decoupled Implementations**: Changes in one version do not impact the other, reducing the risk of introducing bugs.

- **Negative**:
    - **Increased Complexity**: Maintaining multiple versions increases the complexity of the codebase.
    - **Resource Overhead**: Running multiple versions may require additional resources.
    - **Coordination Effort**: Ensuring consistency and compatibility between versions requires careful planning and coordination.
