package edu.fmi.ai.queens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vasil on 11/15/15.
 */
public class Queens {
    private int dim;
    private int[] positions;
    private int[] conflicts;

    public Queens(int dim) {
        this.dim = dim;
        this.positions = new int[dim];
        this.conflicts = new int[dim];
    }

//    public boolean findSolution(long maxSeconds) {
//        long start = System.currentTimeMillis();
//        int conflicts = countConflicts();
//        boolean useHack = false;
//        while(conflicts > 0) {
//            boolean changed = false;
//
//            for(int i = 0; i < dim; i++) { //iterating queens
//                int currentQueenPosition = positions[i];
//                int currentQueenConflicts = countConflicts();
//                //int lowestConflictsCount = currentQueenConflicts;
//                for(int j = 0; j < dim; j++) {
//                    positions[i] = j;
//                    int newConflictCount = countConflicts();
//                    if(newConflictCount < currentQueenConflicts) {
//                        currentQueenConflicts = newConflictCount;
//                        currentQueenPosition = j;
//                        changed = true;
//                    }
//                }
//
//                positions[i] = currentQueenPosition;
//                conflicts = currentQueenConflicts;
//                if(conflicts == 0) break;
//            }
//
//            if(changed) {
//               useHack = false;
//            } else {
//                useHack = true;
//            }
//
//            if(System.currentTimeMillis() - 1000 * maxSeconds > start) {
//                return false;
//            }
//        }
//
//        return true;
//    }



    public boolean findSolution(long seconds) {
        long startTime = System.currentTimeMillis();
        int conflictsCount = countConflicts();
        Random rand = new Random();
        boolean moved = false;
        boolean useRandomization = false;
        while(conflictsCount > 0) {
            for(int i = 0; i < dim; i++) {
                int currentConflicts = countConflictsForQueen(i);
                if(currentConflicts > 0) {
                    int currentPosition = positions[i];
                    int bestConflict = currentConflicts;
                    int bestPosition = currentPosition;
                    List<Integer> equals = new ArrayList<>();
                    for(int j = 0; j < dim; j++) {
                        positions[i] = j;
                        int conf = countConflictsForQueen(i);
                        if(conf < bestConflict) {
                            bestConflict = conf;
                            bestPosition = j;
                            moved = true;
                        } else if(conf == bestConflict) {
                            equals.add(j);
                        }
                    }
                    if(currentPosition != bestPosition) {
                        positions[i] = bestPosition;
                        conflictsCount = countConflicts();
                    } else if(useRandomization && equals.size() > 0) {
                        int random = rand.nextInt(equals.size());
                        positions[i] = equals.get(random);
                        conflictsCount = countConflicts();
                    }
                }
            }
            if(!moved) {
                useRandomization = false;
            } else {
                useRandomization = true;
            }

            if(System.currentTimeMillis() - 1000 * seconds > startTime) {
                System.out.println(this);
                System.out.println("CONFLICTS: " + countConflicts());
                return false;
            }
        }

        return true;

    }

    private int countConflictsForQueen(int queen) {
        conflicts[queen] = 0;
        for(int i = 0; i < dim; i++) {
            if(i == queen) {
                continue;
            }
            boolean onSameRow = positions[i] == positions[queen];
            boolean onSameDiagonal = Math.abs(i - queen) == Math.abs(positions[i] - positions[queen]);
            if(onSameRow || onSameDiagonal) {
                conflicts[queen]++;
            }
        }
        return conflicts[queen];
    }

    private int countConflicts() {
        int conflictsCount = 0;
        for(int i = 0; i < dim; i++) {
            conflicts[i] = 0;
        }

        for(int i = 0; i < dim - 1; i++) {
            for(int j = i + 1; j < dim; j++) {
                boolean onSameRow = positions[i] == positions[j];
                boolean onSameDiagonal = Math.abs(i - j) == Math.abs(positions[i] - positions[j]);
                if(onSameRow || onSameDiagonal) {
                    conflictsCount++;
                    conflicts[i]++;
                    conflicts[j]++;
                }
            }
        }

        return conflictsCount;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = dim - 1; i >= 0; i--) {
            result.append((i + 1) % 10 + " ");
            for(int j = 0; j < dim; j++) {
                if(i == positions[j]) {
                    result.append("Q ");
                } else {
                    result.append("_ ");
                }
            }
            result.append(System.getProperty("line.separator"));
        }
        result.append("  ");
        for(int i = 1; i <= dim; i++) {
            result.append(i % 10 + " ");
        }
        result.append(System.getProperty("line.separator"));

        return result.toString();
    }

    public static void main(String[] args) {
        Queens q = new Queens(10);
        boolean result = q.findSolution(5);

        System.out.println(q);
        if(!result) {
            System.out.println("FAIL");
        } else {
            System.out.println("SUCCESS");
        }
    }
}
