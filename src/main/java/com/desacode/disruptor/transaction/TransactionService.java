package com.desacode.disruptor.transaction;


import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionService {
    private final RingBuffer<Transaction> ringBuffer;
    private final TransactionRepository transactionRepository;


    public TransactionService(RingBuffer<Transaction> ringBuffer, TransactionRepository transactionRepository) {
        this.ringBuffer = ringBuffer;
        this.transactionRepository = transactionRepository;
    }


    public void processTransaction(Transaction transaction) throws Exception {
        long sequence = ringBuffer.next();
        try{
            Transaction newData = ringBuffer.get(sequence);
            newData.setOrderId(transaction.getOrderId());
            newData.setItems(transaction.getItems());
            newData.setTotalPrice(transaction.getTotalPrice());
//            log.info("Published Sequence: {}", sequence);
        }finally {
            ringBuffer.publish(sequence);
//            log.info("Remaining Capacity: {}", ringBuffer.remainingCapacity());
        }
    }
}
