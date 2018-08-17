package com.avantica.everest.service;

import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.TransactionType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  public List<Transaction> findTransactionByType(TransactionType transactionType) {
    return new ArrayList<>();
  }
}
