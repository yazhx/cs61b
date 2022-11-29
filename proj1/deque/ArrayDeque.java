package deque;

public class ArrayDeque <T>{
    private int size;
    private int first;
    private int last;
    private T[] items;
    private final int FACTOR = 2;

    public ArrayDeque() {
        this.size = 0;
        this.first = 0;
        this.last = 0;
        this.items = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        if (isFull())
            resize(size * FACTOR);

        size += 1;
        first = (first + (items.length - 1)) % items.length;
        items[first] = item;
    }

    public void addLast(T item) {
        if (isFull())
            resize(size * FACTOR);

        items[last++] = item;
        size += 1;
    }

    private boolean isFull() {
        return size == items.length;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            int index = getIndex(i);
            newItems[i] = items[index];
        }
        items = newItems;
        first = 0;
    }

    private int getIndex(int i) {
        return (first + i) % items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = getIndex(i);
            sb.append(items[index]);
            sb.append(" ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    public T removeFirst() {
        if (underUsage())
            resize((int) (items.length * 0.5));

        size -= 1;
        return items[first++];
    }

    public T removeLast() {
        if (underUsage())
            resize((int) (items.length * 0.5));

        size -= 1;
        return items[--last];
    }

    private boolean underUsage() {
        float usage = (float) size / items.length;
        return (items.length >= 16) && (usage < 0.25);
    }

    public T get(int index) {
        if (index >= size)
            return null;

        int actualIndex = getIndex(index);
        return items[actualIndex];
    }

    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque<?> other))
            return false;

        if (size != other.size())
            return false;

        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i)))
                return false;
        }
        return true;
    }
}
