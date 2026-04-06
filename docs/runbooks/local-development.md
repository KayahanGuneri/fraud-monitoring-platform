# Local Development Runbook

## Purpose

This document explains how to start and verify the local development infrastructure for the Fraud Detection & Transaction Monitoring Platform with MCP Integration.

## Current Scope

This runbook covers the local infrastructure foundation for:
- PostgreSQL
- Redis
- RabbitMQ

It also documents the reserved service ports for backend and frontend applications.

## Default Local Ports

| Component | Port |
|---|---:|
| PostgreSQL | 5432 |
| Redis | 6379 |
| RabbitMQ | 5672 |
| RabbitMQ Management UI | 15672 |
| transaction-api-service | 8081 |
| fraud-worker-service | 8082 |
| mcp-server-service | 8083 |
| frontend-web | 5173 |

## Default Credentials

### PostgreSQL
- Database: `fraud_platform`
- Username: `fraud_user`
- Password: `fraud_pass`

### RabbitMQ
- Username: `fraud_admin`
- Password: `fraud_admin_pass`

## Prerequisites

Before running locally, make sure:
- Docker Desktop is installed
- Docker Desktop is running
- port 5432 is free or intentionally mapped
- port 6379 is free or intentionally mapped
- port 5672 is free or intentionally mapped
- port 15672 is free or intentionally mapped

## Start Local Infrastructure

From the project root:

```bash
docker compose up -d
Verify Containers
docker compose ps

Expected result:

postgres is running or healthy
redis is running or healthy
rabbitmq is running or healthy
Access RabbitMQ Management

Open in browser:

http://localhost:15672

Login with:

username: fraud_admin
password: fraud_admin_pass
Check Logs

To inspect all container logs:

docker compose logs

To inspect a single service:

docker compose logs postgres
docker compose logs redis
docker compose logs rabbitmq
Stop Local Infrastructure
docker compose down
Full Reset

This removes containers and named volumes:

docker compose down -v
Notes for Next Backend Phase

In the next backend phase, services will start connecting to:

PostgreSQL for persistence
RabbitMQ for asynchronous messaging
Redis for fast fraud-state lookups

The currently reserved backend ports are:

8081 for transaction-api-service
8082 for fraud-worker-service
8083 for mcp-server-service

The frontend development port is:

5173

---

## `.env.example`

**Path:** `.env.example`

```env
POSTGRES_DB=fraud_platform
POSTGRES_USER=fraud_user
POSTGRES_PASSWORD=fraud_pass

RABBITMQ_DEFAULT_USER=fraud_admin
RABBITMQ_DEFAULT_PASS=fraud_admin_pass

POSTGRES_PORT=5432
REDIS_PORT=6379
RABBITMQ_PORT=5672
RABBITMQ_MANAGEMENT_PORT=15672

TRANSACTION_API_PORT=8081
FRAUD_WORKER_PORT=8082
MCP_SERVER_PORT=8083
FRONTEND_PORT=5173