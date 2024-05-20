package io.github.robinhosz.techbank.cqrs.core.commands;

@FunctionalInterface
public interface CommandHandlerMethod <T extends BaseCommand> {
    void handle(T command);
}
