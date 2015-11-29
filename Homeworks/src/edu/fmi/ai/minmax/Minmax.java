package edu.fmi.ai.minmax;

public class Minmax {

    private TicTacField gameField;

    public Minmax(TicTacField gameField) {
        this.gameField = gameField;
    }

    public TicTacField findBest() {

    }











    public static void main(String[] args) {
        TicTacField t = new TicTacField();
        TicTacField t2 = new TicTacField(t);
        t.set(2, 2, 'X');
        System.out.println(t);
        System.out.println(t2);
    }

}
