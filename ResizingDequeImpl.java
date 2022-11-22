import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingDequeImpl<E> implements ResizingDeque<E> {
    private E[] array;
    private int head;
    private int tail;
    private int elementNum;

    public ResizingDequeImpl() {
        array = (E[]) new Object[2];
        head = 1;
        tail = 1;
        elementNum = 0;
    }

    public ResizingDequeImpl(E[] eArr, int h, int t, int en) {
        array = eArr;
        head = h;
        tail = t;
        elementNum = en;
    }

    @Override
    public int size() {
        return elementNum;
    }

    @Override
    public E[] getArray() {
        return array;
    }

    private int arraySize() {
        return ((Object[]) array).length;
    }

    private void newArrayAdd() {
        E[] nArray = (E[]) new Object[arraySize() * 2];
        int i = head;
        int counter = 0;
        while (counter < arraySize()) {
            if (i >= arraySize()) {
                i = 0;
            }
            nArray[counter] = array[i];
            counter++;
            i++;
        }
        head = 0;
        tail = arraySize() - 1;
        array = nArray;
    }

    @Override
    public void addFirst(E e) {
        if (arraySize() == elementNum) {
            newArrayAdd();
        }

        if (head == 0) {
            head = arraySize() - 1;
        } else {
            head--;
        }
        array[head] = e;
        elementNum++;
        if (size() == 1) {
            head = 0;
            tail = 0;
        }
    }

    @Override
    public void addLast(E e) {
        if (arraySize() == elementNum) {
            newArrayAdd();
        }

        if (tail == arraySize() - 1) {
            tail = 0;
        } else {
            tail++;
        }
        array[tail] = e;
        elementNum++;
        if (size() == 1) {
            head = 0;
            tail = 0;
        }
    }

    private void newArraySub() {
        E[] nArray = (E[]) new Object[arraySize() / 2];
        int counter = 0;

        if (size() == 0) {
            head = 1;
            tail = 1;
        } else if (head < tail) {
            for (int i = head; i < tail + 1; i++) {
                nArray[counter] = array[i];
                counter++;
            }
            tail = counter - 1;
            head = 0;
        } else {
            int i = head;

            while (counter < arraySize() / 2) {
                if (i >= arraySize()) {
                    i = 0;
                }
                nArray[counter] = array[i];
                counter++;
                i++;
            }
            tail = counter - 1;
            head = 0;
        }
        array = nArray;
    }

    @Override
    public E pollFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        E removed = array[head];
        array[head] = null;
        if (head >= arraySize() - 1) {
            head = 0;
        } else {
            head++;
        }
        elementNum--;
        if (size() == 0) {
            head = 1;
            tail = 1;
        }
        if (elementNum < arraySize() / 4) {
            newArraySub();
        }
        return removed;
    }

    @Override
    public E pollLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        E removed = array[tail];
        array[tail] = null;
        if (tail == 0) {
            tail = arraySize() - 1;
        } else {
            tail--;
        }
        elementNum--;
        if (size() == 0) {
            head = 1;
            tail = 1;
        }
        if (elementNum < arraySize() / 4) {
            newArraySub();
        }
        return removed;
    }

    @Override
    public E peekFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return array[tail];
    }

    @Override
    public Iterator<E> iterator() {

        return new IteratorOverride<>(array);
    }

    private class IteratorOverride<E> implements Iterator<E> {
        private E[] currArray;
        private int pos = head - 1;
        private boolean looped = false;

        public IteratorOverride(E[] eArray) {
            currArray = eArray;
        }

        @Override
        public boolean hasNext() {
            int temPos = pos + 1;
            if (temPos >= arraySize()) {
                temPos = 0;
                looped = true;
            }
            return ((temPos <= tail && tail < arraySize() - 1) || !looped)
                    && currArray[temPos] != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                pos++;
                if (pos >= arraySize()) {
                    pos = 0;
                }
                return (E) currArray[pos];
            } else {
                throw new NoSuchElementException();
            }

        }
    }
}

