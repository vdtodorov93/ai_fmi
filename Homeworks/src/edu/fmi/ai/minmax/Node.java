package edu.fmi.ai.minmax;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasil on 11/27/15.
 */
public class Node {
    /*----------------------
            Members
     ----------------------*/

    private TicTacField field;
    private boolean isXTurn;
    private List<Node> childStates;
    private Integer winner;
    private int depth;


    /*----------------------
        Constructors
     ----------------------*/

    public Node() {
        childStates = new ArrayList<>();
    }


    /*----------------------
            Methods
     ----------------------*/

    public void buildTree() {
        if(depth == 9) {
            checkWinner();
            return;
        }
    }

    private void checkWinner() {
        Integer result = field.checkWinner();
    }


    /*----------------------
        Getters and Setters
     ----------------------*/

    public TicTacField getField() {
        return field;
    }

    public void setField(TicTacField field) {
        this.field = field;
    }

    public boolean isXTurn() {
        return isXTurn;
    }

    public void setIsXTurn(boolean isXTurn) {
        this.isXTurn = isXTurn;
    }

    public List<Node> getChildStates() {
        return childStates;
    }

    public void setChildStates(List<Node> childStates) {
        this.childStates = childStates;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
