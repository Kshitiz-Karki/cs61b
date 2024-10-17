package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private static class TreeNode<K, V> {
        private final K key;
        private V value;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;

        private TreeNode(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    private TreeNode<K, V> root;
    private int size;
    private Set<K> keys;

    public BSTMap() {
        root = null;
        size = 0;
        keys = new HashSet<>();
    }


    @Override
    public void clear() {
        root = null;
        size = 0;
        keys = null;
    }

    private boolean containsKeyHelper(TreeNode<K, V> node, K key) {
        if (node == null) return false;
        if (node.key.compareTo(key) == 0) return true;
        else if (node.key.compareTo(key) < 0) return containsKeyHelper(node.left, key);
        else return containsKeyHelper(node.right, key);
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private V getHelper(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        if (node.key.compareTo(key) == 0) return node.value;
        else if (node.key.compareTo(key) < 0) return getHelper(node.left, key);
        else return getHelper(node.right, key);
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    @Override
    public int size() {
        return size;
    }

    private TreeNode<K, V> putHelper(TreeNode<K, V> node, K key, V value) {
        if (node == null) return new TreeNode<>(key, value);
        if (node.key.compareTo(key) == 0) node.value = value;
        else if (node.key.compareTo(key) < 0) node.left = putHelper(node.left, key, value);
        else node.right = putHelper(node.right, key, value);
        return node;
    }

    @Override
    public void put(K key, V value) {
        keys.add(key);
        root = putHelper(root, key, value);
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private final Iterator<K> keysIterator;

        public BSTMapIterator() {
            keysIterator = keys.iterator();
        }

        @Override
        public boolean hasNext() {
            return keysIterator.hasNext();
        }

        @Override
        public K next() {
            return keysIterator.next();
        }
    }

    private void printInOrderHelper(TreeNode<K, V> node) {
        if (node == null) return;
        printInOrderHelper(node.left);
        System.out.print(node.key + " ");
        printInOrderHelper(node.right);
    }

    public void printInOrder() {
        printInOrderHelper(root);
        System.out.println();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("Berlin", 3);
        map.put("Amsterdam", 4);
        map.put("Zurich", 7);
        map.put("London", 10);
        for (String city: map) {
            System.out.print(city + " ");
        }
        System.out.println();
        map.printInOrder();
        System.out.println("containsKey('Amsterdam'): " + map.containsKey("Amsterdam"));
    }
}
