package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class BSTNode {
        private K k;
        private V v;
        private int N;
        private BSTNode left, right;

        public BSTNode(K k, V v, int N) {
            this.k = k;
            this.v = v;
            this.N = N;
        }

        @Override
        public String toString() {
            return k.toString();
        }
    }
    private BSTNode root;

    public BSTMap() {
        root = null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode x = findKey(root, key);
        return x != null;
    }

    private BSTNode findKey(BSTNode x, K key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.k);
        if (cmp < 0)
            return findKey(x.left, key);
        else if (cmp > 0) {
            return findKey(x.right, key);
        } else
            return x;
    }

    @Override
    public V get(K key) {
        BSTNode x = findKey(root, key);
        return x != null? x.v : null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode x) {
        if (x == null)
            return 0;
        return x.N;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode x, K key, V value) {
        if (x == null) {
            return new BSTNode(key, value, 1);
        }

        int cmp = key.compareTo(x.k);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else {
            x.v = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void printInOrder() {
        StringBuilder sb = new StringBuilder();
        printInOrder(root, sb);
        System.out.println(sb.toString());
    }

    private void printInOrder(BSTNode x, StringBuilder sb) {
        if (x == null)
            return;
        printInOrder(x.left, sb);
        sb.append(x.k);
        sb.append(" ");
        printInOrder(x.right, sb);
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(root, set);
        return set;
    }

    private void keySet(BSTNode x, Set<K> set) {
        if (x == null)
            return;

        keySet(x.left, set);
        set.add(x.k);
        keySet(x.right, set);
    }

    @Override
    public V remove(K key) {
        BSTNode node = findKey(root, key);
        if (node == null)
            return null;
        root = remove(root, key);
        return node.v;
    }

    @Override
    public V remove(K key, V value) {
        BSTNode node = findKey(root, key);
        if (node == null || !node.v.equals(value))
            return null;
        root = remove(root, key);
        return node.v;
    }

    private BSTNode remove(BSTNode x, K key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.k);
        if (cmp < 0)
            x.left = remove(x.left, key);
        else if (cmp > 0)
            x.right = remove(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            BSTNode t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private BSTNode min(BSTNode x) {
        if (x == null)
            return null;
        return min(x.left);
    }

    private BSTNode deleteMin(BSTNode x) {
        if (x.left == null)
            return x.right;

        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator<>();
    }

    private class BSTMapIterator<K> implements Iterator<K>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public K next() {
            return null;
        }
    }
}
