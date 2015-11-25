package edu.fmi.ai;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by vasil on 11/14/15.
 */
public class Main {
    public static void main(String[] args) {
        int[] asd = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int mid = asd.length / 2;
        System.out.println(mid);
        int[] asd1 = Arrays.copyOfRange(asd, 0, mid);
        int[] asd2 = Arrays.copyOfRange(asd, mid, asd.length - 1);
        for(int a: asd1) {
            System.out.print(a + " ");
        }
        System.out.println();

        for(int a: asd2) {
            System.out.print(a + " ");
        }
    }
}
