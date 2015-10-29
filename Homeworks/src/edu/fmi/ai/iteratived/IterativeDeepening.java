package edu.fmi.ai.iteratived;

public class IterativeDeepening {

    private final int FREE_FIELD = 0;
    //private final int TAKEN_FIELD = Integer.MAX_VALUE;
    private final int TAKEN_FIELD = 99; //for better formatting

    private int[][] field;
    private int n;
    private int m;
    private int endX;
    private int endY;

    // n - height m - width
    public IterativeDeepening(int n, int m) {
        field = new int[n][m];
        this.n = n;
        this.m = m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = FREE_FIELD;
            }
        }
    }

    public void setObstacle(int x, int y) {
        if(isValid(x, y)) {
            field[x][y] = TAKEN_FIELD;
        }
    }

    public void findWay(int startX, int startY, int endX, int endY) {
        this.endX = endX;
        this.endY = endY;

        field[startX][startY] = -42;

        int[][] moves = {
                {1, -1, 0, 0},
                {0, 0, -1, 1}
        };

        int steps = 0;

        int nextX = startX;
        int nextY = startY;
        while ((nextX != endX || nextY != endY) && steps <= 10_000) {
            int bestDistance = Integer.MAX_VALUE;
            int bestX = nextX;
            int bestY = nextY;
            for(int i = 0; i < 4; i++) {
                int currentX = nextX + moves[0][i];
                int currentY = nextY + moves[1][i];
                if (isValid(currentX, currentY)) {
                    int dist = manhattanDist(currentX, currentY);
                    if(dist < bestDistance) {
                        bestX = currentX;
                        bestY = currentY;
                        bestDistance = dist;
                    }
                }
            }
            field[bestX][bestY] = manhattanDist(bestX, bestY);
            nextX = bestX;
            nextY = bestY;
        }


        if(steps != 10_000) {
            System.out.println("DONE !");
            field[endX][endY] = -69;
            print();
        }



    }

    private void print() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(String.format("%1$4s", field[i][j]));
            }
            System.out.println();
        }
    }

    private int manhattanDist(int x, int y) {
        return Math.abs(endX - x) + Math.abs(endY - y);
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && field[x][y] != TAKEN_FIELD;
    }

    public static void main(String[] args) {
        IterativeDeepening it = new IterativeDeepening(6, 6);
        it.setObstacle(4, 1);
        it.setObstacle(4, 2);
        it.setObstacle(4, 3);
        it.setObstacle(4, 4);
        it.findWay(5, 2, 0, 5);



        /*
        0 0 0 0 E 0
        0 0 0 0 0 0
        0 0 0 0 0 0
        0 0 0 0 0 0
        0 * * * * 0
        0 0 S 0 0 0
         */
    }
}
