package io.github.robinhosz.techbank.account.cmd.infrastructure;


import io.github.robinhosz.techbank.account.cmd.domain.EventStoreRepository;
import io.github.robinhosz.techbank.account.cmd.exceptions.ConcurrencyException;
import io.github.robinhosz.techbank.cqrs.core.events.BaseEvent;
import io.github.robinhosz.techbank.cqrs.core.events.EventModel;
import io.github.robinhosz.techbank.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() -1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event: events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .eventType(event.getClass().getTypeName())
                    .aggregateType(event.getClass().getDeclaringClass().getTypeName())
                    .eventData(event)
                    .version(version)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (persistedEvent != null) {
                //TODO: produce event to kafka
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        return null;
    }
}
