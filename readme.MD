# Full Stack Task App

*Requirements*

* Maven and Java (17+) and Go(1.25+)
* Node and NPM
* Docker

### How to Run

#### 1. Build Backend (Java Spring Boot)

```bash
cd task-service
mvn clean package -DskipTests
```
#### 2. Build Backend (Go)

```bash
cd go-task-service
go build
```

#### 2. Frontend (React)

```bash
cd my-react-app
npm install
```

#### 3. Run with Docker (Recommended)

```bash
docker compose up --build
```

Note: its recommended to run with docker but you can also seperately run it with spring boot and npm but you might have to make some changes

### To Visit

- Frontend: [http://localhost:8000](http://localhost:8000)
- Backend Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
