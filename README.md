# Fraud Detection & Transaction Monitoring Platform with MCP Integration

## Project Overview

This is a production-grade portfolio project designed to demonstrate:

* microservices architecture
* asynchronous processing
* rule-based fraud analysis
* PostgreSQL persistence
* Redis-based fast state and cache usage
* RabbitMQ event-driven messaging
* real-time dashboard updates
* MCP integration for AI agent tools

---

## Core Services

### Backend Services

* transaction-api-service
* fraud-worker-service
* mcp-server-service

### Frontend

* frontend-web

### Infrastructure

* PostgreSQL
* Redis
* RabbitMQ
* Docker
* Docker Compose

---

## Current Status

Completed foundations:

* monorepo bootstrap
* Git/GitHub setup
* Phase 1 backend service skeletons
* Phase 1 DevOps local infrastructure foundation

Current active work:

* Phase 2 transaction-api-service development
* Phase 2 DevOps local configuration refinement

---

## Planned Phases

* Phase 0: Bootstrap
* Phase 1: Foundation
* Phase 2: Transaction API
* Phase 3: Async Processing
* Phase 4: Fraud Engine
* Phase 5: Real-Time Monitoring
* Phase 6: MCP Integration
* Phase 7: Final Hardening

---

## Monorepo Structure

```
fraud-monitoring-platform/
  services/
    transaction-api-service/
    fraud-worker-service/
    mcp-server-service/
  apps/
    frontend-web/
  infra/
    docker-compose/
  docs/
    architecture/
    api/
    runbooks/
    prompts/
  shared/
    event-contracts/
    common-schemas/
  scripts/
```

---

## Local Development (Phase 2 DevOps)

### Infrastructure Services

Docker Compose provides:

* PostgreSQL
* Redis
* RabbitMQ

---

## Ports

| Service     | Host Port | Container Port |
| ----------- | --------- | -------------- |
| PostgreSQL  | 5434      | 5432           |
| Redis       | 6379      | 6379           |
| RabbitMQ    | 5672      | 5672           |
| RabbitMQ UI | 15672     | 15672          |

---

## Why PostgreSQL uses 5434

Port `5434` is used to avoid conflicts with a local PostgreSQL installation.

* Container: `5432`
* Host: `127.0.0.1:5434`

---

## Environment Variables

### Database

* DB_URL
* DB_USERNAME
* DB_PASSWORD

### RabbitMQ

* RABBITMQ_HOST
* RABBITMQ_PORT
* RABBITMQ_USERNAME
* RABBITMQ_PASSWORD

### Redis

* REDIS_HOST
* REDIS_PORT

### Server

* SERVER_PORT

See `.env.example` for reference.

---

## How to Run (Local)

### Step 1 — Start Infrastructure

Run Docker Compose:

* PostgreSQL starts on port `5434`
* Redis starts on port `6379`
* RabbitMQ starts on port `5672`

---

### Step 2 — Verify Containers

Check that all containers are running:

* PostgreSQL
* Redis
* RabbitMQ

---

### Step 3 — Run transaction-api-service

Start the Spring Boot application from IDE or terminal.

---

### Step 4 — Verify Application

Check:

* application starts without errors
* database connection works
* RabbitMQ connection works

Health endpoint:

```
http://localhost:8081/actuator/health
```

---

## Troubleshooting

### Database Connection Error

* Check PostgreSQL container is running
* Verify port `5434`
* Check DB credentials

---

### RabbitMQ Connection Error

* Check RabbitMQ container
* Verify port `5672`
* Check credentials

RabbitMQ UI:

```
http://localhost:15672
```

---

### Redis Issues

* Check Redis container
* Verify port `6379`

---

### Port Conflicts

If a container fails to start:

* another service may be using the port
* update docker-compose and env together

---

## Phase 2 DevOps Outcome

After this phase:

* environment variables are standardized
* local setup is clearly documented
* transaction-api-service config is externalized
* developer experience is improved

---

## Next Step

Current active work:

* Phase 3 Async Processing completed
* RabbitMQ-based event-driven communication implemented
* fraud-worker-service integrated with transaction-api-service

---

## Phase 3 Highlights

Phase 3 introduces the first production-style asynchronous flow:

- transaction-api-service publishes transaction-created events
- RabbitMQ handles message routing via exchange/queue/binding
- fraud-worker-service consumes events
- placeholder fraud processing pipeline is executed

Verified capabilities:

- transaction persistence in PostgreSQL
- event publishing to RabbitMQ
- queue creation and binding via Spring AMQP
- event consumption by worker service
- end-to-end async flow validation
