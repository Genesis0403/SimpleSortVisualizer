package sorts;

import model.SortArray;
import model.SortVisualizer;

import java.util.Arrays;

public class InsertionSort implements ISort {

    @Override
    public void sort(SortArray array) {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < array.length(); i++) {
            int value = array.get(i);
            int index = i;
            for (int j = i; j > 0 && value < array.get(j - 1); j--) {
                array.updateSingleElement(j, array.get(j - 1));
                index = j - 1;
            }
            array.updateSingleElement(index, value);
        }

        SortVisualizer.SEMAPHORE.release();
    }
}
