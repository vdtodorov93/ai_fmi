package edu.fmi.ai.astar;

import java.util.*;

/**
 * Created by vasil on 11/1/15.
 */
public class Astar {
    // use 99 for format reasons. In "production" use max value.
    private final int TAKEN = 99; //Integer.MAX_VALUE;

    private int[][] field;
    private int n;
    private int m;
    private int finalX;
    private int finalY;

    //public Astar(int n, int m, int finalX, int finalY, int startX, int startY) {
    public Astar(int n, int m, int finalX, int finalY) {
        this.field = new int[n][m];
        this.n = n;
        this.m = m;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    public void findPath(int startX, int startY) {
        int [][] directions = {
                {0, 0, 1, -1},
                {1, -1, 0, 0}
        };

        Set<Node> addedNodes = new HashSet<>();
        //List<Node> path = new ArrayList<>();
        boolean foundPath = false;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node startNode = new Node(startX, startY, 0, calcManhattan(startX, startY));
        Node finalNode = new Node(finalX, finalY, 0, 0);
        pq.add(startNode);
        addedNodes.add(startNode);
        while(!pq.isEmpty()) {
            Node currentNode = pq.peek();
            pq.remove();
            if(currentNode.equals(finalNode)) {
                System.out.println("We found it!");
                System.out.println("STEPS: " + currentNode.pathTo);
                System.out.println("-------------------------------");
                foundPath = true;
                print();
                break;
            }

            //List<Node> temporateNodes = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int newX = currentNode.x + directions[0][i];
                int newY = currentNode.y + directions[1][i];

                if(isValid(newX, newY)) {
                    Node nodeToAdd = new Node(newX, newY, currentNode.pathTo + 1, calcManhattan(newX, newY));
                    if(!addedNodes.contains(nodeToAdd)) {
                        pq.add(nodeToAdd);
                        addedNodes.add(nodeToAdd);
                        field[newX][newY] = nodeToAdd.pathTo;
                    }
                }
            }
        }

        if(!foundPath) {
            System.out.println("No path found =(");
        }
    }

    public void take(int x, int y) {
        if(isValid(x, y)) {
            field[x][y] = TAKEN;
        }
    }

    public void print() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(String.format("%1$4s", field[i][j]));
            }
            System.out.println();
        }
    }

    private int calcManhattan(int x, int y) {
        return Math.abs(finalX - x) + Math.abs(finalY - y);
    }

    private boolean isValid(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m || field[x][y] == TAKEN) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Astar st = new Astar(5, 6, 0, 5);
        st.take(2, 3);
        st.take(2, 4);
        //st.take(2, 5);
        st.take(2, 2);
        st.take(2, 1);
        st.take(2, 1);
        st.print();
        st.findPath(4, 5);
    }
}

class Node implements Comparable<Node>{
    int x;
    int y;
    int pathTo;
    int manhattan;

    public Node(int x, int y, int pathTo, int manhattan) {
        this.x = x;
        this.y = y;
        this.pathTo = pathTo;
        this.manhattan = manhattan;
    }

    public int getHeuristic() {
        return pathTo + manhattan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (x != node.x) return false;
        return y == node.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(getHeuristic(), o.getHeuristic());
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", pathTo=" + pathTo +
                ", manhattan=" + manhattan +
                '}';
    }
}
