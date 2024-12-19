package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayDeque<T> queue = (ArrayDeque<T>) o;
        if (queue.size() != size()) {
            return false;
        }
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }

    public MaxArrayDeque() {
        super();
    }

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public static <T> MaxArrayDeque<T> of(T... elements) {
        MaxArrayDeque<T> returndeque = new MaxArrayDeque<>();
        for (T element : elements) {
            if (element != null) {
                returndeque.addFirst(element);
            }
        }
        return returndeque;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 1; i < size(); i++) {
            int result = comparator.compare(max, get(i));
            if (result < 0) {
                max = get(i);
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        comparator = c;
        return max();
    }
}
