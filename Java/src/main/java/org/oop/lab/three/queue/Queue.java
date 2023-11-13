package org.oop.lab.three.queue;

import java.util.Collection;

public interface Queue<T> extends Collection<T> {
    void enqueue(T t);
    T dequeue();
    T peek();
    @Override
    boolean isEmpty();
    @Override
    int size();

    boolean isFull();
}
