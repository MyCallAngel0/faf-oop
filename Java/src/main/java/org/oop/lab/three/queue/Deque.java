package org.oop.lab.three.queue;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.LinkedList;

public class Deque<T> extends AbstractCollection<T> implements Queue<T> {
    private final LinkedList<T> deque = new LinkedList<>();
    private final int capacity;

    public Deque(int capacity) {
        this.capacity = capacity;
    }


    @Override
    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }
        deque.add(item);
    }

    public void addFirst(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }
        deque.addFirst(item);
    }

    public void addLast(T item) {
        if (size() >= capacity) {
            throw new IllegalStateException("Deque is full");
        }
        deque.addLast(item);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        return deque.removeFirst();
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        return deque.removeLast();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        return deque.getFirst();
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        return deque.getLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public boolean isFull() {
        return size() == capacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    @Override
    public int size() {
        return deque.size();
    }

    private class DequeIterator implements Iterator<T> {
        private int currentIndex;

        public DequeIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements in the deque");
            }
            return deque.get(currentIndex++);
        }
    }
}
