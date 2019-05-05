package com.idiotleon.tutorialdagger2withkotlin.modules.provides.persist;

public class Transaction {
    private final long ts;

    public Transaction(long ts){this.ts = ts;}

    @Override
    public String toString() {
        return String.valueOf(ts);
    }
}