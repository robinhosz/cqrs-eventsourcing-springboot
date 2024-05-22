package io.github.robinhosz.techbank.account.cmd.api.controllers;

import io.github.robinhosz.techbank.account.cmd.api.commands.OpenAccountCommand;
import io.github.robinhosz.techbank.account.cmd.api.commands.RestoreReadDbCommand;
import io.github.robinhosz.techbank.account.cmd.api.dto.OpenAccountResponse;
import io.github.robinhosz.techbank.account.common.dto.BaseResponse;
import io.github.robinhosz.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/restoreReadDb")
public class RestoreReadDbController {

    private final Logger logger = Logger.getLogger(RestoreReadDbController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> restoreReadDb() {
        try {
            commandDispatcher.send(new RestoreReadDbCommand());
            return new ResponseEntity<>(new BaseResponse("Read Database restore successfully created"), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a had request but failed to process. Reason: {0}", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to restore read database Reason: {0}", e.toString());
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
