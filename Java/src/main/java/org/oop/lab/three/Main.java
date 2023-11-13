package org.oop.lab.three;

import org.oop.lab.three.queue.CircularQueue;
import org.oop.lab.three.queue.Deque;
import org.oop.lab.three.queue.LinearQueue;
import org.oop.lab.three.queue.Queue;
import org.oop.lab.three.stack.ArrayDownStack;
import org.oop.lab.three.stack.ArrayUpStack;
import org.oop.lab.three.stack.LinkedListStack;
import org.oop.lab.three.stack.Stack;

@SuppressWarnings("unchecked")
public class Main {
    public static void main(String[] args) {

        Stack<String> stack = new ArrayDownStack<>(8);
        System.out.println(stack.isEmpty());
        stack.push("Random0");
        stack.push("Random1");
        stack.push("Random2");
        stack.push("Random3");
        stack.push("Random4");
        System.out.println(stack.size());
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
        stack.pop();
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
        stack.forEach(System.out::println);

        /*Queue<String> queue = new LinearQueue<>(2);
        System.out.println(queue.isEmpty());
        queue.enqueue("Random1");
        System.out.println(queue.isFull());
        queue.enqueue("Random2");
        System.out.println(queue.isFull());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        queue.dequeue();
        queue.enqueue("Random3");
        queue.forEach(System.out::println);*/

        /*Deque<String> queue = new Deque<>(2);
        System.out.println(queue.isEmpty());
        queue.enqueue("Random1");
        System.out.println(queue.isFull());
        queue.enqueue("Random2");
        System.out.println(queue.isFull());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        queue.dequeue();
        queue.enqueue("Random3");
        System.out.println(queue.peekLast());
        queue.forEach(System.out::println);
        queue.removeLast();
        System.out.println(queue.peekLast());*/
    }
}
