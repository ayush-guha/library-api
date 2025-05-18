# Assumptions

- Borrower email addresses are unique and case-insensitive.
- Books with the same ISBN must have the same title and author; otherwise, registration fails.
- Multiple copies of a book (same ISBN) are allowed, each with a unique ID.
- Only one borrower can borrow a specific book (by ID) at a time.
- Returning a book sets its lending record's `returnedAt` timestamp.
- The API does not support reservations or waiting lists.
- No authentication or authorization is implemented.
- Error responses are returned as plain text or JSON with a message.
- PostgreSQL is used for transactional integrity and ACID compliance.
