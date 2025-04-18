package com.cjbbank.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjbbank.transaction.api.model.PagedData;
import com.cjbbank.transaction.api.model.TransactionDTO;
import com.cjbbank.transaction.service.TransactionService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * GET /categories/{categoryId} : Get Category By Id
     * Get category by id
     *
     * @param categoryId  (required)
     * @return OK (status code 200)
     */

    @RequestMapping(method = RequestMethod.GET, value = "/transactions/{transactionId}", produces = {
            "application/json" })
    public ResponseEntity<TransactionDTO> getTransactionById(
            @Parameter(name = "transactionId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("transactionId") String transactionId)
            throws Exception {

        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/transactions", produces = { "application/json" })
    public ResponseEntity<PagedData> getAllTransactions(
            @NotNull @Min(1) @Parameter(name = "page", description = "page number", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @NotNull @Max(100) @Parameter(name = "perPage", description = "page size", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "perPage", required = true, defaultValue = "10") Integer perPage

    ) throws Exception {

        return ResponseEntity.ok(transactionService.getAllTransactions(page, perPage));

    }

    @RequestMapping(method = RequestMethod.POST, value = "/transactions", produces = {
            "application/json" }, consumes = { "application/json" })
    public ResponseEntity<TransactionDTO> createTransaction(
            @Parameter(name = "body", description = "") @Valid @RequestBody(required = false) TransactionDTO body)
            throws Exception {

        return new ResponseEntity<>(transactionService.createTransaction(body), HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/transactions/{transactionId}")
    public ResponseEntity<Void> deleteTransactionById(
            @Parameter(name = "transactionId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("transactionId") String transactionId)
            throws Exception {
        transactionService.deleteTransactionById(transactionId);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transactions/{transactionId}", produces = {
            "application/json" }, consumes = { "application/json" })
    public ResponseEntity<TransactionDTO> updateTransactionById(
            @Parameter(name = "transactionId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("transactionId") String transactionId,
            @Parameter(name = "body", description = "") @Valid @RequestBody(required = false) TransactionDTO body)
            throws Exception {

        return ResponseEntity.ok(transactionService.updateTransactionById(transactionId, body));

    }

}
