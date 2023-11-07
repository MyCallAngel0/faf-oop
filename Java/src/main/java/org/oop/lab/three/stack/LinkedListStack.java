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
        if (size == capacity) {
            System.out.println("Stack is full. Cannot push.");
            return;
        }
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        T item = top.data;
        top = top.next;
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return size == 0;
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

