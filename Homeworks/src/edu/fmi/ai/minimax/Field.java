package edu.fmi.ai.minimax;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;

public class Field {

    // ** MEMBERS **
    private char[][] field;

    // ** CONSTRUCTORS **
    public Field() {
        field = new char[3][3];
    }

    public Field(char[][] field) {
        this();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.field[i][j] = field[i][j];
            }
        }
    }

    // ** PUBLIC METHODS **
    public void playX(int i, int j) throws InvalidArgumentException {
        checkField(i,j);
        field[i][j] = Utils.X_FIELD;
    }

    public void playY(int i, int j) throws InvalidArgumentException {
        checkField(i,j);
        field[i][j] = Utils.O_FIELD;
    }

    public int calculateTurns() {
        int turns = 9;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(field[i][j] == Utils.EMPTY_FIELD) {
                    turns--;
                }
            }
        }

        return turns;
    }

    public int getWinner() {
        for(int i = 0; i < 3; i++) {
            if(field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != Utils.EMPTY_FIELD) { // ROW
                return winnerFromChar(field[i][0]);
            }
            if(field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != Utils.EMPTY_FIELD) { // COL
                return winnerFromChar(field[0][i]);
            }
        }

        if(field[0][0] == field[1][1] && field[0][0]== field[2][2] && field[0][0] != Utils.EMPTY_FIELD) { // MAIN DIAGONAL
            return winnerFromChar(field[0][0]);
        }
        if(field[2][0] == field[1][1] && field[2][0]== field[0][2] && field[2][0] != Utils.EMPTY_FIELD) { // SECOND DIAGONAL
            return winnerFromChar(field[2][0]);
        }

        return 0; //DRAW
    }

    public int winnerFromChar(char player) {
        //System.out.println(player);
        return player == Utils.X_FIELD ? Utils.X_TARGET : Utils.O_TARGET;
    }

    public char[][] getField() {
        return field;
    }

    // ** PRIVATE METHODS **
    private void checkField(int i, int j) throws InvalidArgumentException {
        if(field[i][j] != Utils.EMPTY_FIELD) {
            throw new InvalidArgumentException(new String[] {"Field [" + i + "][" + j + "] is not empty. Value: " + field[i][j]});
        }
    }

    public void print() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(field[i][j] == Utils.EMPTY_FIELD ? "  " : field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field1 = (Field) o;

        return Arrays.deepEquals(field, field1.field);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(field);
    }
}
