package edu.fmi.ai.geneticalgo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasil.todorov on 11/2/2015.
 */
public class BagProblem {
    private List<Item> items;
    private int maxWeight;

    public BagProblem(int maxWeight) {
        this.maxWeight = maxWeight;
        items = new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println("ASD");
    }
}

class Item {
    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    String name;
    int value;
    int weight;
}
