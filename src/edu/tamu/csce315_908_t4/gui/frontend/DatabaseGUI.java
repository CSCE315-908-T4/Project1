package edu.tamu.csce315_908_t4.gui.frontend;

import javax.swing.*;
import java.awt.*;

public class DatabaseGUI extends JFrame {
    private JPanel query;
    private JPanel results;
    private JLabel label;
    private JButton addButton;
    private JButton searchButton;
    private JComboBox person;
    private JTextField searchBar;
    private JComboBox boolSelect;
    private JTextArea resultsBox;
    private JButton printResults;
    private JRadioButton radioButton;

    public DatabaseGUI() {
        query = new JPanel(new FlowLayout());
        results = new JPanel(new FlowLayout());
        label = new JLabel("Enter query");
        addButton = new JButton("Add");
        addButton.setBounds(50, 30, 100, 40);
        searchButton = new JButton("Search");
        searchButton.setBounds(180, 30, 100, 40);
        //search.addActionListener(new SearchListener());
        person = new JComboBox();
        searchBar = new JTextField(20);
        boolSelect = new JComboBox();


        resultsBox = new JTextArea("Results");
        resultsBox.setRows(20);
        resultsBox.setColumns(80);
        printResults = new JButton("Print Results");

        // panel 1: query
        query.add(label);
        query.add(addButton);
        query.add(searchButton, BorderLayout.WEST);
        query.add(person);
        query.add(searchBar);
        query.add(boolSelect);

        // panel 2: results
        results.add(resultsBox);
        results.add(printResults);

        // add panels to JFrame
        add(query);
        add(results);

        // set window title
        setTitle("FILMPEDIA");
        // set window size
        setSize(1800, 800);
        // set layout
        //setLayout(null);
        setLayout(new GridLayout(1, 2));
        // set action on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display window
        setVisible(true);
    }

    public static void main(String[] args) {
        DatabaseGUI window = new DatabaseGUI();
    }
}
