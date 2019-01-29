package sorts;

import model.SortArray;
import model.SortVisualizer;

public class ShellSort implements ISort {

    @Override
    public void sort(SortArray array) {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] steps = { 1, 4, 10, 23, 57, 145, 356, 911, 1968, 4711, 11969, 27901,
                84801, 213331, 543749, 1355339, 3501671, 8810089, 21521774,
                58548857, 157840433, 410151271, 1131376761, 2147483647 };
        int stepIndex = 0;

        while (steps[stepIndex] < array.length()/3) ++stepIndex;
        while (stepIndex >= 0) {
            int step = steps[stepIndex];
            for (int i = step; i < array.length(); i++) {
                for (int j = i; j >= step && array.get(j) < array.get(j - step); j -= step) {
                    array.swap(j, j - step);
                }
            }
            stepIndex--;
        }
        SortVisualizer.SEMAPHORE.release();
    }
}
