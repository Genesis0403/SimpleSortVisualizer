package sorts;

import model.SortArray;

public class QuickSort3Way implements ISort {

    @Override
    public void sort(SortArray array) {
        array.shuffleElements();
        qSort(array, 0, array.length() - 1);
    }

    private void qSort(SortArray array, int left, int right) {
        if (right <= left) return;
        int lowerIndex = left;
        int greaterIndex = right;
        int i = left + 1;
        int value = array.get(left);
        while (i <= greaterIndex) {
            if (array.get(i) > value) array.swap(greaterIndex--, i);
            else if (array.get(i) < value) array.swap(lowerIndex++, i++);
            else i++;
        }
        qSort(array, left, lowerIndex - 1);
        qSort(array, greaterIndex + 1, right);
    }
}
