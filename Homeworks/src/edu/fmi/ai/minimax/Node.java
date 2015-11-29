package edu.fmi.ai.minimax;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Field field;
    private List<Node> anchestors;
    private int level;
    private boolean isLeaf;
    private boolean isXTurn;
    private int best;
    private Integer punningMarker;

    public Node(Field field, int level, Integer punningMarker) throws InvalidArgumentException {
        this.field = field;
        this.anchestors = new ArrayList<>();
        this.level = level;
        this.isXTurn = level % 2 == 0;
        this.punningMarker = punningMarker;

        int winner = field.getWinner();
        if(winner != 0) {
            //System.out.println("LEAF");
            //field.print();

            isLeaf = true;
            best = winner;
        } else if(level < 9) {
            if(isXTurn) {
                best = Utils.O_TARGET;
            } else {
                best = Utils.X_TARGET;
            }

            int newPunning = isXTurn ? 1 : -1;
            outer:
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(field.getField()[i][j] == Utils.EMPTY_FIELD) {
                        Field newField = new Field(field.getField());
                        if(isXTurn) {
                            newField.playX(i, j);
                        } else {
                            newField.playY(i, j);
                        }

                        Node newNode = new Node(newField, level + 1, newPunning);
                        anchestors.add(newNode);
                        int newNodeBest = newNode.best;
                        if(isXTurn) {
                            if(newNodeBest > best) {
                                best = newNodeBest;
                                if(best > punningMarker && level > 0) {
                                    break outer;
                                }
                            }
                            newPunning = best;
                        } else {
                            if(newNodeBest < best) {
                                best = newNodeBest;
                                if(best < punningMarker && level > 0) {
                                    break outer;
                                }
                            }
                            newPunning = best;
                        }
                    }
                }
            }
        } else {
            isLeaf = true;
        }
    }

    public void print() {
        field.print();
    }

    public int getBest() {
        return best;
    }

    public List<Node> getAnchestors() {
        return anchestors;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public Field getField() {
        return field;
    }
}
