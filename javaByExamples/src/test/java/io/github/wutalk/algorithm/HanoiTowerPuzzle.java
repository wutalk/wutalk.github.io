package io.github.wutalk.algorithm;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HanoiTowerPuzzle {

    @Test
    public void testCalc() {
        int nDisks = 3;
        doTowers(nDisks, 'A', 'B', 'C');
        // assertEquals(30, parseAndCalc("20+2*((30-40/2)-5)"));
    }

    public static void main(String[] args) {
        int nDisks = 3;
        doTowers(nDisks, 'A', 'B', 'C');
    }

    private static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.printf("Disk %d from %c to %c\n", topN, from, to); // from -> to
        } else {
            doTowers(topN - 1, from, to, inter); // from -> inter
            System.out.printf("Disk %d from %c to %c\n", topN, from, to);
            doTowers(topN - 1, inter, from, to); // inter -> to
        }
    }
}