package deque;

public class LinkedListDeque<T> {
    public class IntNode<T> {
        public IntNode<T> pre;
        public T data;
        public IntNode<T> next;

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
        }

        public IntNode(IntNode<T> newnode, T item) {
            pre = newnode;
            data = item;
            next = null;
        }
    }
    private IntNode<T> first;
    private IntNode<T> last;
    public int size;

    public LinkedListDeque() {
        first = new IntNode<>();
        last = new IntNode<>();
        first.next = last;
        last.pre = first;
        last.next = first;
        first.pre = last;
        size = 0;
    }

    public LinkedListDeque(T item) {
        first = new IntNode<>();
        IntNode<T> newnode = new IntNode<>(first, item);
        last = new IntNode<>();
        newnode.next = last;
        last.pre = newnode;
        last.next = first;
        first.pre = last;
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

    public boolean isEmpty() {
        return size == 0;
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

}
