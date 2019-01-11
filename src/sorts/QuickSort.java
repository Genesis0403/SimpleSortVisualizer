package sorts;

import model.SortArray;
import model.SortVisualizer;

public class QuickSort implements ISort {

    @Override
    public void sort(SortArray array) {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        quickSort(array, 0, array.length() - 1);

        SortVisualizer.SEMAPHORE.release();
    }

    private void quickSort(SortArray array, int begIndex, int endIndex) {
        int left = begIndex;
        int right = endIndex;
        int vel = array.get((left + right) / 2);
        while (left <= right) {
            while (array.get(left) < vel) {
                left++;
            }
            while (array.get(right) > vel) {
                right--;
            }
            if (left <= right) {
                array.swap(left, right);
                left++;
                right--;
            }
        }

        if (left < endIndex) {
            quickSort(array, left, endIndex);
        }
        if (right > begIndex) {
            quickSort(array, begIndex, right);
        }
    }
}
