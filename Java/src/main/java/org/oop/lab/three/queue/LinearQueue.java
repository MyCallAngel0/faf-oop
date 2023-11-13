package org.oop.lab.three.queue;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinearQueue<T> extends AbstractCollection<T> implements Queue<T> {
    private final Object[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public LinearQueue(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }

        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }

        T item = (T) data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        return (T) data[front];
    }

    @Override
    public boolean isEmpty() {
            return size == 0;
        }
        @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinearQueueIterator();
    }

    private class LinearQueueIterator implements Iterator<T> {
        private int currentIndex;
        private int remaining;

        public LinearQueueIterator() {
            currentIndex = front;
            remaining = size;
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the queue.");
            }
            T item = (T) data[currentIndex];
            currentIndex = (currentIndex + 1) % capacity;
            remaining--;
            return item;
        }
    }

    @Override
    public int size() {
            return size;
        }
}
