package Try;//LEC9

public class RotatingSLList<T> extends SLList<T> {
    public void rotateRight() {
        T item = removeLast();
        addFirst(item);
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> list = new RotatingSLList<>();
        list.addLast(9);
        list.addLast(15);
        list.addLast(22);
        list.rotateRight();
    }
}