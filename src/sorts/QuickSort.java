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

        qSort(array, 0, array.length() - 1);

        SortVisualizer.SEMAPHORE.release();
    }

    private void qSort(SortArray array, int lo, int hi) {
        if (hi <= lo) return;
        int mid = partition(array, lo, hi);
        qSort(array, lo, mid - 1);
        qSort(array, mid + 1, hi);
    }

    private int partition(SortArray array, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int mid = array.get(lo);
        while (true) {
            while (array.get(++i) <= mid) if (i >= hi) break;
            while (array.get(--j) > mid); //  if (j <= lo) break

            if (i >= j) break;
            array.swap(i, j);
        }
        array.swap(lo, j);
        return j;
    }
}
