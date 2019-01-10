package model;

import sorts.BubbleSort;
import sorts.ISort;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SortVisualiser {

    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;
    public final static int BARS_LENGTH = 5;
    public final static int BARS = WIDTH / BARS_LENGTH;

    private final static SortArray array = new SortArray();
    private final static List<ISort> sorts = new ArrayList<>();
    {
        sorts.add(new BubbleSort());
    }

    private static void initComponents() {
        JFrame frame = new JFrame("Sorts Visualiser");
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Sorts");
        menu.add("Bubble sort");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(array);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SortVisualiser().initComponents();
    }
}
