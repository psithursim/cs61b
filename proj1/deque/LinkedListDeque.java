package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    public static class IntNode<T> {
        private IntNode<T> pre;
        private T data;
        private IntNode<T> next;

        public IntNode() {
            pre = null;
            data = null;
            next = null;
        }

        public IntNode(T item) {
            pre = null;
            data = item;
            next = null;
        }

        public IntNode(T item, IntNode<T> newnode) {
            pre = null;
            data = item;
            next = newnode;
            newnode.pre = this;
        }

        public IntNode(IntNode<T> newnode, T item) {
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

    public LinkedListDeque(T item) {
        first = new IntNode<>();
        IntNode<T> newnode = new IntNode<>(first, item);
        last = new IntNode<>();
        newnode.next = last;
        last.pre = newnode;
        size = 1;
    }

    public void addFirst(T item) {
        IntNode<T> temp = first.next;
        IntNode<T> newnode = new IntNode<>(first, item);
        newnode.next = temp;
        temp.pre = newnode;
        size++;
    }

    public void addLast(T item) {
        IntNode<T> temp = last.pre;
        IntNode<T> newnode = new IntNode<>(item, last);
        newnode.pre = temp;
        temp.next = newnode;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode<T> temp = first.next;
        while (temp != last) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        IntNode<T> temp = first.next;
        while (temp != last) {
            str.append(temp.data + " ");
            temp = temp.next;
        }
        return str.toString();
    }

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

    public T get(int index) {
        if (index <= 0 || index > size) {
            return null;
        }
        IntNode<T> temp = first;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public T getRecursive(int index) {
        if (index <= 0 || index > size) {
            return null;
        }
        int pos = 0;
        IntNode<T> temp = first;
        return cursive(temp, pos, index);
    }

    public T cursive(IntNode<T> temp, int pos, int index) {
        if (pos == index) {
            return temp.data;
        }
        pos++;
        temp = temp.next;
        return cursive(temp, pos, index);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIerator();
    }

    private class LinkedListDequeIerator implements Iterator<T> {
        private IntNode<T> now;

        public LinkedListDequeIerator() {
            now = first;
        }

        public boolean hasNext() {
            return now.next != last;
        }

        public T next() {
            now = now.next;
            T returnItem = now.data;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListDeque<T> that = (LinkedListDeque<T>) o;
        if (size != that.size) return false;
        if (toString().equals(that.toString())) {
            return true;
        }
        return false;
    }
}
