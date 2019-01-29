package sorts;

import model.SortArray;
import model.SortVisualizer;

public class RecursiveMergeSort implements ISort {

    @Override
    public void sort(SortArray array) {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mergeSort(array, 0, array.length() - 1);
        SortVisualizer.SEMAPHORE.release();
    }

    private void mergeSort(SortArray array, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex + 1 < 16) {
            insertionSort(array, leftIndex, rightIndex);
        } else {
            int mid = (leftIndex + rightIndex) / 2;
            mergeSort(array, leftIndex, mid);
            mergeSort(array, mid + 1, rightIndex);
            merge(array, leftIndex, mid, rightIndex);
        }
    }

    private void insertionSort(SortArray array, int l, int r) {
        for (int i = l + 1; i < r + 1; i++) {
            int value = array.get(i);
            int index = i;
            for (int j = i; j > l && array.get(j - 1) > value; j--) {
                array.updateSingleElement(j, array.get(j - 1));
                index = j - 1;
            }
            array.updateSingleElement(index, value);
        }
    }

    private void merge(SortArray array, int leftIndex, int mid, int rightIndex) {
        if (array.get(mid) <= array.get(mid + 1)) return;

        int[] l = array.copy(leftIndex, mid);
        int[] r = array.copy(mid + 1, rightIndex);

        int i = 0;
        int j = 0;
        int tempIndex = leftIndex;
        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                array.updateSingleElement(tempIndex, l[i]);
                ++i;
            } else {
                array.updateSingleElement(tempIndex, r[j]);
                ++j;
            }
            ++tempIndex;
        }

        while (i < l.length) {
            array.updateSingleElement(tempIndex, l[i]);
            ++i;
            ++tempIndex;
        }

        while (j < r.length) {
            array.updateSingleElement(tempIndex, r[j]);
            ++j;
            ++tempIndex;
        }
    }
}
