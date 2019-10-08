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
        person.setBounds(50, 100, 100, 40);
        searchBar = new JTextField(20);
        searchBar.setBounds(160, 100, 500, 40);
        boolSelect = new JComboBox();
        boolSelect.setBounds(670, 100, 100, 40);


        resultsBox = new JTextArea("Results");
        resultsBox.setBounds(1000, 30, 700, 300);
        printResults = new JButton("Print Results");

        // panel 1: query
        add(label);
        add(addButton);
        add(searchButton);
        add(person);
        add(searchBar);
        add(boolSelect);

        // panel 2: results
        add(resultsBox);
        add(printResults);

        // add panels to JFrame
        add(query);
        add(results);

        // set window title
        setTitle("FILMPEDIA");
        // set window size
        setSize(1800, 800);
        // set layout
        //setLayout(null);
        //setLayout(new GridLayout(1, 2));
        // set action on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display window
        setVisible(true);
    }

    public static void main(String[] args) {
        DatabaseGUI window = new DatabaseGUI();
    }
}
