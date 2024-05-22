package io.github.robinhosz.techbank.account.cmd.infrastructure;

import io.github.robinhosz.techbank.account.cmd.domain.AccountAggregate;
import io.github.robinhosz.techbank.cqrs.core.domain.AggregateRoot;
import io.github.robinhosz.techbank.cqrs.core.events.BaseEvent;
import io.github.robinhosz.techbank.cqrs.core.handlers.EventSourcingHandler;
import io.github.robinhosz.techbank.cqrs.core.infrastructure.EventStore;
import io.github.robinhosz.techbank.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvent(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for(var id: aggregateIds) {
            var aggregate = getById(id);
            if(aggregate == null || !aggregate.getActive()) continue;
            var events = eventStore.getEvents(id);
            for(var event: events) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }
}
