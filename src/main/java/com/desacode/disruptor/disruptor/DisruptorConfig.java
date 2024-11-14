package com.desacode.disruptor.disruptor;


import com.desacode.disruptor.DisruptorApplication;
import com.desacode.disruptor.transaction.Transaction;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class DisruptorConfig {
    @Bean
    public Disruptor<Transaction> disruptor(TransactionEventHandler transactionEventHandler) {
        TransactionEventFactory transactionEventFactory = new TransactionEventFactory();
        int bufferSize = 1024;

        // Create disruptor
        Disruptor<Transaction> disruptor = new Disruptor<>(
                transactionEventFactory,
                bufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );

        disruptor.handleEventsWith(transactionEventHandler);

        disruptor.start();
        return disruptor;
    }

    @Bean
    public RingBuffer<Transaction> ringBuffer(Disruptor<Transaction> disruptor) {
        return disruptor.getRingBuffer();
    }
}
