package sorts;

import model.SortArray;

public class BubbleSort implements ISort {

    @Override
    public void sort(SortArray array) {
        for (int i = 0; i < array.length() - 1; i++) {
            for (int j = 0; j < array.length() - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    array.swap(j, j + 1);
                }
            }
        }
    }
}
