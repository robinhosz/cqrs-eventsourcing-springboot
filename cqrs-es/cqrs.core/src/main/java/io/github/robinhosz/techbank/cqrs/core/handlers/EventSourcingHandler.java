package io.github.robinhosz.techbank.cqrs.core.handlers;

import io.github.robinhosz.techbank.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
}
