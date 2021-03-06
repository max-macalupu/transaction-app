package com.avantica.everest.controller;

import com.avantica.everest.domain.GetTransactionRequest;
import com.avantica.everest.domain.OkResponse;
import com.avantica.everest.domain.TransactionRequest;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.TransactionType;
import com.avantica.everest.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        .id(Long.valueOf(request.getWeight()))
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
  @DeleteMapping
  public @ResponseBody OkResponse deleteTransaction(@RequestBody TransactionRequest request) {

    transactionService.delete(request.getId(), request.getTransactionType());

    return new OkResponse("Transaction was deleted successfully.");
  }

  /***
   * This method is used to get all transaction.
   * @param  weight
   * @return
   */
  @GetMapping("/weight")
  public @ResponseBody Object getTransactionByWeight(
      @RequestParam("param") Integer weight) {
    List<Transaction> transactionList = transactionService.findByWeight(weight);
    if (CollectionUtils.isEmpty(transactionList)) {
      return new OkResponse("Are not exist transaction with the filter yet.");
    }
    return transactionList;
  }

  /***
   *
   * @param transactionType
   * @return
   */
  @GetMapping("/type")
  public @ResponseBody Object getTransactionByTransactionType(
      @RequestParam("param") TransactionType transactionType) {
    List<Transaction> transactionList = transactionService
        .findByTransactionType(transactionType);

    if (CollectionUtils.isEmpty(transactionList)) {
      return new OkResponse("Are not exist transaction with the filter.");
    }
    return transactionList;
  }
}