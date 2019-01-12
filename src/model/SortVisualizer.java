package model;

import sorts.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class SortVisualizer extends JFrame{

    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;
    public final static int BARS_LENGTH = 5;
    public final static int BARS = WIDTH / BARS_LENGTH;

    public final static Semaphore SEMAPHORE = new Semaphore(1);
    private final static SortArray array = new SortArray();
    private final static Map<String, ISort> sorts = new HashMap<>();
    {
        sorts.put("BubbleSort", new BubbleSort());
        sorts.put("InsertionSort", new InsertionSort());
        sorts.put("QuickSort", new QuickSort());
        sorts.put("MergeSort", new MergeSort());
    }

    private JMenuBar menuBar = new JMenuBar();
    private JMenu sortMenu = new JMenu("Sorts");

    private JMenuItem bubbleSort = new JMenuItem("Bubble sort");
    private JMenuItem insertionSort = new JMenuItem("Insertion sort");
    private JMenuItem quickSort = new JMenuItem("Quick sort");
    private JMenuItem mergeSort = new JMenuItem("Merge Sort");

    private JMenu sleepTimeMenu = new JMenu("Sleep time");
    private ButtonGroup sleepTimeGroup = new ButtonGroup();

    public SortVisualizer(String title) {
        super(title);
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        sortMenu.add(bubbleSort);
        bubbleSort.addActionListener(e ->
                new Thread(() -> {
                    array.shuffleElements();
                    sorts.get("BubbleSort").sort(array);
        }).start());

        sortMenu.add(insertionSort);
        insertionSort.addActionListener(e ->
                new Thread(() -> {
                    array.shuffleElements();
                    sorts.get("InsertionSort").sort(array);
        }).start());

        sortMenu.add(quickSort);
        quickSort.addActionListener(e ->
                new Thread(() -> {
                    array.shuffleElements();
                    sorts.get("QuickSort").sort(array);
        }).start());

        sortMenu.add(mergeSort);
        mergeSort.addActionListener(e ->
                new Thread(() -> {
                    array.shuffleElements();
                    sorts.get("MergeSort").sort(array);
        }).start());


        String time = "ms";
        JRadioButton sleepTime3 = new JRadioButton("3 " + time);
        sleepTime3.addActionListener(e -> array.setSleepTime(3));
        sleepTimeGroup.add(sleepTime3);
        sleepTimeMenu.add(sleepTime3);

        JRadioButton sleepTime5 = new JRadioButton("5 " + time);
        sleepTime5.addActionListener(e -> array.setSleepTime(5));
        sleepTimeGroup.add(sleepTime5);
        sleepTimeMenu.add(sleepTime5);

        JRadioButton sleepTime10 = new JRadioButton("10 " + time);
        sleepTime10.addActionListener(e -> array.setSleepTime(10));
        sleepTimeGroup.add(sleepTime10);
        sleepTimeMenu.add(sleepTime10);

        JRadioButton sleepTime15 = new JRadioButton("15 " + time);
        sleepTimeGroup.add(sleepTime15);
        sleepTime15.addActionListener(e -> array.setSleepTime(15));
        sleepTimeMenu.add(sleepTime15);

        JRadioButton sleepTime50 = new JRadioButton("50 " + time);
        sleepTimeGroup.add(sleepTime50);
        sleepTime50.addActionListener(e -> array.setSleepTime(50));
        sleepTimeMenu.add(sleepTime50);

        JRadioButton sleepTime70 = new JRadioButton("70 " + time);
        sleepTimeGroup.add(sleepTime70);
        sleepTime70.addActionListener(e -> array.setSleepTime(70));
        sleepTimeMenu.add(sleepTime70);

        menuBar.add(sortMenu);
        menuBar.add(sleepTimeMenu);
        setJMenuBar(menuBar);

        getContentPane().add(array);

        pack();
        setVisible(true);
    }
}
