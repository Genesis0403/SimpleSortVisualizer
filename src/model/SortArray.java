package model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortArray extends JPanel {

    private int[] array = new int[SortVisualiser.BARS];
    private boolean[] color = new boolean[SortVisualiser.BARS];

    public SortArray() {
        fillArray();
        shuffleElements();
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
    }

    public void updateArray() {
        shuffleElements();
    }

    private void fillArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    private void shuffleElements() {
        Random random = new Random();
        for (int i = array.length - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
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
        repaint();
        sleep(5);
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
            throw new IllegalArgumentException("Illegal index" + index + " array's length=" + array.length);
        }
        return array[index];
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SortVisualiser.WIDTH, SortVisualiser.HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for (int x = 0; x < SortVisualiser.BARS; x++) {
            g2.setColor(Color.WHITE);
            int begX = x + (SortVisualiser.BARS_LENGTH - 1) * x;
            int begY = SortVisualiser.HEIGHT - array[x] - 24;

            if (color[x]) {
                g2.setColor(Color.RED);
                color[x] = false;
            }

            g2.fillRect(begX, begY, SortVisualiser.BARS_LENGTH, array[x]);
        }
    }
}
