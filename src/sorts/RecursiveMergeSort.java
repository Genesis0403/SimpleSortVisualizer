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

    private void merge(SortArray array, int leftIndex, int mid, int rightIndex) {
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

    private void mergeSort(SortArray array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            mergeSort(array, leftIndex, mid);
            mergeSort(array, mid + 1, rightIndex);
            if (array.get(mid) > array.get(mid + 1)) {
                merge(array, leftIndex, mid, rightIndex);
            }
        }
    }
}
