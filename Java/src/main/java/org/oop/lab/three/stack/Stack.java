package org.oop.lab.three.stack;

import java.util.Collection;

public interface Stack<T> extends Collection<T> {
    @Override
    boolean isEmpty();
    @Override
    int size();
    void push(T t);
    T pop();
    T peek();

    boolean isFull();

}
