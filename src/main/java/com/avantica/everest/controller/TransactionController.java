package com.avantica.everest.controller;

import com.avantica.everest.domain.GetTransactionRequest;
import com.avantica.everest.domain.OkResponse;
import com.avantica.everest.domain.TransactionRequest;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/***
 * This class is used to manage all
 * transaction rest services.
 */
@RestController
@RequestMapping("/v1/module/transaction")
public class TransactionController {

  public static final String SUCCESSFULLY_MESSAGE = "Transaction was created successfully.";

  @Autowired
  private TransactionService transactionService;

  /***
   * this method is used to create new transaction.
   * @param request
   * @return
   */
  @PostMapping
  public @ResponseBody OkResponse createTransaction(@RequestBody TransactionRequest request) {
    Transaction transaction = new Transaction.Builder()
        .name(request.getName())
        .weight(request.getWeight())
        .inputDate(request.getInputDate())
        .transactionType(request.getTransactionType())
        .build();

    transactionService.create(transaction);
    return new OkResponse(SUCCESSFULLY_MESSAGE);
  }

  /***
   * This method is used to update some transaction.
   * @param request
   * @return
   */
  @PutMapping
  public @ResponseBody OkResponse deleteTransaction(@RequestBody TransactionRequest request) {

    transactionService.delete(request.getId());

    return new OkResponse("Transaction was deleted successfully.");
  }

  /***
   * This method is used to get all transaction.
   * @param request
   * @return
   */
  @GetMapping("/weight")
  public @ResponseBody Object getTransactionByWeight(
      @RequestBody GetTransactionRequest request) {
    List<Transaction> transactionList = transactionService.findByWeight(request.getWeight());
    if (CollectionUtils.isEmpty(transactionList)) {
      return new OkResponse("Are not exist transaction with the filter yet.");
    }
    return transactionList;
  }

  /***
   *
   * @param request
   * @return
   */
  @GetMapping("/type")
  public @ResponseBody Object getTransactionByTransactionType(
      @RequestBody GetTransactionRequest request) {
    List<Transaction> transactionList = transactionService
        .findByTransactionType(request.getTransactionType());

    if (CollectionUtils.isEmpty(transactionList)) {
      return new OkResponse("Are not exist transaction with the filter.");
    }
    return transactionList;
  }
}