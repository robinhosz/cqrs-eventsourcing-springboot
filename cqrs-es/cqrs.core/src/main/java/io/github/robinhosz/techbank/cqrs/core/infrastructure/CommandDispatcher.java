package io.github.robinhosz.techbank.cqrs.core.infrastructure;

import io.github.robinhosz.techbank.cqrs.core.commands.BaseCommand;
import io.github.robinhosz.techbank.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {

    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
