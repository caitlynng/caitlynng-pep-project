# Project: Social Media Blog API

## Overview
This project serves as the backend for a hypothetical social media app, focusing on managing user accounts and messages. Acting as a micro-blogging or messaging app, it provides functionality for user registration, login, message creation, retrieval, updating, and deletion. The application is built with a backend framework, and it leverages a database to persist user and message information.

## Features Implemented

1. **User Registration:**
   - **Endpoint:** `POST localhost:8080/register`
   - Validates username, password, and avoids duplicates.
   - Successful registration responds with a JSON of the account.
   - **Status:** 200 OK on success, 400 Client error on failure

2. **User Login:**
   - **Endpoint:** `POST localhost:8080/login`
   - Validates login credentials.
   - Successful login responds with a JSON of the account.
   - **Status:** 200 OK on success, 401 Unauthorized on failure

3. **Message Creation:**
   - **Endpoint:** `POST localhost:8080/messages`
   - Validates message_text and user existence.
   - Successful creation responds with a JSON of the message.
   - **Status:** 200 OK on success, 400 Client error on failure

4. **Retrieve All Messages:**
   - **Endpoint:** `GET localhost:8080/messages`
   - Responds with a JSON list of all messages from the database.
   - **Status:** 200 OK

5. **Retrieve Message by ID:**
   - **Endpoint:** `GET localhost:8080/messages/{message_id}`
   - Responds with a JSON of the message by ID.
   - **Status:** 200 OK

6. **Delete Message:**
   - **Endpoint:** `DELETE localhost:8080/messages/{message_id}`
   - Deletes a message and responds with the deleted message.
   - **Status:** 200 OK

7. **Update Message:**
   - **Endpoint:** `PATCH localhost:8080/messages/{message_id}`
   - Updates a message text by ID.
   - Successful update responds with the full updated message.
   - **Status:** 200 OK on success, 400 Client error on failure

8. **Retrieve User's Messages:**
   - **Endpoint:** `GET localhost:8080/accounts/{account_id}/messages`
   - Responds with a JSON list of messages by a specific user.
   - **Status:** 200 OK

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data
- MySQL

## How to Set Up/Get Started

1. Ensure you have Java and Maven installed.
2. Clone the repository.
3. Run the application using Maven or your preferred IDE.
4. The application will run on `localhost:8080`.

## Usage

- Register a new account using `POST localhost:8080/register`.
- Log in with your account via `POST localhost:8080/login`.
- Create, retrieve, update, or delete messages.
- Retrieve all messages or messages by a specific user.

## Contributors

- Cailtyn Nguyen

## License

- Revature
