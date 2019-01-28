package sorts;

import model.SortArray;
import model.SortVisualizer;

public class SelectionSort implements ISort {

    @Override
    public void sort(SortArray array) {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < array.length(); i++) {
            int min = i;
            for (int j = i + 1; j < array.length(); j++) {
                if (array.get(j) < array.get(min)) {
                    min = j;
                }
                array.updateSingleElement(j, array.get(j));
            }
            array.swap(min, i);
        }

        SortVisualizer.SEMAPHORE.release();
    }
}
