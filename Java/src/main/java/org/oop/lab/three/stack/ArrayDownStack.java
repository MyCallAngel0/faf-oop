package org.oop.lab.three.stack;

import java.util.AbstractCollection;
import java.util.Iterator;

public class ArrayDownStack<T> extends AbstractCollection<T> implements Stack<T> {
    private final Object[] data;
    private int free;
    private final int capacity;

    public ArrayDownStack(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.free = capacity;
    }

    @Override
    public void push(T item) {
        if (free == 0) {
            System.out.println("Stack is full.");
            return;
        }
        data[--free] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return (T) data[free++];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return (T) data[free];
    }

    @Override
    public boolean isEmpty() {
        return free == capacity;
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

    public Iterator<T> iterator() {
        return new ArrayDownStack.StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int currentIndex = free;
        @Override
        public boolean hasNext() {
            return currentIndex <= capacity - 1;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return (T) data[currentIndex++];
        }
    }

    @Override
    public int size() {
        return capacity - free;
    }
}
