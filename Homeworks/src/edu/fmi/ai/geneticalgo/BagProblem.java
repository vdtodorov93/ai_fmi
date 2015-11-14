package edu.fmi.ai.geneticalgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BagProblem {
    /*-----------------------------
                Attributes
    -----------------------------*/

    private List<Item> items;
    private boolean[] bestSolution;
    private int totalWeight; //The maximum weight the bag cannot exceed

    public BagProblem() {
        this.items = new ArrayList<>();
    }

    /*-----------------------------
                Methods
     -----------------------------*/

    public void calculateBestSolution() {
        double averageWeight = getAverageWeight();
        int itemsInMember = (int)(totalWeight / averageWeight);
        System.out.println("START NUMBER OF ITEMS: " + itemsInMember);

        Population population = new Population();
        addPopulationMembers(population, 5, itemsInMember);
        int totalIterations = items.size() * items.size();

        for(int i = 0; i < totalIterations; i++) {
            boolean[] first = population.getTopMember().getMember();
            boolean[] second = population.getTopMember().getMember();
            boolean[] crossOver = crossOver(first, second);
            mutate(crossOver);
            int weight = calculateWeight(crossOver);
            int value = calculateValue(crossOver);
            if(weight <= totalWeight) {
                population.addToPopulation(crossOver, value);
            }
        }


        bestSolution = population.top().getMember();
        mapVectorToItems();
    }

    public void mapVectorToItems() {
        int weight = 0;
        int value = 0;
        int numberOfItems = 0;
        for(int i = 0; i < bestSolution.length; i++) {
            if(bestSolution[i]) {
                System.out.print(items.get(i) + " ");
                weight += items.get(i).getWeight();
                value += items.get(i).getValue();
                numberOfItems++;
            }
        }
        System.out.println();
        System.out.println("value: " + value + " weight: " + weight + " number of items: " + numberOfItems);
    }

    private double getAverageWeight() {
        double averageWeight = items
                .stream()
                .mapToInt(Item::getWeight)
                .average()
                .getAsDouble();

        return averageWeight;
    }

    private boolean[] crossOver(boolean[] left, boolean[] right) {
        int mid = left.length / 2;
        boolean[] result = new boolean[left.length];
        for(int i = 0; i < left.length; i++) {
            if(i < mid) {
                result[i] = left[i];
            } else {
                result[i] = right[i - mid];
            }
        }
        return result;
    }

    private void mutate(boolean[] vector) {
        Random rand = new Random();
        int index = rand.nextInt(vector.length - 1);
        boolean oldIndexValue = vector[index];
        vector[index] = vector[index + 1];
        vector[index + 1] = oldIndexValue;
    }

    private void addPopulationMembers(Population population, int count, int memberSize) {
        while(count > 0) {
            boolean[] vector = generateVector(memberSize);
            int weight = calculateWeight(vector);
            if(weight <= totalWeight) {
                int value = calculateValue(vector);
                population.addToPopulation(vector, value);
                count--;
            }
        }
    }

    private boolean[] generateVector(int count) {
        int size = items.size();
        if(count > size) {
            count = size;
        }

        boolean[] result = new boolean[size];
        Random rand = new Random();
        //generates a boolean vector with size items.size() and *count* number of items.
        while(count > 0) {
            int index = rand.nextInt(size);
            if(!result[index]) {
                result[index] = true;
                count--;
            }
        }
        return result;
    }

    private int calculateWeight(boolean[] vector) {
        int weight = 0;
        for(int i = 0; i < vector.length; i++) {
            if(vector[i]) {
                weight += items.get(i).getWeight();
            }
        }

        return weight;
    }
    private int calculateValue(boolean[] vector) {
        int value = 0;
        for(int i = 0; i < vector.length; i++) {
            if(vector[i]) {
                value += items.get(i).getValue();
            }
        }

        return value;
    }


    /*-----------------------------
            Getters and setters
    -----------------------------*/

    public List<Item> getItems() {
        return items;
    }

    public boolean[] getBestSolution() {
        return bestSolution;
    }

    public void setBestSolution(boolean[] bestSolution) {
        this.bestSolution = bestSolution;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public static void main(String[] args) {
        BagProblem problem = new BagProblem();
        List<Item> items = problem.getItems();
        //name, weight, value
        items.add(new Item("noktoreza4ka", 5, 4));
        items.add(new Item("knife", 15, 40));
        items.add(new Item("water", 20, 50));
        items.add(new Item("chocolate", 5, 10));
        items.add(new Item("sandwich", 13, 40));
        items.add(new Item("gsm", 10, 45));
        items.add(new Item("map", 3, 55));
        items.add(new Item("gun", 40, 20));
        problem.setTotalWeight(50);
        problem.calculateBestSolution();
    }
}