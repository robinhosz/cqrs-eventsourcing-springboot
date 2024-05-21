package io.github.robinhosz.techbank.account.cmd.api.controllers;

import io.github.robinhosz.techbank.account.cmd.api.commands.DepositFundsCommand;
import io.github.robinhosz.techbank.account.cmd.exceptions.AggregateNotFoundException;
import io.github.robinhosz.techbank.account.common.dto.BaseResponse;
import io.github.robinhosz.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/depositFunds")
public class DepositFundsController {
    private final Logger logger = Logger.getLogger(OpenAccountController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id, @RequestBody DepositFundsCommand command) {

        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Funds deposited successfully"), HttpStatus.OK);

        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a had request but failed to process. Reason: {0}", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to deposit funds to bank account. Reason: {0}", e.toString());
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
