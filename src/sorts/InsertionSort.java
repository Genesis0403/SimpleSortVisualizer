package sorts;

import model.SortArray;
import model.SortVisualizer;

public class InsertionSort implements ISort {

    @Override
    public void sort(SortArray array) {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < array.length(); i++) {
            for (int j = i; j > 0 && array.get(j) < array.get(j - 1); j--) {
                array.swap(j, j - 1);
            }
        }
        SortVisualizer.SEMAPHORE.release();
    }
}
