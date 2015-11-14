package edu.fmi.ai.geneticalgo;

/**
 * Created by vasil on 11/14/15.
 */
public class Item {
    public Item() {
    }

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    private String name;
    private int weight;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
