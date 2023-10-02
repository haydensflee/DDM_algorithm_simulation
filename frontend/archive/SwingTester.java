package archive;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class SwingTester {
   public static void main(String[] args) {
      createWindow();
   }

   private static void createWindow() {
      JFrame frame = new JFrame("Swing Tester");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      createUI(frame);
      frame.setSize(560, 600);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   // private static void createUI(JFrame frame){
   // //Create a border
   // Border blackline = BorderFactory.createTitledBorder("Title");
   // JPanel panel = new JPanel();
   // LayoutManager layout = new FlowLayout();
   // panel.setLayout(layout);

   // JPanel panel1 = new JPanel();
   // String spaces = " ";

   // panel1.add(new JLabel(spaces + "Title border to JPanel" + spaces));
   // panel1.setBorder(blackline);

   // panel.add(panel1);
   // frame.getContentPane().add(panel, BorderLayout.CENTER);
   // }
   private static void createUI(final JFrame frame) {
      JPanel panel = new JPanel();

      GridBagLayout layout = new GridBagLayout();

      panel.setLayout(layout);
      GridBagConstraints gbc = new GridBagConstraints();

      // LayoutManager layout1 = new BoxLayout(panel, BoxLayout.Y_AXIS);
      JPanel panel1 = new JPanel();
      panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
      Border border1 = BorderFactory.createTitledBorder("Place Entities");
      JRadioButton radioButton1 = new JRadioButton("Cowboy");
      JRadioButton radioButton2 = new JRadioButton("Alien");
      JRadioButton radioButton3 = new JRadioButton("Target");
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.ipady = 20;
      gbc.ipadx = 80;
      gbc.gridx = 0;
      gbc.gridy = 0;
      panel1.add(radioButton1);
      panel1.add(radioButton2);
      panel1.add(radioButton3);
      panel1.setBorder(border1);
      panel.add(panel1, gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      // panel.add(new JButton("Button 2"),gbc);

      JPanel panel2 = new JPanel();
      panel2.setLayout(new GridLayout(0, 2));
      Border border2 = BorderFactory.createTitledBorder("Edit Entity Ranges");
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.ipady = 20;
      gbc.ipadx = 200;
      gbc.gridx = 0;
      gbc.gridy = 1;
      JSlider talkSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
      JSlider shootSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
      panel2.add(new JLabel("Talk Range (m)"));
      panel2.add(talkSlider);
      panel2.add(new JLabel("Shoot Range (m)"));
      panel2.add(shootSlider);
      panel2.setBorder(border2);
      panel.add(panel2, gbc);
      // panel.add(new JButton("Button 3"),gbc);

      JPanel panel3 = new JPanel();
      panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
      Border border3 = BorderFactory.createTitledBorder("Select Distributed Algorithm");
      gbc.gridx = 0;
      gbc.gridy = 2;
      String[] numbers = { "One", "Two", "Three", "Four", "Five" };
      JComboBox<String> comboBox = new JComboBox<>(numbers);
      comboBox.setSelectedIndex(3);
      panel3.add(comboBox);
      panel3.setBorder(border3);
      panel.add(panel3, gbc);

      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridwidth = 2;
      panel.add(new JButton("Run Simulation"), gbc);

      frame.getContentPane().add(panel, BorderLayout.CENTER);
   }
}