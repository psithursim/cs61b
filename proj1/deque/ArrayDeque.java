package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
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

    public void resize(int newSize) {
        T[] newDeque = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newDeque[i] = deque[(head + 1 + i) % deque.length];
        }
        deque = newDeque;
        head = (-1 + deque.length) % deque.length;
        tail = size;
    }

    public void addFirst(T item) {
        if (size == deque.length) {
            resize(size * 2);
        }
        deque[head] = item;
        head = (head - 1 + deque.length) % deque.length;
        size++;
    }

    public void addLast(T item) {
        if (size == deque.length) {
            resize(size * 2);
        }
        deque[tail] = item;
        tail = (tail + 1 + deque.length) % deque.length;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(deque[(head + i) % deque.length] + " ");
        }
        System.out.println();
    }

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

    public T get(int index) {
        int temp = (head + 1 + index) % deque.length;
        return deque[temp];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIerator();
    }

    private class ArrayDequeIerator implements Iterator<T> {
        private int pos;
        private int cnt;

        public ArrayDequeIerator() {
            pos = head;
        }

        public boolean hasNext() {
            return cnt < size;
        }

        public T next() {
            pos = (pos + 1) % deque.length;
            T returnItem = deque[pos];
            cnt++;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayDeque<T> that = (ArrayDeque<T>) o;
        if (size != that.size) return false;
        for (int i = head; i < tail; i++) {
            if (!deque[i].equals(that.deque[i])) return false;
        }
        return true;
    }

}
