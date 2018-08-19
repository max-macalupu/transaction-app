package com.avantica.everest.controller;

import com.avantica.everest.domain.GetTransactionRequest;
import com.avantica.everest.domain.OkResponse;
import com.avantica.everest.domain.TransactionRequest;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/module/transaction")
public class TransactionController {

  public static final String SUCCESSFULLY_MESSAGE = "Transaction was created successfully.";

  private TransactionService transactionService;

  @PostMapping
  public @ResponseBody OkResponse createTransaction(@RequestBody TransactionRequest request) {
    return new OkResponse(SUCCESSFULLY_MESSAGE);
  }

  @PutMapping
  public @ResponseBody OkResponse updateTransaction(@RequestBody TransactionRequest request) {
    return new OkResponse(SUCCESSFULLY_MESSAGE);
  }

  @GetMapping
  public @ResponseBody List<Transaction> getTransaction(@RequestBody GetTransactionRequest request) {
    return new ArrayList<>();
  }
}