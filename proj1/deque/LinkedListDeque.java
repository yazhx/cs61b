package deque;

public class LinkedListDeque<T> implements Deque<T>{
    public static class Node<T> {
        public T item;
        public Node<T> pre;
        public Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private final Node<T> sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node(-1, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<T>(item, null);
        sentinel.next.pre = node;
        node.next = sentinel.next;
        sentinel.next = node;
        node.pre = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> node = new Node<T>(item, null);
        sentinel.pre.next = node;
        node.pre = sentinel.pre;
        node.next = sentinel;
        sentinel.pre = node;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> node = sentinel.next;
        StringBuilder sb = new StringBuilder();
        while (node != sentinel) {
            sb.append(node.item);
            sb.append(" ");
            node = node.next;
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            return null;

        Node<T> node = sentinel.next;
        sentinel.next = node.next;
        node.next.pre = sentinel;
        node.pre = null;
        node.next = null;
        size -= 1;
        return node.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            return null;

        Node<T> node = sentinel.pre;
        sentinel.pre = node.pre;
        node.pre.next = sentinel;
        node.pre = null;
        node.next = null;
        size -= 1;
        return node.item;
    }

    @Override
    public T get(int index) {
        if (index > size)
            return null;

        Node<T> node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index -= 1;
        }
        return node.item;
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque<?> other))
            return false;

        if (size != other.size())
            return false;

        Node<T> p1 = sentinel.next;
        Node<?> p2 = other.sentinel.next;
        while (p1 != sentinel) {
            if (!p1.item.equals(p2.item))
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    public T getRecursive(int index) {
        if (index >= size)
            return null;

        Node<T> node = getHelper(sentinel.next, index);
        return node.item;
    }

    private Node<T> getHelper(Node<T> node, int index) {
        if (index == 0)
            return node;

        return getHelper(node.next, index - 1);
    }
}
