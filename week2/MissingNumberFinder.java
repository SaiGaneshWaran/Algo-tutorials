package com.algo.week2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class MissingNumberFinder {

    // Function to generate an array with random nonnegative integers
    public static int[] createInput(int size) {
        Random rand = new Random();
        HashSet<Integer> set = new HashSet<>();

        while (set.size() < size) {
            set.add(rand.nextInt(size * 2)); // Ensure missing numbers exist
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // Unsorted Approach - Using Boolean Array (O(n))
    public static int findMissingUnsorted(int[] arr) {
        int maxVal = Arrays.stream(arr).max().orElse(0);
        boolean[] present = new boolean[maxVal + 1];

        for (int num : arr) {
            if (num < present.length) {
                present[num] = true;
            }
        }

        for (int i = 0; i < present.length; i++) {
            if (!present[i]) {
                return i;
            }
        }

        return maxVal + 1;
    }

    // Sorted Approach - Using Linear Search (O(n))
    public static int findMissingSorted(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                return i;
            }
        }
        return arr.length;
    }

    // Measure execution time
    public static void measureTime(int[] arr, boolean sorted) {
        long startTime, endTime;
        int missingNumber;

        if (sorted) {
            Arrays.sort(arr); // Sorting (not included in measurement)
            startTime = System.nanoTime();
            missingNumber = findMissingSorted(arr);
            endTime = System.nanoTime();
            System.out.println("Sorted: Missing = " + missingNumber + ", Time = " + (endTime - startTime) / 1e6 + " ms");
        } else {
            startTime = System.nanoTime();
            missingNumber = findMissingUnsorted(arr);
            endTime = System.nanoTime();
            System.out.println("Unsorted: Missing = " + missingNumber + ", Time = " + (endTime - startTime) / 1e6 + " ms");
        }
    }

    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 40000, 80000, 160000};

        System.out.println("Unsorted Approach Timing:");
        for (int size : sizes) {
            int[] arr = createInput(size);
            measureTime(arr, false);
        }

        System.out.println("\nSorted Approach Timing:");
        for (int size : sizes) {
            int[] arr = createInput(size);
            measureTime(arr, true);
        }
    }
}

