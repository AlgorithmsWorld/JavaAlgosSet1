package com.rampatra.common;

import com.rampatra.trees.BFSUsingQueue;

import static java.lang.System.out;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ramswaroop
 * @date: 4/19/15
 * @time: 6:35 PM
 * @see: https://www.cs.bu.edu/teaching/c/tree/breadth-first/
 */
public class BinaryTree<E extends Comparable<E>> extends Tree<E> {

    public BinaryNode<E> root;
    Queue<BinaryNode<E>> queue = new LinkedQueue<>(); // needed for insertion

    /**
     * Inserts a node into the binary tree such that
     * it always forms a complete binary tree.
     *
     * @param value
     */
    public BinaryNode<E> put(E value) {
        return put(root, value);
    }

    public BinaryNode<E> put(BinaryNode<E> node, E value) {
        // create a new node from the value
        BinaryNode<E> newNode = new BinaryNode<>(value, null, null);

        if (node == null) {
            return root = queue.add(newNode);
        } else {
            BinaryNode<E> parentNode = queue.element();
            if (parentNode.left == null) {
                parentNode.left = newNode;
            } else if (parentNode.right == null) {
                parentNode.right = newNode;
                queue.remove(); // parent node has both left and right child now, so dequeue it
            }
            queue.add(newNode);
        }
        return node;
    }

    /**
     * Traversals.
     */


    /**
     * Prints the pre-order traversal of the tree.
     */
    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(BinaryNode<E> node) {
        if (node == null) {
            return;
        }
        out.print(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * Prints the in-order traversal of the tree.
     */
    public void inOrder() {
        inOrder(root);
    }

    public void inOrder(BinaryNode<E> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        out.print(node.value);
        inOrder(node.right);
    }


    /**
     * Prints the post-order traversal of the tree.
     */
    public void postOrder() {
        postOrder(root);
    }

    public void postOrder(BinaryNode<E> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        out.print(node.value);
    }


    /**
     * Prints the node of the tree breadth-wise.
     * <p/>
     * DEF: Breadth-first search (BFS) is an algorithm for traversing or searching tree
     * or graph data structures. It starts at the tree root (or some arbitrary node of a
     * graph, sometimes referred to as a `search key'[1]) and explores the neighbor nodes
     * first, before moving to the next level neighbors. See {@link BFSUsingQueue}
     * for a O(n) solution.
     * 
     * Time complexity: O(n^2)
     */
    public void breadthFirstTraversal() {
        // assuming level starts at zero
        for (int level = 0; level < height(root); level++) {
            printLevel(root, level);
        }
    }

    public void printLevel(BinaryNode<E> node, int level) {
        if (node == null) return;

        // print the starting node
        if (level == 0) {
            printValue(node);
        } else { // print the neighbour nodes            
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }

    /**
     * Deletes the entire tree.
     */
    public void delete() {
        root = null;
    }

    /**
     * Deletes a particular node from the tree
     * and rearranges the remaining nodes.
     *
     * @param value
     */
    public void delete(E value) {

    }

    /**
     * Deletes all child nodes of {@param node}.
     *
     * @param node
     */
    public void deleteChildren(BinaryNode<E> node) {
        if (node == null) {
            return;
        }
        node.left = null;
        node.right = null;
    }


    /**
     * Return the height of the tree.
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    public int height(BinaryNode<E> node) {
        if (node == null) return 0;

        return Math.max(height(node.left), height(node.right)) + 1;
    }


    /**
     * Returns the number of nodes currently in the tree.
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    public int size(BinaryNode<E> node) {
        if (node == null) {
            return 0;
        } else {
            return size(node.left) + 1 + size(node.right);
        }
    }

    /**
     * Tests if this tree is empty.
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }


    /**
     * The diameter of a tree (sometimes called the width) is the number
     * of nodes on the longest path between two leaves in the tree.
     *
     * @return the diameter of the tree.
     */
    public int diameter() {
        return diameter(root);
    }

    public int diameter(BinaryNode<E> node) {
        if (node == null) return 0;

        // diameter of current node
        int diameter = height(node.left) + height(node.right) + 1;

        // return max diameters of current node, left sub-tree and right sub-tree
        return Math.max(diameter, Math.max(diameter(node.left), diameter(node.right)));
    }


    /**
     * Width is the number of nodes in a particular level.
     *
     * @return maximum width of the tree.
     */
    public int width() {
        return width(root, 0);
    }

    public int width(BinaryNode<E> node, int width) {
        if (node == null) return 0;

        if (node.left == null && node.right == null) return 1; // for single/leaf node

        int levelWidth = width(node.left, width) + width(node.right, width);

        // to find max width
        if (levelWidth > width) width = levelWidth;

        return width;
    }

    public void printValue(BinaryNode<E> node) {
        if (node == null) return;

        out.print(node.value);
    }

    // test cases
    public static void main(String[] a) {
        BinaryTree<Integer> bt = new BinaryTree<>();
        bt.put(1);
        bt.put(2);
        bt.put(3);
        bt.put(4);
        bt.put(5);
        bt.put(6);
        bt.put(7);
        bt.put(8);
        bt.breadthFirstTraversal();
        out.println();
        bt.inOrder();
    }
}
