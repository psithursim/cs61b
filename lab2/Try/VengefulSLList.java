package Try;//LEC9

public class VengefulSLList<T> extends SLList<T> {
    public SLList<T> deletedItems;

    public VengefulSLList() {
        deletedItems = new SLList<T>();
    }

    public void printLostItems() {
        deletedItems.print();
    }

    @Override
    public T removeLast() {
        T item = super.removeLast();
        deletedItems.addLast(item);
        return item;
    }
}