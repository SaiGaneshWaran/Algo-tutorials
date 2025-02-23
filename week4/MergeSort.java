package com.algo.week4;

import java.util.Random;

/**
 *
 * @author klaus
 */
public class MergeSort {

    // Merge (sorted) ranges values[first]...values[mid] and values[mid+1]...values[last]
    private static void mergeRanges(int[] values, int firstIndex, int midIndex, int lastIndex){
        // TO DO: implement the merge function
        int size=lastIndex+1-firstIndex;
        int []temp=new int[size];
        int index1=firstIndex;
        int index2=midIndex+1;
        int tempIndex=0;

        while(index1<=midIndex && index2<=lastIndex){
            if(values[index1]<values[index2]){
                temp[tempIndex]=values[index1];
                index1++;
            }
            else{
                temp[tempIndex]=values[index2];
                index2++;
            }
            tempIndex++;
        }

        while(index1<=midIndex){
            temp[tempIndex]=values[index1];
            index1++;
            tempIndex++;
        }
        while(index2<=lastIndex){
            temp[tempIndex]=values[index2];
            index2++;
            tempIndex++;

        }

        for (int i=0;i<size;i++){
            values[firstIndex+i]=temp[i];
        }

        //HAVE TO CODE TO ADD THE REMAINING PARTS
    }


    // Auxiliary recursive function
    // Sorts the range values[first]...values[last]
    private static void sortRange(int[] values, int first, int last){
        if(last > first){    // Otherwise there is nothing to do (single value)
            int mid = (first + last) / 2;
            sortRange(values, first, mid);      // Recursively sort first half
            sortRange(values, mid + 1, last);   // Recursively sort second half
            mergeRanges(values, first, mid, last); // Merge sorted halves
        }
    }

    public static void sort(int[] values){
        sortRange(values, 0, values.length - 1);
    }

    public static int[] randomValues(int howMany){
        int[] result = new int[howMany];
        Random random = new Random();
        for(int i=0; i<howMany; i++)
            result[i] = random.nextInt() % (10 * howMany);
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // How many values to generate
        int numValues = 5;
        // Whether to print results. Only use with small numbers of values.
        boolean printResults = true;


        int[] a = randomValues(numValues);
        if(printResults){
            System.out.print("Before sorting: ");
            for(int i=0;i<numValues; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
        long start = System.currentTimeMillis();
        sort(a);
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        if(printResults){
            System.out.print("After sorting: ");
            for(int i=0;i<numValues; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
        System.out.println();
        System.out.println("Elapsed time = " + elapsed + " seconds");
    }
}
