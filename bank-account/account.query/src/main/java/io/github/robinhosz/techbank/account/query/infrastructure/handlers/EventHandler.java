package io.github.robinhosz.techbank.account.query.infrastructure.handlers;

import io.github.robinhosz.techbank.account.common.events.AccountClosedEvent;
import io.github.robinhosz.techbank.account.common.events.AccountOpenedEvent;
import io.github.robinhosz.techbank.account.common.events.FundsDepositedEvent;
import io.github.robinhosz.techbank.account.common.events.FundsWithdrawEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawEvent event);
    void on(AccountClosedEvent event);
}
