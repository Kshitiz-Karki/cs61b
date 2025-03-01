package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    /*
    helper function for both max() and max(Comparator c)
     */
    private T calculateMax(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            T currItem = get(i);
            if (c.compare(currItem, maxItem) > 0) {
                maxItem = currItem;
            }
        }
        return maxItem;
    }

    public T max() {
        return calculateMax(comparator);
    }

    public T max(Comparator<T> c) {
        return calculateMax(c);
    }
}
