package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

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
    private Set<K> set;

    public BSTMap() {
        root = null;
        set = new HashSet<>();
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
            set.add(key);
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

    public void printInOrder() {
        if (root == null) {
            System.out.println("tree is empty");
        } else {
            printInorderHelper(root);
            System.out.println();
        }
    }

    public void printInorderHelper(TreeNode<K, V> node) {
        if (node == null) {
            return;
        }
        printInorderHelper(node.left);
        System.out.print("(" + node.key + ", " + node.value + "), ");
        printInorderHelper(node.right);
    }

    @Override
    public Set<K> keySet() {
        return set;
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
        return new BSTMapIterator();
    }

    //reference - https://www.youtube.com/watch?v=RXy5RzGF5wo
    private class BSTMapIterator implements Iterator<K> {
        public Stack<TreeNode<K, V>> s;
        public BSTMapIterator() {
            s = new Stack<>();
            TreeNode<K, V> ptr = root;
            while (ptr != null) {
               s.push(ptr);
                ptr = ptr.left;
            }
        }

        public boolean hasNext() {
            return !s.isEmpty();
        }

        public K next() {
            TreeNode<K, V> node = s.pop();
            TreeNode<K, V> ptr = node.right;
            while (ptr != null) {
                s.push(ptr);
                ptr = ptr.left;
            }
            return node.key;
        }
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("Germany", 45);
        bst.put("Switzerland", 3);
        bst.put("Amsterdam", 78);
        bst.put("Amsterdam", 78);

        for (String x : bst) {
            System.out.println(x);
        }
        System.out.println(bst.keySet());
    }
}
