package com.desacode.disruptor.transaction;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionTestService {
    private final TransactionService transactionService;
    private final TransactionServiceNoDisruptor transactionServiceNoDisruptor;

    public void benchmarkNoDisruptor(int numTransactions) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numTransactions; i++) {
            Transaction transaction = new Transaction(); // Create and populate transaction
            transactionServiceNoDisruptor.processTransaction(transaction); // Synchronous processing
        }

        long endTime = System.currentTimeMillis();
        log.info("Time taken for synchronous processing: {} ms", (endTime - startTime));
    }

    public void benchmarkWithDisruptor(int numTransactions) throws Exception {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numTransactions; i++) {
            Transaction transaction = new Transaction(); // Create and populate transaction
            transactionService.processTransaction(transaction); // Asynchronous Disruptor processing
        }

        long endTime = System.currentTimeMillis();
        log.info("Time taken for Disruptor processing: {} ms", (endTime - startTime));
    }


}
