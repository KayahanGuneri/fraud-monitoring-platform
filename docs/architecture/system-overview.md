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
- validation
- persistence
- publishing transaction-created events

### fraud-worker-service
Responsible for:
- consuming transaction events
- evaluating fraud rules
- reading/writing fast state in Redis
- storing fraud decisions
- publishing realtime update events

### mcp-server-service
Responsible for:
- exposing MCP tools
- safe read-only style access to selected platform data

### frontend-web
Responsible for:
- monitoring dashboard
- suspicious transaction views
- realtime updates
- charts and user activity visibility