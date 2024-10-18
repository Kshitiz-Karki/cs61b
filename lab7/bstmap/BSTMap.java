package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*****************************************************
    BSTMap with size as an instance variable
 *****************************************************/
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private static class TreeNode<K, V> {
        private TreeNode<K, V> left;
        private K key;
        private V value;
        private TreeNode<K, V> right;

        private TreeNode(K key, V value) {
            left = null;
            this.key = key;
            this.value = value;
            right = null;
        }
    }

    private TreeNode<K, V> root;
    private Set<K> keys;
    private int size;

    public BSTMap() {
        root = null;
        keys = new HashSet<>();
        size = 0;
    }


    @Override
    public void clear() {
        root = null;
        keys = null;
        size = 0;
    }

    private boolean containsKeyHelper(TreeNode<K, V> node, K key) {
        if (node == null) return false;
        if (node.key.compareTo(key) == 0) return true;
        else if (node.key.compareTo(key) < 0) return containsKeyHelper(node.right, key);
        else return containsKeyHelper(node.left, key);
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private V getHelper(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        if (node.key.compareTo(key) == 0) return node.value;
        else if (node.key.compareTo(key) < 0) return getHelper(node.right, key);
        else return getHelper(node.left, key);
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
        if (node == null) {
            size++;
            return new TreeNode<>(key, value);
        }
        if (node.key.compareTo(key) == 0) node.value = value;
        else if (node.key.compareTo(key) < 0) node.right = putHelper(node.right, key, value);
        else node.left = putHelper(node.left, key, value);
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

    private boolean isLeafNode(TreeNode<K, V> node) {
        return node != null && node.left == null && node.right == null;
    }

    private boolean hasOnlyOneChild(TreeNode<K, V> node) {
        return node != null && ((node.left == null && node.right != null) || (node.left != null && node.right == null));
    }

    private boolean hasBothChild(TreeNode<K, V> node) {
        return node != null && node.left != null && node.right != null;
    }

    private int height(TreeNode<K, V> node) {
        if (node == null) return 0;
        int x = height(node.left);
        int y = height(node.right);
        return x > y ? x + 1 : y + 1;
    }

    private TreeNode<K, V> predecessorSuccessorNode(TreeNode<K, V> node) {
        TreeNode<K, V> retNode;
        if (height(node.left) > height(node.right)) {
            //return predecessor
            retNode = node.left;
            while (retNode.right != null) {
                retNode = retNode.right;
            }
        } else {
            //return successor
            retNode = node.right;
            while (retNode.left != null) {
                retNode = retNode.left;
            }
        }
        return retNode;
    }

    private TreeNode<K, V> removeHelper(TreeNode<K, V> node, K key) {
        //if key is not present
//        if (node == null) return null;
        //case 1:   deletion key is a leaf node
        if (node.key.compareTo(key) == 0 && isLeafNode(node)) {
            size--;
            return null;
        }
        //case 2:   deletion key has only one child
        else if (node.key.compareTo(key) == 0 && hasOnlyOneChild(node)) {
            size--;
            if (node.left == null) return node.right;
            else return node.left;
        }
        //case 3:   deletion key has both left and right child
        else if (node.key.compareTo(key) == 0 && hasBothChild(node)) {
            //Hibbard deletion method
            TreeNode<K, V> delNode = predecessorSuccessorNode(node);
            removeHelper(root, delNode.key);
            node.key = delNode.key;
            node.value = delNode.value;
        } else if (node.key.compareTo(key) < 0) node.right = removeHelper(node.right, key);
        else node.left = removeHelper(node.left, key);
        return node;
    }

    @Override
    public V remove(K key) {
        V retValue = get(key);
        if (retValue == null) return null;
        keys.remove(key);
        TreeNode<K, V> retNode = removeHelper(root, key);
        if (retNode == null) {
            this.clear();
//            return null;
        }
        if (retNode != null && retNode != root) root = retNode;
        return retValue;
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

//        public static void main(String[] args) {
//            BSTMap<String, Integer> map = new BSTMap<>();
//            map.put("Berlin", 3);
//            map.put("Amsterdam", 4);
//            map.put("Zurich", 7);
//            map.put("London", 10);
//            for (String city: map) {
//                System.out.print(city + " ");
//            }
//            System.out.println();
//            map.printInOrder();
//            System.out.println("containsKey('Amsterdam'): " + map.containsKey("Amsterdam"));
//            System.out.println("BSTMap size: " + map.size());
//            map.remove("Amsterdam");
//            map.printInOrder();
//            System.out.println("BSTMap size: " + map.size());
//            map.put("dog", 1);
//            map.put("bag", 2);
//            map.put("flat", 3);
//            map.put("alf", 4);
//            map.put("cat", 5);
//            map.put("elf", 6);
//            map.put("glut",7);
//            map.put("eyes", 8);
//            System.out.println("keys: " + map.keySet());
//            map.printInOrder();
//            System.out.println("BSTMap size: " + map.size());
//            map.remove("glut");
//            map.printInOrder();
//            System.out.println("BSTMap size: " + map.size());
//            map.remove("flat");
//            map.printInOrder();
//            System.out.println("BSTMap size: " + map.size());
//            map.remove("dog");
//            map.printInOrder();
//            System.out.println("BSTMap size: " + map.size());
//            map.remove("mouse");
//            map.printInOrder();
//            System.out.println("BSTMap size: " + map.size());
//            System.out.println("keys: " + map.keySet());
//        }
}
