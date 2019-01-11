package model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortArray extends JPanel {

    private long SLEEP_TIME = 5;

    public long getSleep() {
        return SLEEP_TIME;
    }

    private int[] array = new int[SortVisualizer.BARS];
    private boolean[] color = new boolean[SortVisualizer.BARS];

    public SortArray() {
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        fillArray();
    }

    private void fillArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    public void shuffleElements() {
        try {
            SortVisualizer.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        for (int i = array.length - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
            repaint();
            color[i] = true;
            repaint();
            sleep(5);
        }
        SortVisualizer.SEMAPHORE.release();
    }

    public void swap(int firstIndex, int secondIndex) {
        if (firstIndex < 0
            || secondIndex < 0
            || firstIndex >= array.length
            || secondIndex >= array.length) {
            throw new IllegalArgumentException("Illegal indexes: firstIndex=" + firstIndex +
                                               " secondIndex=" + secondIndex);
        }

        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        color[secondIndex] = true;
        color[firstIndex] = true;
        repaint();
        sleep(SLEEP_TIME);
    }

    private void sleep(long ms) {
        long nanos = ms * (long)1e6;
        long startTime = System.nanoTime();
        long elapsedTime;
        do {
            elapsedTime = System.nanoTime() - startTime;
        } while (elapsedTime < nanos);
    }

    public int length() {
        return array.length;
    }

    public int get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("Illegal index=" + index + " array's length=" + array.length);
        }
        return array[index];
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SortVisualizer.WIDTH, SortVisualizer.HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for (int x = 0; x < SortVisualizer.BARS; x++) {
            g2.setColor(Color.WHITE);
            int begX = x + (SortVisualizer.BARS_LENGTH - 1) * x;
            int begY = SortVisualizer.HEIGHT - array[x] - 24;

            if (color[x]) {
                g2.setColor(Color.RED);
                color[x] = false;
            }

            g2.fillRect(begX, begY, SortVisualizer.BARS_LENGTH, array[x]);
        }
    }

    public void setSleepTime(long sleepTime) {
        if (sleepTime < 1 || sleepTime > 100) {
            throw new IllegalArgumentException("Illegal amount of milliseconds=" + sleepTime);
        }
        SLEEP_TIME = sleepTime;
    }
}
