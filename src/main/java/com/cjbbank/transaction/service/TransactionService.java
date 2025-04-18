package com.cjbbank.transaction.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cjbbank.transaction.api.model.PagedData;
import com.cjbbank.transaction.api.model.TransactionDTO;
import com.cjbbank.transaction.entity.TransactionDO;
import com.cjbbank.transaction.enums.TransactionReturnCodeEnum;
import com.cjbbank.transaction.exception.GeneralException;
import com.cjbbank.transaction.repository.TransactionRepository;
import com.cjbbank.transaction.util.ResponseUtil;
import com.cjbbank.transaction.util.UUIDUtil;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostConstruct
    public void init() throws Exception {
        // Bean初始化完成后执行的逻辑

        int i = 0;
        while (i++ <= 11) {
            TransactionDTO body = new TransactionDTO();
            body.setName("tx" + i);
            body.setAmount(BigDecimal.valueOf(i));

            this.createTransaction(body);
        }

        System.out.println("Bean初始化完成，执行@PostConstruct方法");
    }

    @Cacheable(value = "transactions", key = "#id")
    public TransactionDTO getTransactionById(String id) throws GeneralException {
        TransactionDO result = transactionRepository.findById(id);

        if (result == null) {
            throw new GeneralException(TransactionReturnCodeEnum.TRANSACTION_NOT_FOUND.VALUE, id);
        }

        return result;
    }

    @CacheEvict(value = "transactions", allEntries = true)
    public void clearAllTransactions() {
        transactionRepository.clear();
    }

    @Cacheable(value = "transactions")
    public PagedData getAllTransactions(int page, int perPage) throws GeneralException {

        List<TransactionDO> totalTransactionList = transactionRepository.findAll();

        //pageNo 从1开始
        page = page <= 0 ? 1 : page;
        perPage = perPage <= 0 ? 10 : perPage;

        int start = (page - 1) * perPage;
        if (start > totalTransactionList.size() - 1) {
            //其实下标，大于全体列表的最后一个下标，返回空
            return ResponseUtil.emptyPagedData();
        }

        int end = Math.min(start + perPage, totalTransactionList.size());

        List<TransactionDTO> transactionDtoList = totalTransactionList.subList(start, end).stream()
                .map(e -> (TransactionDTO) e).toList();

        return ResponseUtil.wrapPagedData(totalTransactionList.size(), transactionDtoList);

    }

    @CacheEvict(value = "transactions", allEntries = true)
    public TransactionDTO createTransaction(TransactionDTO body) throws Exception {

        TransactionDO transaction = new TransactionDO();
        BeanUtils.copyProperties(body, transaction);

        String transactionId = UUIDUtil.generateUUID();
        transaction.setId(transactionId);
        transactionRepository.save(transaction);

        return transactionRepository.findById(transactionId);

    }

    @CacheEvict(value = "transactions", allEntries = true)
    public void deleteTransactionById(String transactionId) throws Exception {
        if (transactionRepository.findById(transactionId) == null) {
            throw new GeneralException(TransactionReturnCodeEnum.TRANSACTION_NOT_FOUND.VALUE, transactionId);
        }

        transactionRepository.deleteById(transactionId);

    }

    @CacheEvict(value = "transactions", allEntries = true)
    public TransactionDTO updateTransactionById(String transactionId, TransactionDTO body) throws Exception {

        if (transactionRepository.findById(transactionId) == null) {
            throw new GeneralException(TransactionReturnCodeEnum.TRANSACTION_NOT_FOUND.VALUE, transactionId);
        }

        TransactionDO transaction = new TransactionDO();
        BeanUtils.copyProperties(body, transaction);

        transaction.setId(transactionId);

        transactionRepository.update(transaction);

        return transactionRepository.findById(transactionId);

    }
}
