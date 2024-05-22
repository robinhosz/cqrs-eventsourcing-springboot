## CQRS and Event Sourcing Project with Spring Boot and Kafka

## Description
This project demonstrates how to implement the CQRS (Command Query Responsibility Segregation) architecture and Event Sourcing using Spring Boot and Apache Kafka. The CQRS architecture allows for the separation of write operations (commands) from read operations (queries), while Event Sourcing maintains a complete history of all changes in the system through events.

## Features
* CQRS Implementation: Separates the command (write) and query (read) models.
* Event Sourcing: Captures all changes to an application state as a sequence of events.
* Spring Boot: Simplifies the development and deployment of the application.
* Apache Kafka: Manages event streaming and messaging between services.

## Prerequisites
Before you begin, ensure you have met the following requirements:

* Java 8 or higher installed.
* Apache Maven installed.
* Apache Kafka installed and running.
* A compatible database (MySQL and Mongodb) configured.

## Usage
Once the application is running, you can:

* Send commands to create or update entities.
* Query the current state of entities.
* Monitor events produced and consumed through Kafka topics.

## Project Structure
* Command Service: Handles all write operations and publishes events.
* Query Service: Handles all read operations and subscribes to events.
* Event Store: Stores all events that represent changes in the system.
* Kafka Configuration: Manages Kafka producers and consumers.
