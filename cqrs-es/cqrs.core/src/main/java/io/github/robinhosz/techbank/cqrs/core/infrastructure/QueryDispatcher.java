package io.github.robinhosz.techbank.cqrs.core.infrastructure;

import io.github.robinhosz.techbank.cqrs.core.domain.BaseEntity;
import io.github.robinhosz.techbank.cqrs.core.queries.BaseQuery;
import io.github.robinhosz.techbank.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
