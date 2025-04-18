package com.cjbbank.transaction.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.cjbbank.transaction.entity.TransactionDO;

@Repository
public class TransactionRepository {
    private final Map<String, TransactionDO> transactions = new ConcurrentHashMap<>();//保证线程安全

    public TransactionDO save(TransactionDO transaction) {
        /*    if (transactions.values().stream()
                .anyMatch(t -> t.getType() == transaction.getType() && t.getAmount() == transaction.getAmount())) {
            throw new DuplicateTransactionException("Duplicate transaction detected");
        }*/
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    public void clear() {
        transactions.clear();
    }

    public List<TransactionDO> findAll() {
        return new ArrayList<>(transactions.values());
    }

    public TransactionDO findById(String id) {
        return transactions.get(id);
    }

    public void deleteById(String id) {

        transactions.remove(id);
    }

    public TransactionDO update(TransactionDO transaction) {
        /*    if (!transactions.containsKey(transaction.getId())) {
            throw new TransactionNotFoundException("Transaction not found with id: " + transaction.getId());
        }*/
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }
}