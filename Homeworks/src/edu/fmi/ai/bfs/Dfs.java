package edu.fmi.ai.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dfs<T extends Comparable<T>> {
    private int size;
    Set<T> visited;
    Node<T> start;

    public Dfs(Node<T> start) {
        this.start = start;
        visited = new HashSet<>();
    }

    public void traverse() {
        traverse(start);
    }

    public void traverse(Node<T> node) {
        if (!visited.contains(node.getValue())) {
            System.out.print(node.getValue() + ", ");
            visited.add(node.getValue());
            if (node.getChildren() != null) {
                for (Node<T> child : node.getChildren()) {
                    traverse(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node<Integer>[] nodes = (Node<Integer>[])new Node[8];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node<Integer>(i);
        }

        nodes[0].addChild(nodes[1]);
        nodes[1].addChild(nodes[2]);
        nodes[1].addChild(nodes[4]);
        nodes[1].addChild(nodes[3]);
        nodes[4].addChild(nodes[6]);
        nodes[4].addChild(nodes[7]);
        nodes[4].addChild(nodes[5]);
        nodes[7].addChild(nodes[0]);

        /*
        0 -> 1
        1 -> 2, 3, 4
        4 -> 5, 6, 7
        7 -> 0

          0
           \
            1
          / |  \
         2  3   4
               / | \
              6  7  5

         and 7 -> 0
         */


        Dfs<Integer> dfs = new Dfs<>(nodes[1]);
        dfs.traverse();

    }
}

class Node<T extends Comparable<T>> {
    T value;
    List<Node<T>> children;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, List<Node<T>> children) {
        this(value);
        this.children = children;
    }

    public void addChild(Node<T> child) {
        if (children == null) {
            children = new ArrayList<>();
        }

        children.add(child);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }
}


