package edu.fmi.ai.queens;

/**
 * Created by vasil on 11/15/15.
 */
public class QueensTest {
    volatile int failedTests = 0;
    int maxTest;
    int minTest;

    public QueensTest(int minTest, int maxTest) {
        this.maxTest = maxTest;
        this.minTest = minTest;
    }

    public void test() throws InterruptedException {
        Thread[] threads = new Thread[maxTest + 1];
        for(int i = minTest; i <= maxTest; i++) {
            int tmp = i;
            Thread r = new Thread() {
                @Override
                public void run() {
                    Queens q = new Queens(tmp);
                    boolean findAnswer = q.findSolution(15);
                    if(!findAnswer) {
                        System.out.println("TEST WITH " + tmp + " QUEENS FAILED!!!");
                        failedTests++;
                    }
                }
            };
            threads[i] = r;
            r.run();
        }

        for(int i = minTest; i <= maxTest; i++) {
            threads[i].join();
        }
        System.out.println("-------------------------------");
        System.out.println("TOTAL FAILED TESTS " + failedTests + " OUT OF " + (maxTest - minTest + 1));
    }

    public static void main(String[] args) throws InterruptedException {
        new QueensTest(15, 26).test();
    }

}
