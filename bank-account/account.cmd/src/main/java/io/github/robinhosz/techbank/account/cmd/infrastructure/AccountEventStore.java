package io.github.robinhosz.techbank.account.cmd.infrastructure;


import io.github.robinhosz.techbank.account.cmd.domain.AccountAggregate;
import io.github.robinhosz.techbank.account.cmd.domain.EventStoreRepository;
import io.github.robinhosz.techbank.account.cmd.exceptions.AggregateNotFoundException;
import io.github.robinhosz.techbank.account.cmd.exceptions.ConcurrencyException;
import io.github.robinhosz.techbank.cqrs.core.events.BaseEvent;
import io.github.robinhosz.techbank.cqrs.core.events.EventModel;
import io.github.robinhosz.techbank.cqrs.core.infrastructure.EventStore;
import io.github.robinhosz.techbank.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {

    @Autowired
    private EventProducer eventProducer;

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
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .eventData(event)
                    .version(version)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Aggregate not found");
        }
        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }
}
