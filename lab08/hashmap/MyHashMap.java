package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor;
    private int M;
    private int N = 0;
    private Set<K> keys;
    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        M = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(initialSize);
        keys = new HashSet<>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = (Collection<Node>[]) new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        MyHashMap<K, V> ks = new MyHashMap<>();
        buckets = ks.buckets;
        M = ks.M;
        N = ks.N;
    }

    @Override
    public boolean containsKey(K key) {
        Node node = getNode(key);
        return node != null;
    }

    private Node getNode(K key) {
        int i = hash(key);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) return node;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null? null : node.value;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void put(K key, V value) {
        if (N / M >= loadFactor) resize(2 * M);
        int i = hash(key);
        if (!containsKey(key)) {
            keys.add(key);
        } else {
            remove(key);
        }
        N++;
        buckets[i].add(new Node(key, value));
    }

    private void resize(int chains) {
        MyHashMap<K, V> ks = new MyHashMap<>(chains);
        for (int i = 0; i < M; i++) {
            for (Node node : buckets[i]) {
                ks.put(node.key, node.value);
            }
        }

        this.M = ks.M;
        this.N = ks.N;
        this.buckets = ks.buckets;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        int i = hash(key);
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException("No such key!");
        buckets[i].remove(node);
        keys.remove(key);
        N--;
        return node.value;
    }

    @Override
    public V remove(K key, V value) {
        int i = hash(key);
        Node node = getNode(key);
        if (node == null || node.value != value)
            throw new IllegalArgumentException("No such Node!");

        buckets[i].remove(node);
        keys.remove(key);
        N--;
        return node.value;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

}
