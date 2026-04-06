
---

## `docs/architecture/system-overview.md`

**Path:** `docs/architecture/system-overview.md`

```md
# System Overview

## Purpose

This platform monitors transaction activity and evaluates fraud risk in near real time.

## Main Responsibilities

1. Accept transaction data
2. Persist transactions
3. Publish transaction-created events
4. Evaluate fraud rules asynchronously
5. Store fraud decisions
6. Expose live updates to the dashboard
7. Provide MCP tools for AI agents

## Service Responsibilities

### transaction-api-service
Responsible for:
- transaction ingestion
- request validation
- persistence of incoming transaction data
- publishing transaction-created events to RabbitMQ

### fraud-worker-service
Responsible for:
- consuming transaction events from RabbitMQ
- evaluating fraud rules
- using Redis for fast state and recent activity checks
- storing fraud decisions
- publishing live update events for dashboard usage

### mcp-server-service
Responsible for:
- exposing MCP tools
- safely reading selected platform data
- avoiding ownership of core transaction ingestion logic

### frontend-web
Responsible for:
- monitoring dashboard UI
- suspicious transaction views
- charts and user activity visibility
- real-time updates

## Infrastructure Responsibilities

### PostgreSQL
Used as the main relational data store for:
- transactions
- fraud decisions
- future audit/history data

### Redis
Used for:
- fast recent-activity lookups
- cache/state support for fraud rules
- future short-lived monitoring data

### RabbitMQ
Used for:
- asynchronous transaction event publishing
- worker-side fraud processing flow
- decoupled service communication

## Local Development Foundation

For local development, the platform currently uses Docker Compose to run:
- PostgreSQL on port 5432
- Redis on port 6379
- RabbitMQ on port 5672
- RabbitMQ Management UI on port 15672

Backend service ports:
- transaction-api-service: 8081
- fraud-worker-service: 8082
- mcp-server-service: 8083

Frontend development port:
- frontend-web: 5173

## Current Phase Scope

Current foundation covers:
- monorepo structure
- backend service skeletons
- local infrastructure setup
- runbook and architecture documentation

Business logic, fraud rules, realtime messaging implementation, and MCP protocol logic are intentionally deferred to later phases.