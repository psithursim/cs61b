package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class IntNode<T> {
        private IntNode<T> pre;
        private T data;
        private IntNode<T> next;

        IntNode() {
            pre = null;
            data = null;
            next = null;
        }

        IntNode(T item, IntNode<T> newnode) {
            pre = null;
            data = item;
            next = newnode;
            newnode.pre = this;
        }

        IntNode(IntNode<T> newnode, T item) {
            newnode.next = this;
            pre = newnode;
            data = item;
            next = null;
        }
    }
    private IntNode<T> first;
    private IntNode<T> last;
    private int size;

    public LinkedListDeque() {
        first = new IntNode<>();
        last = new IntNode<>();
        first.next = last;
        last.pre = first;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        IntNode<T> temp = first.next;
        IntNode<T> newnode = new IntNode<>(first, item);
        newnode.next = temp;
        temp.pre = newnode;
        size++;
    }

    @Override
    public void addLast(T item) {
        IntNode<T> temp = last.pre;
        IntNode<T> newnode = new IntNode<>(item, last);
        newnode.pre = temp;
        temp.next = newnode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntNode<T> temp = first.next;
        while (temp != last) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        IntNode<T> temp = first.next;
        T target = temp.data;
        temp = temp.next;
        first.next = temp;
        temp.pre = first;
        size--;
        return target;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        IntNode<T> temp = last.pre;
        T target = temp.data;
        temp = temp.pre;
        temp.next = last;
        last.pre = temp;
        size--;
        return target;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        IntNode<T> temp = first;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        int pos = 0;
        IntNode<T> temp = first.next;
        return cursive(temp, pos, index);
    }

    private T cursive(IntNode<T> temp, int pos, int index) {
        if (pos == index) {
            return temp.data;
        }
        pos++;
        temp = temp.next;
        return cursive(temp, pos, index);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private IntNode<T> now;

        LinkedListDequeIterator() {
            now = first;
        }

        @Override
        public boolean hasNext() {
            return now.next != last;
        }

        @Override
        public T next() {
            now = now.next;
            return now.data;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Deque<T> that;
        if (o instanceof ArrayDeque) {
            that = (ArrayDeque<T>) o;
        } else if (o instanceof LinkedListDeque) {
            that = (LinkedListDeque<T>) o;
        } else {
            return false;
        }
        if (size() != that.size()) {
            return false;
        }
        Iterator<T> thisIterator = iterator();
        Iterator<T> thatIterator = that.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }
}
