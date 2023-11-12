package org.oop.lab.three.queue;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueue<T> extends AbstractCollection<T> implements Queue<T> {
    private Object[] data;
    private int capacity;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        T item = (T) data[front];
        data[front] = null;
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        @SuppressWarnings("unchecked")
        T item = (T) data[front];
        return item;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (front == 0 && rear == capacity - 1) || (rear == front - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularQueueIterator();
    }

    private class CircularQueueIterator implements Iterator<T> {
        private int currentIndex;
        private int remaining;

        public CircularQueueIterator() {
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

    public int size() {
        return size;
    }
}
