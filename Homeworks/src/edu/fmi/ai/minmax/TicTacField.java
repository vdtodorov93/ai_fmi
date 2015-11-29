package edu.fmi.ai.minmax;

public class TicTacField {
    static final char CHAR_X = 'X';
    static final char CHAR_O = 'O';
    static final Integer INTEGER_X = 1;
    static final Integer INTEGER_Y = -1;
    private char[][] field;

    public TicTacField() {
        this.field = new char[3][3];
    }

    public TicTacField(TicTacField tictact) {
        this();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.field[i][j] = field[i][j];
            }
        }
    }

    public Integer checkWinner() {
        for(int i = 0; i < 3; i++) {
            if(field[i][0] == field[i][1] && field[i][0] == field[i][2]) { // ROW
                return winerFromChar(field[i][0]);
            }
            if(field[0][i] == field[1][i] && field[0][i] == field[2][i]) { // COL
                return winerFromChar(field[0][i]);
            }
        }

        if(field[0][0] == field[1][1] && field[0][0]== field[2][2]) { // MAIN DIAGONAL
            return winerFromChar(field[0][0]);
        }
        if(field[2][0] == field[1][1] && field[2][0]== field[0][2]) { // SECOND DIAGONAL
            return winerFromChar(field[2][0]);
        }

        return 0; //DRAW
    }

    public void set(int x, int y, char c) {
        field[x][y] = c;
    }

    private Integer winerFromChar(char winner) {
        return winner == CHAR_X ? INTEGER_X : INTEGER_Y;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                b.append(field[i][j] + " ");
            }
            b.append(System.getProperty("line.separator"));
        }
        return b.toString();
    }
}
