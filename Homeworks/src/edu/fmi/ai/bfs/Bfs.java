package edu.fmi.ai.bfs;

import java.util.*;

public class Bfs<T extends Comparable<T>> {
    private Map<T, List<T>> graph;
    private boolean[] visited;
    Map<T, Integer> positions;

    public Bfs(Map<T, List<T>> graph) {
        //initializing
        this.graph = graph;
        positions = new HashMap<>();

        //map each element with position
        int position = 0;
        for (T node : graph.keySet()) {
            positions.put(node, position++);
        }
        visited = new boolean[position];
    }

    public void traverse(T start) {
        Queue<T> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            T current = queue.peek();
            queue.remove();
            int currentPosition = positionOf(current);
            if (!visited[currentPosition]) {
                System.out.print(current + " ");
                if (graph.containsKey(current)) {
                    for (T child : graph.get(current)) {
                        queue.add(child);
                    }
                }
                visited[currentPosition] = true;
            }
        }
    }

    private int positionOf(T element) {
        return positions.get(element);
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3, 4));
        graph.put(2, Arrays.asList(4, 5, 6));
        graph.put(3, Arrays.asList());
        graph.put(4, Arrays.asList(7, 8, 9));
        graph.put(5, Arrays.asList(7, 8, 9));
        graph.put(6, Arrays.asList());
        graph.put(7, Arrays.asList());
        graph.put(8, Arrays.asList());
        graph.put(9, Arrays.asList());
        Bfs<Integer> bfs = new Bfs<>(graph);
        bfs.traverse(2);

        /*
        kind of dummy, dfs is better (used Set for visited)

        1 -> 2, 3, 4
        2 -> 4, 5, 6
        3 ->
        4 -> 7, 8, 9
        5 -> 7, 8, 9
        6 ->
        7 ->
        8 ->
        9 ->

         */

    }
}
