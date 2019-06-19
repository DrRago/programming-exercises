package de.dhbwka.exercise.collections;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * Binary tree
 *
 * @author Leonhard Gahr
 */
@Getter
@Setter
public class BinaryTree<T extends Comparable<T>> {
    private T node;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    /**
     * empty constructor creates empty tree
     */
    public BinaryTree() {
        this(null);
    }

    /**
     * Create tree with node value but no right or left tree
     *
     * @param node the node value
     */
    public BinaryTree(T node) {
        this(node, null, null);
    }

    /**
     * Create full tree
     *
     * @param node  the node value
     * @param left  the left tree
     * @param right the right tree
     */
    public BinaryTree(T node, BinaryTree<T> left, BinaryTree<T> right) {
        this.node = node;
        this.left = left;
        this.right = right;
    }

    /**
     * add a value to the tree
     *
     * @param value the value to add
     * @return whether the add was successful or a duplicate
     */
    public boolean add(T value) {
        if (this.node == null) {
            this.node = value;
            return true;
        }

        if (this.node.compareTo(value) < 0) {
            if (this.left == null) {
                this.left = new BinaryTree<>(value);
                return true;
            }
            return this.left.add(value);
        } else if (this.node.compareTo(value) > 0) {
            if (this.right == null) {
                this.right = new BinaryTree<>(value);
                return true;
            }
            return this.right.add(value);
        }

        return false;
    }

    /**
     * return a sorted list of all node values
     *
     * @return the list of node values
     */
    public List<T> traverse() {
        LinkedList<T> result = new LinkedList<>();
        if (this.node != null) {
            result.addAll(this.left != null ? this.left.traverse() : new LinkedList<>());
            result.add(this.node);
            result.addAll(this.right != null ? this.right.traverse() : new LinkedList<>());
        }
        return result;
    }

    @Override
    public String toString() {
        return traverse().toString();
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>(12);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(22);

        System.out.println(tree);
    }
}
