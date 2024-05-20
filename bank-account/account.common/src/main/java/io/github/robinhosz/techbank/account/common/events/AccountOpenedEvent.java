package io.github.robinhosz.techbank.account.common.events;

import io.github.robinhosz.techbank.account.common.dto.AccountType;
import io.github.robinhosz.techbank.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
    private String id;
    private String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private double openingBalance;
}
