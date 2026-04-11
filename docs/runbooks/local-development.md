# Local Development Runbook

## Purpose

This document explains how to start and verify the local development infrastructure for the Fraud Detection & Transaction Monitoring Platform with MCP Integration.

---

## Current Scope

This runbook covers the local infrastructure foundation for:

* PostgreSQL
* Redis
* RabbitMQ

It also documents the reserved service ports for backend and frontend applications.

---

## Default Local Ports

| Component               |  Port |
| ----------------------- | ----: |
| PostgreSQL              |  5432 |
| Redis                   |  6379 |
| RabbitMQ                |  5672 |
| RabbitMQ Management UI  | 15672 |
| transaction-api-service |  8081 |
| fraud-worker-service    |  8082 |
| mcp-server-service      |  8083 |
| frontend-web            |  5173 |

---

## Default Credentials

### PostgreSQL

* Database: `fraud_platform`
* Username: `fraud_user`
* Password: `fraud_pass`

### RabbitMQ

* Username: `fraud_admin`
* Password: `fraud_admin_pass`

---

## Prerequisites

Before running locally, make sure:

* Docker Desktop is installed
* Docker Desktop is running
* Ports 5432, 6379, 5672, 15672 are available

---

## Start Local Infrastructure

From the project root:

```bash
docker compose up -d
```

Verify containers:

```bash
docker compose ps
```

Expected:

* postgres is running or healthy
* redis is running or healthy
* rabbitmq is running or healthy

---

## Access RabbitMQ Management UI

Open in browser:

```
http://localhost:15672
```

Login:

* username: fraud_admin
* password: fraud_admin_pass

---

## Check Logs

All logs:

```bash
docker compose logs
```

Single service logs:

```bash
docker compose logs postgres
docker compose logs redis
docker compose logs rabbitmq
```

---

## Stop Infrastructure

```bash
docker compose down
```

---

## Full Reset

```bash
docker compose down -v
```

---

## Notes for Next Backend Phase

In the next backend phase, services will connect to:

* PostgreSQL for persistence
* RabbitMQ for asynchronous messaging
* Redis for fast fraud-state lookups

---

# Async Flow Validation (Phase 3)

This section explains how to manually verify the asynchronous transaction flow using RabbitMQ.

---

## Step 1 — Start Infrastructure

```bash
docker compose up -d
```

---

## Step 2 — Start Services

Start both services:

* transaction-api-service
* fraud-worker-service

---

## Step 3 — Verify RabbitMQ Setup

Open:

```
http://localhost:15672
```

Login:

* username: fraud_admin
* password: fraud_admin_pass

---

## Step 4 — Check Exchange

Navigate:

```
Exchanges → fraud.transaction.exchange
```

Verify:

* exchange exists
* type is `direct`

---

## Step 5 — Check Queue

Navigate:

```
Queues → fraud.transaction.created.queue
```

Verify:

* queue exists
* messages = 0 (initially)

---

## Step 6 — Create Transaction

Send request:

```
POST /transactions
```

Example:

```json
{
  "userId": "user-1",
  "amount": 120.50,
  "currency": "USD",
  "merchant": "Amazon",
  "country": "US",
  "city": "New York",
  "latitude": 40.7128,
  "longitude": -74.0060
}
```

---

## Step 7 — Observe RabbitMQ

Check queue:

* message count may briefly increase
* then drop to 0 after consumer processes

---

## Step 8 — Verify Consumer Logs

Expected log:

```
Consumed transaction created event...
```

---

## Step 9 — End-to-End Verification

Checklist:

* transaction saved in DB
* event published
* message reached queue
* worker consumed event
* processing started

---

## Debug Tips

If queue is empty:

* producer may not be publishing

If messages stay in queue:

* consumer may not be running

If exchange missing:

* config issue

If binding missing:

* Rabbit config not loaded

Always verify:

1. exchange exists
2. queue exists
3. binding exists
4. producer logs
5. consumer logs

---

## Summary

Phase 3 ensures that:

* asynchronous communication is working
* services are decoupled
* RabbitMQ flow is observable and testable

This is the foundation for fraud processing in Phase 4.
