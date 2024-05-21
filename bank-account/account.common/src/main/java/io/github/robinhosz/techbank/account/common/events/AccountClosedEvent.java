package io.github.robinhosz.techbank.account.common.events;

import io.github.robinhosz.techbank.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AccountClosedEvent extends BaseEvent {
}
