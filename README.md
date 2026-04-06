# Fraud Detection & Transaction Monitoring Platform with MCP Integration

## Project Overview

This is a production-grade portfolio project designed to demonstrate:

- microservices architecture
- asynchronous processing
- rule-based fraud analysis
- PostgreSQL persistence
- Redis-based fast state and cache usage
- RabbitMQ event-driven messaging
- real-time dashboard updates
- MCP integration for AI agent tools

## Core Services

### Backend Services
- transaction-api-service
- fraud-worker-service
- mcp-server-service

### Frontend
- frontend-web

## Infrastructure
- PostgreSQL
- Redis
- RabbitMQ
- Docker
- Docker Compose

## Current Status

Completed foundations:
- monorepo bootstrap
- Git/GitHub setup
- Phase 1 backend service skeletons
- Phase 1 DevOps local infrastructure foundation

## Planned Phases

- Phase 0: Bootstrap / Zero-State Project Creation
- Phase 0.5: Git + GitHub Setup
- Phase 1: Foundation Skeletons
- Phase 2: Transaction API
- Phase 3: Async Processing
- Phase 4: Fraud Engine
- Phase 5: Real-Time Monitoring
- Phase 6: MCP Integration
- Phase 7: Final Hardening and Portfolio Delivery

## Monorepo Structure

```text
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