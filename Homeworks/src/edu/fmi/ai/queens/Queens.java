package edu.fmi.ai.queens;

/**
 * Created by vasil on 11/15/15.
 */
public class Queens {
    private int dim;
    private int[] positions;

    public Queens(int dim) {
        this.dim = dim;
        this.positions = new int[dim];
    }

    public boolean findSolution(long maxSeconds) {
        long start = System.currentTimeMillis();
        int conflicts = countConflicts();
        while(conflicts > 0) {
            boolean changed = false;

            for(int i = 0; i < dim; i++) { //iterating queens
                int currentQueenPosition = positions[i];
                int currentQueenConflicts = countConflicts(); //TODO: remove me if not needed
                //int lowestConflictsCount = currentQueenConflicts;
                for(int j = 0; j < dim; j++) {
                    positions[i] = j;
                    int newConflictCount = countConflicts();
                    if(newConflictCount <= currentQueenConflicts) {
                        currentQueenConflicts = newConflictCount;
                        currentQueenPosition = j;
                        changed = true;
                    }
                }

                positions[i] = currentQueenPosition;
                conflicts = currentQueenConflicts;
                if(conflicts == 0) break;
            }
            if(System.currentTimeMillis() - 1000 * maxSeconds > start) {
                return false;
            }
        }

        return true;
    }

    private int countConflicts() {
        int conflicts = 0;
        for(int i = 0; i < dim - 1; i++) {
            for(int j = i + 1; j < dim; j++) {
                boolean onSameRow = positions[i] == positions[j];
                boolean onSameDiagonal = Math.abs(i - j) == Math.abs(positions[i] - positions[j]);
                if(onSameRow || onSameDiagonal) {
                    conflicts++;
                }
            }
        }

        return conflicts;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = dim - 1; i >= 0; i--) {
            for(int j = 0; j < dim; j++) {
                if(i == positions[j]) {
                    result.append("Q ");
                } else {
                    result.append("_ ");
                }
            }
            result.append(System.getProperty("line.separator"));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Queens q = new Queens(9);
//        System.out.println(q.countConflicts());
//        System.out.println(q);
        q.findSolution(5);
        System.out.println(q);
    }
}
