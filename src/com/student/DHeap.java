package com.student;

/**
 * Created by Yoav on 12/12/2015.
 */
public class DHeap {

    // Number of children for each node
    private int d;

    // Current heap size
    private int heapSize;

    // Array to process
    private Integer[] A;

    // Counters for numbers of exchange and assignments
    private int exchangeCount = 0;
    private int ComparisonCount = 0;

    /**
     * Constructor
     * @param pA - The array to sort
     * @param pD - Number of children for each node
     */
    public DHeap(Integer[] pA, int pD)
    {
        this.A = pA;
        heapSize = A.length;
        d = pD;
    }

    /**
     * Build the max heap 
     */
    public void buildMaxHeap()
    {
        // Initialize the size of the heap to array's length
        heapSize = A.length;

        // Max Heapify all parent nodes
        for (int i = (A.length / d); i >= 1; i--)
        {
            maxHeapify(A, i);
        }
    }

    /**
     * 
     * @param currArray - Array
     * @param index - Index of the node to max heapify
     */
    public void maxHeapify(Integer[] currArray, Integer index)
    {
        // Initialize largest value to the index given
        Integer largest = index;

        // Iterate over all the children
        for (int parentChildIndex = 1; parentChildIndex <= d; parentChildIndex++)
        {
            // Get child index
            Integer childIndex = getChild(index, parentChildIndex);

            // Increase number of comparison
            ComparisonCount++;

            // Make sure child index is legal (Within our heapsize) and compare values between parent and child
            if ((childIndex != null) && (currArray[childIndex - 1] > currArray[largest - 1]))
            {
                largest = childIndex;
            }
        }

        // Check if we need to exchange values
        if (largest != index)
        {
            // Excahnge and run maxHepify on child
            exchange(currArray, index, largest);
            maxHeapify(currArray, largest);
        }
    }

    /**
     * Exchange values of two cells in the array
     * @param A - The array
     * @param i - First cell index
     * @param j - Second cell index
     */
    public void exchange(Integer[] A, int i, int j)
    {
        // Add to counter
        exchangeCount += 3;

        // Exchange
        Integer temp = A[i - 1];
        A[i - 1] = A[j - 1];
        A[j - 1] = temp;
    }

    /**
     * Heap sort
     */
    public void heapSort()
    {
        // Reset counters
        resetExchangeCount();
        resetComparisonCount();

        buildMaxHeap();

        for (int i = A.length; i > 1; i--)
        {
            exchange(A, 1, i);
            heapSize--;
            maxHeapify(A, 1);
        }
    }

    /**
     * Get absolut index of a relative child
     * @param parentIndex
     * @param parentChildIndex
     * @return The absolut index
     */
    private Integer getChild(int parentIndex, int parentChildIndex) {
        int childIndex = (d * (parentIndex - 1)) + parentChildIndex + 1;

        return childIndex > heapSize ? null : childIndex;
    }

    /**
     * reset Exchange counter
     */
    private void resetExchangeCount()
    {
        exchangeCount = 0;
    }

    /**
     * reset Comparisons counter
     */
    private void resetComparisonCount() {
        ComparisonCount = 0;
    }

    /**
     * Getter for Exchange count
     * @return exchangeCount value
     */
    public int getExchangeCount()
    {
        return exchangeCount;
    }

    /**
     * Getter for comparison count
     * @return ComparisonCount value
     */
    public int getComparisonCount() {
        return ComparisonCount;
    }
}
