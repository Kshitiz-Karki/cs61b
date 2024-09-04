package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    //class for each node in the tree
    private static class TreeNode<K, V> {
        TreeNode<K, V> left;
        K key;
        V value;
        TreeNode<K, V> right;

        public TreeNode(K key, V value) {
            this.left = null;
            this.key = key;
            this.value = value;
            this.right = null;
        }
    }

    private TreeNode<K, V> root;

    public BSTMap() {
        root = null;
    }

    // instance methods
    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(TreeNode<K, V> node, K key) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) == 0) {
            return true;
        }
        if (node.key.compareTo(key) > 0) {
            return containsKeyHelper(node.left, key);
        }
        else {
            return containsKeyHelper(node.right, key);
        }
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node.value;
        }
        if (node.key.compareTo(key) > 0) {
            return getHelper(node.left, key);
        }
        else {
            return getHelper(node.right, key);
        }
    }

    @Override
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(TreeNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        int x = sizeHelper(node.left);
        int y = sizeHelper(node.right);
        return x + y + 1;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private TreeNode<K, V> putHelper(TreeNode<K, V> node, K key, V value) {
        if (node == null) {
            return new TreeNode<>(key, value);
        }
        if (node.key.compareTo(key) < 0) {
            node.right = putHelper(node.right, key, value);
        }
        if (node.key.compareTo(key) > 0) {
            node.left = putHelper(node.left, key, value);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
//        return Set.of();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
//        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
//        return null;
    }
}
