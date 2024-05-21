package io.github.robinhosz.techbank.account.query.api.queries;

import io.github.robinhosz.techbank.account.query.api.dto.EqualityType;
import io.github.robinhosz.techbank.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
