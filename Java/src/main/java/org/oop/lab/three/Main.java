package org.oop.lab.three;

import org.oop.lab.three.stack.ArrayDownStack;
import org.oop.lab.three.stack.ArrayUpStack;
import org.oop.lab.three.stack.LinkedListStack;
import org.oop.lab.three.stack.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> stack = new LinkedListStack<>(8);
        System.out.println(stack.isEmpty());
        stack.push("Random");
        System.out.println(stack.size());
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
        stack.pop();
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
    }
}
