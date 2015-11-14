package edu.fmi.ai.geneticalgo;

import java.util.Arrays;

public class PopulationMember implements Comparable<PopulationMember> {
    private boolean[] member;
    private int value;

    public PopulationMember(boolean[] member, int value) {
        this.member = member;
        this.value = value;
    }

    public boolean[] getMember() {
        return member;
    }

    public void setMember(boolean[] member) {
        this.member = member;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PopulationMember populationMember1 = (PopulationMember) o;

        if (value != populationMember1.value) return false;
        return Arrays.equals(member, populationMember1.member);

    }

    @Override
    public int hashCode() {
        int result = member != null ? Arrays.hashCode(member) : 0;
        result = 31 * result + value;
        return result;
    }

    @Override
    public int compareTo(PopulationMember o) {
        return -Integer.compare(this.value, o.value);
    }
}