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
        stack.push("Random");
        stack.push("Crysler1");
        stack.push("Crysler2");
        stack.push("Crysler3");
        stack.push("Crysler4");
        //System.out.println(stack.size());
        //System.out.println(stack.peek());
//        System.out.println(stack.isEmpty());
//        stack.pop();
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.size());
        Deque<String> queue = new Deque<>(2);
        queue.enqueue("Masha");
        queue.enqueue("i");
        System.out.println(queue.size());
        System.out.println(queue.peek());
        queue.dequeue();
        queue.enqueue("Medvedi");
        System.out.println(queue.peekLast());
        queue.forEach(System.out::println);
    }
}
