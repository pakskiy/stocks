package com.pakskiy.stocks.connection;

import java.util.List;

public interface Connector<T, V> {
    List<T> loadSymbols();
    V loadStock(String name);
}
