package archive;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FileB extends JPanel {
    public FileB() {
        setPreferredSize(new Dimension(200, 480));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder("Agent Properties"));
        ButtonGroup group = new ButtonGroup();
        JRadioButton MonkeButton = new JRadioButton("Monke");
        JRadioButton alienButton = new JRadioButton("Alien");
        MonkeButton.setName("MonkeButton");
        alienButton.setName("alienButton");
        alienButton.setSelected(true);
        alienButton.setActionCommand(alienButton.getText());
        JRadioButton targetButton = new JRadioButton("Target");
        group.add(MonkeButton);
        group.add(alienButton);
        group.add(targetButton);
        this.add(MonkeButton);
        this.add(alienButton);
        this.add(targetButton);

        JButton addButton = new JButton("ADD");
        JButton printButton = new JButton("print alien/Monke lists (placeholder)");
        JButton deleteButton = new JButton("DELETE (placeholder)");
        addButton.setName("addButton");
        printButton.setName("printButton");
        deleteButton.setName("deleteButton");

    }
}