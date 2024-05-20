package io.github.robinhosz.techbank.account.cmd.api.commands;

import io.github.robinhosz.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class DepositFundsCommand extends BaseCommand {
    private double ammount;
}
