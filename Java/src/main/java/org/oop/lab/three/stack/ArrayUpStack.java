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
        if (isFull()) {
            throw new java.util.NoSuchElementException("Stack is full");
        }
        data[++top] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        return (T) data[top--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        return (T) data[top];
    }


    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
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
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int currentIndex = top;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return (T) data[currentIndex--];
        }
    }

    @Override
    public int size() {
        return top + 1;
    }
}

