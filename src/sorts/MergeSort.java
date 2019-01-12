package sorts;

import model.SortArray;

public class MergeSort implements ISort {
    @Override
    public void sort(SortArray array) {
        mergeSort(array, 0, array.length() - 1);
        for (int i = 0; i < array.length(); i++) {
            System.out.print(array.get(i) + " ");
        }
    }

    private void mergeSort(SortArray array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            mergeSort(array, leftIndex, mid);
            mergeSort(array, mid + 1, rightIndex);

            int leftSize = mid - leftIndex + 1;
            int rightSize = rightIndex - mid;
            int[] l = new int[leftSize];
            int[] r = new int[rightSize];

            for (int i = 0; i < leftSize; i++) {
                l[i] = array.get(leftIndex + i);
            }
            for (int i = 0; i < rightSize; i++) {
                r[i] = array.get(mid + 1 + i);
            }

            int i = 0;
            int j = 0;
            int tempIndex = leftIndex;
            while (i < leftSize && j < rightSize) {
                if (l[i] <= r[j]) {
                    array.update(tempIndex, l[i]);
                    i++;
                } else {
                    array.update(tempIndex, r[j]);
                    j++;
                }
                tempIndex++;
            }

            while (i < leftSize) {
                array.update(tempIndex, l[i]);
                i++;
                tempIndex++;
            }

            while (j < rightSize) {
                array.update(tempIndex, r[j]);
                j++;
                tempIndex++;
            }
        }

    }
}
