package io.github.robinhosz.techbank.account.query.infrastructure;

import io.github.robinhosz.techbank.cqrs.core.domain.BaseEntity;
import io.github.robinhosz.techbank.cqrs.core.infrastructure.QueryDispatcher;
import io.github.robinhosz.techbank.cqrs.core.queries.BaseQuery;
import io.github.robinhosz.techbank.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.size() == 0) {
            throw new RuntimeException("No query handler was registered for: " + query);
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("More than one query handler was registered for: " + query);
        }
        return handlers.get(0).handle(query);
    }
}
