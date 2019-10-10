package edu.tamu.csce315_908_t4.gui.frontend;

import javax.swing.*;

public class FindDegreePanel extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JTextField actor1;
    private JTextField actor2;
    private JTextArea results;
    private JButton search;

    public FindDegreePanel() {
        label1 = new JLabel("Insert Actor 1");
        label1.setBounds(50, 30, 100, 30);
        label2 = new JLabel("Insert Actor 2");
        label2.setBounds(150, 30, 100, 30);
        actor1 = new JTextField();
        //actor1.setBounds();
        actor2 = new JTextField();
        results = new JTextArea();
        search = new JButton("Search");

        add(label1);
        add(label2);
        add(actor1);
        add(actor2);
        add(results);
        add(search);

        // set window title
        setTitle("Find Degrees Between 2 Actors");
        // set window size
        setSize(1000, 800);
        // set layout
        setLayout(null);
        // set action on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display window
        setVisible(true);
    }

    public static void main(String[] args) {
        FindDegreePanel window = new FindDegreePanel();
    }
}
