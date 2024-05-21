package io.github.robinhosz.techbank.cqrs.core.queries;

import io.github.robinhosz.techbank.cqrs.core.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
