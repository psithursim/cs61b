package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] deque;
    private int size;
    //将放入的位置
    private int head;
    private int tail;

    public ArrayDeque() {
        deque = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 1;
    }

    private void resize(int newSize) {
        T[] newDeque = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newDeque[i] = deque[(head + 1 + i) % deque.length];
        }
        deque = newDeque;
        head = (-1 + deque.length) % deque.length;
        tail = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == deque.length) {
            resize(size * 2);
        }
        deque[head] = item;
        head = (head - 1 + deque.length) % deque.length;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == deque.length) {
            resize(size * 2);
        }
        deque[tail] = item;
        tail = (tail + 1 + deque.length) % deque.length;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(deque[(head + i) % deque.length] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size < deque.length / 4) {
            resize(deque.length / 4);
        }
        head = (head + 1) % deque.length;
        T item = deque[head];
        deque[head] = null;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size < deque.length / 4) {
            resize(deque.length / 4);
        }
        tail = (tail - 1 + deque.length) % deque.length;
        T item = deque[tail];
        deque[tail] = null;
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        int temp = (head + 1 + index) % deque.length;
        return deque[temp];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        ArrayDequeIterator() {
            pos = head;
        }

        @Override
        public boolean hasNext() {
            int temp = (pos + 1) % deque.length;
            return temp != tail;
        }

        @Override
        public T next() {
            pos = (pos + 1) % deque.length;
            return deque[pos];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Deque)) {
            return false;
        }
        Deque<T> that = (Deque<T>) o;
        if (size() != that.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(that.get(i))) {
                return false;
            }
        }
        return true;
    }
}
