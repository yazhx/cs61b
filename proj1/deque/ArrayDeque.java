package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private int size;
    private int front;
    private int rear;
    private T[] items;
    private final int FACTOR = 2;

    public ArrayDeque() {
        this.size = 0;
        this.front = 0;
        this.rear = 0;
        this.items = (T[]) new Object[8];
    }

    /**
     * 循环队列首尾相接，当队头指针front和队尾指针rear进到length-1后，再前进一个位置就自动到0。
     * (1)队头指针进1：front = (front + 1) % length
     * (2)队尾指针进1：rear = (rear + 1) % length
     *
     * 队尾插入新元素和删除队头元素时，两个指针都按顺时针方向进1；
     * 队头插入新元素和删除队尾元素时，两个指针都按逆时针方向减1；(front + (length - 1)) % length
     *
     * 队空条件：front == rear（或使用size==0判断）
     * 队满条件：(rear + 1) % length == front 时队满
     */
    @Override
    public void addFirst(T item) {
        if (isFull())
            resize(items.length * FACTOR);

        front = (front + items.length - 1) % items.length;
        items[front] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (isFull())
            resize(items.length * FACTOR);

        items[rear] = item;
        size += 1;
        rear = (rear + 1) % items.length;
    }

    private boolean isFull() {
        return (rear + 1) % items.length == front;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            int index = getIndex(i);
            newItems[i] = items[index];
        }
        items = newItems;
        front = 0;
        rear = size;
    }

    private int getIndex(int index) {
        return (front + index) % items.length;
    }

    @Override
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
//        return sb.toString();
    }

    @Override
    public T removeFirst() {
        if (underUsage())
            resize((int) (items.length * 0.5));

        if (isEmpty())
            return null;

        T item = items[front];
        size -= 1;
        front = (front + 1) % items.length;
        return item;
    }

    @Override
    public T removeLast() {
        if (underUsage())
            resize((int) (items.length * 0.5));

        if (isEmpty())
            return null;

        rear = (rear + items.length - 1) % items.length;
        T item= items[rear];
        size -= 1;
        return item;
    }

    private boolean underUsage() {
        float usage = (float) size / items.length;
        return (items.length >= 16) && (usage < 0.25);
    }

    @Override
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

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }
}
