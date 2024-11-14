package com.desacode.disruptor.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceNoDisruptor {
    private final TransactionRepository transactionRepository;

    public TransactionServiceNoDisruptor(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void processTransaction(Transaction transaction){
        transactionRepository.save(transaction);
//        long startTime = System.currentTimeMillis();
//        log.info("Processing Transaction Without Disruptor: OrderId={}, Items={}, Total Price={}",
//                transaction.getOrderId(), transaction.getItems(), transaction.getTotalPrice());
//        long endTime = System.currentTimeMillis();
//        log.info("Time taken for synchronous processing: {} ms", (endTime - startTime));
    }
}
