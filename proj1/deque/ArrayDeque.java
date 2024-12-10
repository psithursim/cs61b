package deque;

public class ArrayDeque<T> {
    public T[] deque;
    public int size;
    public int head;
    public int tail;

    public ArrayDeque() {
        deque = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    public void resize(int newSize) {
        T[] newDeque = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newDeque[i] = deque[(head + i) % deque.length];
        }
        deque = newDeque;
    }

    public void addFirst(T item) {
        if (size == deque.length) {
            resize(size * 2);
        }
        head = (head - 1 + deque.length) % deque.length;
        deque[head] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == deque.length) {
            resize(size * 2);
        }
        tail++;
        deque[tail] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
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
        T item = deque[head];
        deque[head] = null;
        head = (head + 1 + deque.length) % deque.length;
        size--;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size < deque.length / 4) {
            resize(size / 4);
        }
        T item = deque[tail];
        deque[tail] = null;
        tail = (tail - 1 + deque.length) % deque.length;
        size--;
        return item;
    }

    public T get(int index) {
        int temp = (head + index) % deque.length;
        return deque[temp];
    }

}
