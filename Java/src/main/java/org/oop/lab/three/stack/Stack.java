package org.oop.lab.three.stack;

import java.util.SequencedCollection;

public interface Stack<E> extends SequencedCollection<E> {
    boolean add(Stack node);
}
