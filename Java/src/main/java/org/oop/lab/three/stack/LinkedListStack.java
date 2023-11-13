package org.oop.lab.three.stack;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<T> extends AbstractCollection<T> implements Stack<T> {

    private Node<T> top;
    private int size;
    private int capacity;

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public void push(T item) {
        if (isFull()) {
            throw new java.util.NoSuchElementException("Stack is full");
        }
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        T item = top.data;
        top = top.next;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
    public int size() {
        return size;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = top;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = current.data;
                current = current.next;
                return item;
            }
        };
    }
}

