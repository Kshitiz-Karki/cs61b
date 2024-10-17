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

    private void printInorderHelper(TreeNode<K, V> node) {
        if (node == null) {
            return;
        }
        printInorderHelper(node.left);
        System.out.print("(" + node.key + ", " + node.value + "), ");
        printInorderHelper(node.right);
    }

    @Override
    public Set<K> keySet() {
        return Set.of(this.iterator().next());
    }

    @Override
    public V remove(K key) {
        return removeHelper(root, key, root);
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private V removeHelper(TreeNode<K,V> node, K key, TreeNode<K,V> parent) {
        //case1: deletion node has no children (just sever the parent's link and the deletion key will be garbage collected)
        //case2: deletion node has one child (move deletion key's parent's pointer to deletion key's child)
        if (node == null) {
            System.out.println("key not present");
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            //case 3: deletion node has both left and right child (delete inorder successor/predecessor and update deletion node's item with the same)
            if (node.left != null && node.right != null) {
                TreeNode<K,V> ip = inorderPredecessorSuccessor(node);
                remove(ip.key);
                V deletedItem = node.value;
                node.key = ip.key;
                node.value = ip.value;
                return deletedItem;
            }
            //case 3, end
            if (key.compareTo(parent.key) > 0) {
                parent.right = node.left != null ? node.left : node.right;
            } else {
                parent.left = node.left != null ? node.left : node.right;
            }
            return node.value;
        }
        if (node.key.compareTo(key) > 0) {
            return removeHelper(node.left, key, node);
        } else {
            return removeHelper(node.right, key, node);
        }
    }

    private TreeNode<K,V> inorderPredecessorSuccessor(TreeNode<K,V> node) {
        TreeNode<K,V> ptr;
        if (height(node.left) > height(node.right)) {
            //inorder predecessor
            ptr = node.left;
            while (ptr.right != null) {
                ptr = ptr.right;
            }
        } else {
            //inorder successor
            ptr = node.right;
            while (ptr.left != null) {
                ptr = ptr.left;
            }
        }
        return ptr;
    }

    private int height(TreeNode<K,V> root) {
        if (root == null) {
            return 0;
        }
        int x = height(root.left);
        int y = height(root.right);
        if (x > y) {
            return x + 1;
        }
        return y + 1;
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

//    public static void main(String[] args) {
//        BSTMap<String, Integer> bst = new BSTMap<>();
//        bst.put("Germany", 45);
//        bst.put("Switzerland", 3);
//        bst.put("Amsterdam", 78);
//        bst.put("Amsterdam", 78);
//
//        for (String x : bst) {
//            System.out.println(x);
//        }
//        System.out.println(bst.keySet());
//    }
}
