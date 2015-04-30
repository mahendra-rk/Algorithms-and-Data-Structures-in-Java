package me.ramswaroop.trees;

import me.ramswaroop.common.BinaryNode;
import me.ramswaroop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ramswaroop
 * Date: 4/19/15
 * Time: 6:35 PM
 * To change this template go to Preferences | IDE Settings | File and Code Templates
 */
public class BinaryTree<E extends Comparable<E>> extends Tree<E> {

    BinaryNode<E> root;

    /**
     * Checks whether two trees having their roots at node1 and node2
     * are identical or not.
     *
     * @param node1
     * @param node2
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean isIdentical(BinaryNode<E> node1, BinaryNode<E> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null && node2 != null || (node1 != null && node2 == null)) return false;

        if (node1.value == node2.value) {
            return true && isIdentical(node1.left, node2.left) && isIdentical(node1.right, node2.right);
        } else {
            return false;
        }
    }

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
        Utils.print(node.value);
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
        Utils.print(node.value);
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
        Utils.print(node.value);
    }

    /**
     * Prints the node of the tree breadth-wise.
     * <p/>
     * DEF: Breadth-first search (BFS) is an algorithm for traversing or searching tree
     * or graph data structures. It starts at the tree root (or some arbitrary node of a
     * graph, sometimes referred to as a `search key'[1]) and explores the neighbor nodes
     * first, before moving to the next level neighbors.
     */
    public void breadthFirstTraversal() {
        // assuming level starts at zero
        breadthFirstTraversal(root, 0);
    }

    public void breadthFirstTraversal(BinaryNode<E> node, int level) {
        if (node == null) return;

        // print the starting node
        if (level == 0) printValue(node);

        // print the neighbour nodes
        printValue(node.left);
        printValue(node.right);

        // go to next level
        level++;
        breadthFirstTraversal(node.left, level);
        breadthFirstTraversal(node.right, level);
    }

    /**
     * Deletes a particular node from the tree.
     *
     * @param value
     */
    public void delete(E value) {

    }

    /**
     * TODO
     * Deletes the entire tree.
     */
    public void delete() {
        delete(root);
        root = null;
    }

    public void delete(BinaryNode<E> node) {
        if (node == null) {
            return;
        }
        // first delete the child nodes
        delete(node.left);
        delete(node.right);
        node = null; // delete node
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
     * Checks whether this tree and another with @param node
     * as root are identical or not.
     *
     * @param node
     * @return
     */
    public boolean isIdentical(BinaryNode<E> node) {
        return isIdentical(this.root, node);
    }

    /**
     * Converts a Tree to its Mirror Tree.
     * <p/>
     * MIRROR OF A BINARY TREE T is another Binary Tree M(T) with
     * left and right children of all non-leaf nodes interchanged.
     * <p/>
     * TIP: In-order traversal of mirror tree is exactly the
     * reverse of the in-order traversal of the original tree.
     */
    public void mirror() {
        mirror(root);
    }

    public void mirror(BinaryNode<E> node) {
        if (node == null) return;

        BinaryNode<E> tempNode;

        // mirror sub-trees
        mirror(node.left);
        mirror(node.right);

        // swap nodes
        tempNode = node.left;
        node.left = node.right;
        node.right = tempNode;
    }

    /**
     * Prints the node to leaf paths, one per line.
     * (Using array)
     */
    /*public void rootToLeafPaths() {
        E[] pathList = (E[]) new Object[100];
        rootToLeafPaths(root, pathList, 0);
    }

    public void rootToLeafPaths(BinaryNode<E> node, E[] pathList, int pathLength) {
        if (node == null) return;

        pathList[pathLength] = node.value;
        pathLength++;

        // if its a leaf node then print the list
        if (node.left == null && node.right == null) {
            int i;
            for (i = 0; i < pathLength - 1; i++) {
                Utils.print(pathList[i] + " -> ");
            }
            // outside the loop so that "->" doesn't appear after the last node
            Utils.println(pathList[i]);
        } else {
            // do the same for subtrees
            rootToLeafPaths(node.left, pathList, pathLength);
            rootToLeafPaths(node.right, pathList, pathLength);
        }
    }*/

    /**
     * Prints the node to leaf paths, one per line.
     * (Using ArrayList)
     */
    public void rootToLeafPaths() {
        List<E> pathList = new ArrayList<>();
        rootToLeafPaths(root, pathList);
    }

    public void rootToLeafPaths(BinaryNode<E> node, List<E> pathList) {
        if (node == null) return;

        pathList.add(node.value);

        // if its a leaf node then print the list
        if (node.left == null && node.right == null) {
            int i;
            for (i = 0; i < pathList.size() - 1; i++) {
                Utils.print(pathList.get(i) + " -> ");
            }
            // outside the loop so that "->" doesn't appear after the last node
            Utils.println(pathList.get(i));
        } else {
            // do the same for subtrees
            rootToLeafPaths(node.left, new ArrayList<>(pathList));
            rootToLeafPaths(node.right, new ArrayList<>(pathList));
        }
    }


    /**
     * Utility methods.
     */

    private void printValue(BinaryNode<E> node) {
        if (node == null) return;

        Utils.print(node.value);
    }
}
