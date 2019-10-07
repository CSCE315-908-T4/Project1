package edu.tamu.csce315_908_t4.gui.frontend;

import javax.swing.*;
import java.awt.*;

public class DatabaseGUI extends JFrame {
    private JPanel query;
    private JPanel results;
    private JLabel label;
    private JButton addButton;
    private JButton searchButton;

    private JTextField searchBar;
    private JButton printResults;
    private JRadioButton radioButton;

    public DatabaseGUI() {
        query = new JPanel(new FlowLayout());
        results = new JPanel();
        label = new JLabel("Here is my message");
        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        //search.addActionListener(new SearchListener());
        searchBar = new JTextField(20);
        printResults = new JButton("Print Results");

        // set window title
        setTitle("FILMPEDIA");
        // set window size
        setSize(1800, 800);
        // set action on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel 1: query
        query.add(label);
        query.add(addButton, BorderLayout.WEST);
        query.add(searchButton, BorderLayout.WEST);
        query.add(printResults);

        // panel 2: results
        results.add(searchButton);

        add(query);
        add(results);


        // display window
        setVisible(true);
    }

    public static void main(String[] args) {
        DatabaseGUI window = new DatabaseGUI();
    }
}
