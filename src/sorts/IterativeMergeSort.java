package sorts;

import model.SortArray;
import model.SortVisualizer;

public class IterativeMergeSort implements ISort {

    @Override
    public void sort(SortArray array) {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int sz = 1; sz < array.length(); sz = sz + sz) {
            for (int lo = 0; lo < array.length() - sz; lo += sz + sz) {
                merge(array, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, array.length() - 1));
            }
        }
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
}
