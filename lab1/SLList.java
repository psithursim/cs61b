public class SLList<T> {
    public static class IntNode<T> {
        public T item;
        public IntNode<T> next;

        public IntNode(T x, IntNode<T> n) {
            item = x;
            next = n;
        }

        public IntNode(IntNode<T> n) {
            next = n;
        }
    }

    public IntNode<T> first;
    public int size;

    public SLList() {
        first = null;
        size = 0;
        first = new IntNode<T>(first);
    }

    public SLList(T x) {
        first = new IntNode<T>(x, null);
        size = 1;
        first = new IntNode<T>(first);
    }

    public void addFirst(T x) {
        first.next = new IntNode<T>(x, first.next);
        size++;
    }

    public void addLast(T x) {
        size++;
        IntNode<T> p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode<T>(x, null);
    }

    public T removeLast() {
        IntNode<T> p = first;
        while (p.next.next != null) {
            p = p.next;
        }
        T item = p.next.item;
        p.next = null;
        return item;
    }

    public void print() {
        IntNode<T> p = first;
        while (p != null) {
            System.out.print(p.item + " ");
        }
    }
}
