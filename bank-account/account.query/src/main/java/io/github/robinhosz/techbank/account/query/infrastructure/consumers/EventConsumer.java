package io.github.robinhosz.techbank.account.query.infrastructure.consumers;

import io.github.robinhosz.techbank.account.common.events.AccountClosedEvent;
import io.github.robinhosz.techbank.account.common.events.AccountOpenedEvent;
import io.github.robinhosz.techbank.account.common.events.FundsDepositedEvent;
import io.github.robinhosz.techbank.account.common.events.FundsWithdrawEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithdrawEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
