
# AI Agent Spring Boot with OpenAI Integration

## Description

This is a Spring Boot application that exposes a REST API to interact with OpenAI's API. It provides a simple way to send prompts and receive AI-generated responses using the Chat Completions endpoint from OpenAI.

## Technologies Used

- Java 17
- Spring Boot 3.5.2
- Spring WebFlux
- Maven
- Lombok
- OpenAI API (via HTTP REST calls)

## Project Structure

```
openai/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/agent/openai/
│       │       ├── config/
│       │       ├── controller/
│       │       ├── dto/
│       │       └── service/
│       └── resources/
│           ├── application.properties
│           └── static/
├── pom.xml
├── mvnw / mvnw.cmd
└── .gitignore
```

## Configuration

You must add your OpenAI API Key in the `application.properties` file:

```
openai.api.key=YOUR_ACTUAL_OPENAI_API_KEY
```

Alternatively, you can configure it via environment variables or externalized configuration if needed.

## Running the Project Locally

### Prerequisites:
- Java 17+
- Maven

### Steps:

```bash
# Navigate to the project root
cd openai

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on port `8080` by default.

## API Endpoints

| HTTP Method | Endpoint          | Description                          |
|-------------|-------------------|--------------------------------------|
| POST        | `/ai/ask`         | Send a prompt and get AI response    |

### Example Request (POST `/ai/ask`):

**URL:**  
`http://localhost:8080/ai/ask`

**Body (JSON):**
```json
{
  "prompt": "Tell me a joke."
}
```

### Example Response:
```json
{
  "response": "Why don't programmers like nature? It has too many bugs."
}
```

## Features

- Asynchronous non-blocking calls with WebClient
- OpenAI API integration
- Clean architecture with separation between controller, service, DTOs, and configuration layers
- Lombok used for DTO boilerplate reduction

## Possible Improvements (Future Work)

- Add exception handling for API errors
- Parameterize AI model selection
- Implement caching for repeated prompts
- Add unit and integration tests
- Create Swagger/OpenAPI documentation

## How to Test the API

You can use **curl**, **Postman**, or any REST client to test:

```bash
curl -X POST http://localhost:8080/ai/ask \
  -H "Content-Type: application/json" \
  -d '{"prompt": "Give me an example of Java code using streams."}'
```

## License

This project is for educational purposes and does not include any production-level API key management. Be sure to handle your OpenAI keys securely in real deployments.
