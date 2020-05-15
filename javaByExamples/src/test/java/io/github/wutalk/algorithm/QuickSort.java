package io.github.wutalk.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class QuickSort {

    int[] theArray;

    @Test
    public void testSort() {
        Random r = new Random(17);
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        String originStr = "[76, 20, 94, 16, 92, 93, 4, 15, 62, 8]";
        String sortedStr = "[4, 8, 15, 16, 20, 62, 76, 92, 93, 94]";

        assertEquals(originStr, Arrays.toString(a));
        theArray = a;

//        SimpleSortTest.insertionSort(a);
        quickSort();

        assertEquals(sortedStr, Arrays.toString(a));
    }


    public void quickSort() {
        recQuickSort(0, theArray.length - 1);
    }


    public void recQuickSort(int left, int right) {

        if (right - left <= 0) {     // if size is 1,

            return;                  //  it's already sorted

        } else {                     // size is 2 or larger
            long pivot = theArray[right];      // rightmost item
            // partition range

            int partition = partitionIt(left, right, pivot);
            //  theArray[partition] is already at right place

            recQuickSort(left, partition - 1);   // sort left side

            recQuickSort(partition + 1, right);  // sort right side
        }
    }

    public int partitionIt(int left, int right, long pivot) {

        int leftPtr = left - 1;           // left    (after ++)

        int rightPtr = right;           // right-1 (after --)

        while (true) {                            // find bigger item

            while (theArray[++leftPtr] < pivot)

                ;  // (nop)

            // find smaller item
            while (rightPtr > 0 && theArray[--rightPtr] > pivot)

                ;  // (nop)


            if (leftPtr >= rightPtr)      // if pointers cross,

                break;                    //    partition done

            else                         // not crossed, so

                swap(leftPtr, rightPtr);  //    swap elements

        }  // end while(true)
        swap(leftPtr, right);           // restore pivot

        return leftPtr;                 // return pivot location

    }  // end partitionIt()


    public void swap(int dex1, int dex2)  // swap two elements
    {

        int temp = theArray[dex1];        // A into temp

        theArray[dex1] = theArray[dex2];   // B into A

        theArray[dex2] = temp;             // temp into B

    }  // end swap
}
