package com.desacode.disruptor.disruptor;

import com.desacode.disruptor.transaction.Transaction;
import com.lmax.disruptor.EventFactory;

public class TransactionEventFactory implements EventFactory<Transaction> {
    @Override
    public Transaction newInstance() {
        return new Transaction();
    }
}
