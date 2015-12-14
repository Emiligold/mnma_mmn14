package com.student;

import java.util.Random;

/**
 * Created by Yoav on 12/12/2015.
 */
public class TestDHeap {

    // Minimum and Maximum number range
    private final int MIN_VALUE = 0;
    private final int MAX_VALUE = 1023;

    // Our 3 case arrays with random values
    private final Integer[] a = generateRandomArray(50, MIN_VALUE, MAX_VALUE);
    private final Integer[] b = generateRandomArray(100, MIN_VALUE, MAX_VALUE);
    private final Integer[] c = generateRandomArray(200, MIN_VALUE, MAX_VALUE);

    // Minimum and Maximum d size with default values
    private int mMinD = 2;
    private int mMaxD = 2;

    // Default constructor
    public TestDHeap() {
    }

    /**
     * Constructor with params
     * @param minD
     * @param maxD
     */
    public TestDHeap(int minD, int maxD) {
        mMinD = minD;
        mMaxD = maxD;
    }

    /**
     * Run DHeap tests
     */
    public void runTests(){

        // Print the arrays
        printArray("A - size " + a.length, a);
        printArray("B - size " + b.length, b);
        printArray("C - size " + c.length, c);

        // Execute test for all d values based on mMinD and mMaxD
        for (int d = mMinD; d <= mMaxD; d++) {
            // Preserve the original random arrays by sending copies of them
            runTestforD(d, a.clone(), b.clone(), c.clone());
        }
    }

    /**
     * Run HeapSort on each of the arrays received with the d value received
     *
     * @param d - Maximum child nodes for each node in the heap
     * @param arrays - Array of arrays to call heapsort on
     */
    private void runTestforD(int d, Integer[]...arrays)
    {
        // Print the header
        System.out.println("For d value = " + d);

        // Iterate over each array given as a parameter, call heapsort and print results
        for (int i=0; i < arrays.length; i++)
        {
            // Create a new DHeap object for given d and current array
            DHeap dHeap = new DHeap(arrays[i], d);
            dHeap.heapSort();

            System.out.println("Comparisons for size " + arrays[i].length + " : " + dHeap.getComparisonCount());
            System.out.println("Exchanges for size " + arrays[i].length + " : " + dHeap.getExchangeCount());
//            printArray("sorted", arrays[i]);
        }
    }

    /**
     * Print a Integer array with its name given in parameter
     *
     * @param arrayName - Label to print before array content
     * @param arr - the array to print
     */
    private void printArray(String arrayName, Integer[] arr)
    {
        // Make sure array is initiated
        if (arr != null)
        {
            // Print the array's name
            System.out.print(arrayName + ":[");

            // Print all the cells seperated by commas. Last cell is not printed because we don't need a comma after it.
            for(int i=0; i < arr.length - 1; i++)
            {
                System.out.print(arr[i] + ",");
            }

            // Print last cell
            System.out.println(arr[arr.length - 1] + "]");
        }
    }

    /**
     * Create a new array with random Integer values between min parameter and max parameter
     * @param size - The size of our new array
     * @param min - The minimum value
     * @param max - The maximum value
     * @return - Returns the array that was created, filled with random values between min and max
     */
    private Integer[] generateRandomArray(int size, int min, int max) {
        Integer[] array = new Integer[size];

        Random generator = new Random();

        // Generate random values via our generator and place in the new array
        for (int i = 0; i < size; i++) {
            array[i] = generator.nextInt(max + 1 - min) + min;
        }

        return array;
    }
}
