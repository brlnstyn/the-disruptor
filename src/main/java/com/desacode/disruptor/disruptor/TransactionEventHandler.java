package com.desacode.disruptor.disruptor;


import com.desacode.disruptor.transaction.Transaction;
import com.desacode.disruptor.transaction.TransactionRepository;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionEventHandler implements EventHandler<Transaction> {
    private final TransactionRepository transactionRepository;

    public TransactionEventHandler(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void onEvent(Transaction transaction, long sequence, boolean endOfBatch) throws Exception {
        transactionRepository.save(transaction);
//        long startTime = System.currentTimeMillis();
//        log.info("Processing Transaction With Disruptor: OrderId={}, Items={}, Total Price={}",
//                transaction.getOrderId(), transaction.getItems(), transaction.getTotalPrice());
//        long endTime = System.currentTimeMillis();
//        log.info("Time taken for synchronous processing: {} ms", (endTime - startTime));
    }
}
