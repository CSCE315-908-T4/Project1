package edu.tamu.csce315_908_t4.gui.frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabaseGUI extends JFrame implements ActionListener {
    private JPanel query;
    private JPanel results;
    private JLabel label;
    private JButton addButton;
    private JButton searchButton;
    private JComboBox searchOption;
    private JTextField searchBar;
    private JComboBox boolSelect;
    private JTextArea resultsBox;
    private JLabel numResults;
    private JButton next10;
    private JRadioButton person, title, episode;
    private JLabel pathLabel;
    private JTextField pathField;
    private JButton sendToFile;
    private ButtonGroup bg;

    public DatabaseGUI() {
        // query side
        addButton = new JButton("Add");
        addButton.setBounds(50, 30, 100, 40);
        addButton.addActionListener(this);
        searchButton = new JButton("Search");
        searchButton.setBounds(180, 30, 100, 40);
        searchButton.addActionListener(this);
        searchOption = new JComboBox();
        searchOption.setBounds(50, 100, 100, 40);
        searchBar = new JTextField(20);
        searchBar.setBounds(160, 100, 500, 40);
        boolSelect = new JComboBox();
        boolSelect.setBounds(670, 100, 100, 40);

        // results side
        resultsBox = new JTextArea("Results");
        resultsBox.setBounds(1000, 30, 700, 300);
        numResults = new JLabel("Number of Results: 00");
        numResults.setBounds(1000, 350, 200, 40);
        next10 = new JButton("Next 10");
        next10.setBounds(1150, 350, 100, 40);
        person = new JRadioButton("Person");
        person.addActionListener(this);
        person.setBounds(1000, 410, 100, 20);
        title = new JRadioButton("Title");
        title.addActionListener(this);
        title.setBounds(1000, 430, 100, 20);
        episode = new JRadioButton("Episode");
        episode.addActionListener(this);
        episode.setBounds(1000, 450, 100, 20);
        pathLabel = new JLabel("Path:");
        pathLabel.setBounds(1350, 460, 100, 40);
        pathField = new JTextField();
        pathField.setBounds(1400, 460, 200, 40);
        sendToFile = new JButton("Send to File");
        sendToFile.addActionListener(this);
        sendToFile.setBounds(1500, 410, 100, 40);
        bg = new ButtonGroup();
        bg.add(person);
        bg.add(title);
        bg.add(episode);

        // query side
        add(addButton);
        add(searchButton);
        add(searchOption);
        add(searchBar);
        add(boolSelect);

        // results side
        add(resultsBox);
        add(numResults);
        add(next10);
        add(person);
        add(title);
        add(episode);
        add(pathLabel);
        add(pathField);
        add(sendToFile);

        // set window title
        setTitle("FILMPEDIA");
        // set window size
        setSize(1800, 800);
        // set layout
        setLayout(null);
        // set action on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display window
        setVisible(true);
    }

    public static void main(String[] args) {
        DatabaseGUI window = new DatabaseGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == addButton.getAction()) {

        }
    }
}
