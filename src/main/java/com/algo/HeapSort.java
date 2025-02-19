package com.algo;

public class HeapSort {
    private HeapSort(){}
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }

        for (int j = n - 1; j >= 0; j--) {
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            heapify(arr, 0, j);
        }
    }

    public static void heapify(int[] arr, int i, int n) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int parent = i;

        if (left < n && arr[left] > arr[parent]) {
            parent = left;
        }
        if (right < n && arr[right] > arr[parent]) {
            parent = right;
        }

        if (i != parent) {
            int temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;

            heapify(arr, parent, n);
        }
    }
}
