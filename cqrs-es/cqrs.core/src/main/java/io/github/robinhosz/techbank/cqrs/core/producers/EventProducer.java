package io.github.robinhosz.techbank.cqrs.core.producers;

import io.github.robinhosz.techbank.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
