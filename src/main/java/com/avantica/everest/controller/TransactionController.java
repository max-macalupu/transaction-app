package com.avantica.everest.controller;


import com.avantica.everest.domain.OkResponse;
import com.avantica.everest.domain.TransactionRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/module/transaction")
public class TransactionController {

  public static final String SUCCESSFULLY_MESSAGE = "Transaction was created successfully.";

  @PostMapping
  public @ResponseBody OkResponse createTransaction(@RequestBody TransactionRequest request) {
    return new OkResponse(SUCCESSFULLY_MESSAGE);
  }
}