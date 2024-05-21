package io.github.robinhosz.techbank.account.query.api.queries;

import io.github.robinhosz.techbank.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
    private String id;
}
