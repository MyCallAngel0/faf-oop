package org.oop.lab.three.stack;

import java.util.AbstractCollection;
import java.util.Iterator;

public class ArrayUpStack<T> extends AbstractCollection<T> implements Stack<T> {
    private Object[] data;
    private int top;
    private int capacity;

    public ArrayUpStack(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.top = -1;
    }

    @Override
    public void push(T item) {
        if (top == capacity - 1) {
            System.out.println("Stack is full.");
            return;
        }
        data[++top] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return (T) data[top--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return (T) data[top];
    }


    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean contains(Object obj) {
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return top + 1;
    }
}

