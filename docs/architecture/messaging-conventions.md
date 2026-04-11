# Messaging Conventions

## Purpose

This document defines the RabbitMQ messaging conventions used in the Fraud Detection & Transaction Monitoring Platform with MCP Integration.

Its goal is to keep asynchronous communication predictable, readable, and easy to extend across later phases.

---

## Current Messaging Scope

Phase 3 currently covers the first async flow:

* transaction-api-service publishes a transaction-created event
* fraud-worker-service consumes that event
* RabbitMQ is used as the broker between services

This is the first production-style event-driven communication path in the platform.

---

## Broker Technology

The platform uses:

* RabbitMQ
* Direct Exchange pattern
* JSON message payloads
* Spring AMQP for producer and consumer integration

---

## Naming Conventions

### Exchange Naming

Exchange names should follow this style:

fraud.<domain>.exchange

Current exchange:

fraud.transaction.exchange

Reason:

* fraud identifies the platform domain
* transaction identifies the business area
* exchange clearly marks the broker resource type

---

### Queue Naming

Queue names should follow this style:

fraud.<domain>.<event>.queue

Current queue:

fraud.transaction.created.queue

Reason:

* clear event intent
* easy to identify in RabbitMQ UI
* scalable for future queues such as fraud result, realtime update, dead-letter, and retry flows

---

### Routing Key Naming

Routing keys should follow this style:

<domain>.<event>

Current routing key:

transaction.created

Reason:

* concise
* readable
* aligned with the direct exchange binding strategy

Examples for future phases:

* transaction.flagged
* fraud.result.created
* dashboard.update.published

---

### Event Type Naming

Application-level event type fields should follow this style:

<domain>.<event>

Current event type:

transaction.created

At the current stage, the routing key and event type are intentionally the same for simplicity and consistency.

In later phases, routing key and event type may diverge if needed, but they should remain semantically aligned.

---

## Current Async Flow

### Producer

Service:

* transaction-api-service

Responsibility:

* persist transaction data
* publish a transaction.created event after successful transaction creation

Publishing target:

* Exchange: fraud.transaction.exchange
* Routing key: transaction.created

---

### Consumer

Service:

* fraud-worker-service

Responsibility:

* listen to transaction-created events
* begin asynchronous fraud processing flow

Listening target:

* Queue: fraud.transaction.created.queue

Binding:

* Exchange: fraud.transaction.exchange
* Routing key: transaction.created

---

## Message Format

The current event structure includes:

* eventId
* eventType
* occurredAt
* payload

Payload currently includes transaction details such as:

* transactionId
* userId
* amount
* currency
* merchant
* country
* city
* latitude
* longitude
* transactionTime
* createdAt

This format is serialized as JSON.

---

## Durability Strategy

Current broker resources are declared as durable:

* exchange is durable
* queue is durable

This is appropriate for local production-style development and future hardening phases.

---

## Virtual Host

Current RabbitMQ virtual host:

/

This is acceptable for local development.

In future environments, a dedicated virtual host may be introduced for stronger isolation.

---

## Future Extension Notes

Later phases may introduce additional broker resources such as:

* fraud decision queues
* realtime dashboard update queues
* retry queues
* dead-letter queues
* MCP-triggered async integration events

These future resources must follow the same naming principles:

* readable
* domain-based
* purpose-specific
* environment-agnostic

---

## Versioning Guidance

At the current phase, explicit version suffixes are not yet required in exchange, queue, or routing key names.

If event contracts become more complex later, versioning may be introduced in one of these forms:

transaction.created.v1
fraud.transaction.exchange.v1

Until that becomes necessary, the platform should keep names simple and stable.

---

## Operational Notes

When debugging RabbitMQ flow locally, always verify:

1. exchange exists
2. queue exists
3. binding exists
4. producer is connected
5. consumer is connected
6. published message count and delivery behavior are visible in RabbitMQ UI

---

## Summary

Current Phase 3 messaging standard:

* Exchange: fraud.transaction.exchange
* Queue: fraud.transaction.created.queue
* Routing key: transaction.created
* Event type: transaction.created

This convention becomes the baseline for all future asynchronous flows in the platform.
