package com.pakskiy.stocks.connection;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Connector<T, V> {
    List<T> loadSymbols();
    CompletableFuture<V> loadStock(String name);
}
