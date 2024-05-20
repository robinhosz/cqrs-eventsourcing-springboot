package io.github.robinhosz.techbank.account.cmd.api.commands;

import io.github.robinhosz.techbank.account.common.dto.AccountType;
import io.github.robinhosz.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
