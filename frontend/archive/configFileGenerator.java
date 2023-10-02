package archive;
// package com.tutorialspoint.gui;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class Alien {
   int ID = -1;
   int xPos = -1;
   int yPos = -1;
}

class Monke {
   int ID = -1;
   int xPos = -1;
   int yPos = -1;
   int talkRange = -1;
   int shootRange = -1;
}

public class configFileGenerator {
   public static ArrayList<Alien> alienList = new ArrayList<Alien>();
   public static ArrayList<Monke> MonkeList = new ArrayList<Monke>();
   public static String selectedAlgorithm = "";

   public static void main(String[] args) throws Exception {
      createWindow();
   }

   private static JMenuBar createMenuBar(JFrame frame) throws Exception {
      JSONParser jsonParser = new JSONParser();
      // create a menu bar
      final JMenuBar menuBar = new JMenuBar();

      // create menus
      JMenu fileMenu = new JMenu("File");
      JMenu editMenu = new JMenu("Edit");
      final JMenu aboutMenu = new JMenu("About");
      final JMenu linkMenu = new JMenu("Links");

      // create menu items
      JMenuItem importConfig = new JMenuItem("Import config");
      importConfig.setActionCommand("import");

      importConfig.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            final JLabel label = new JLabel();
            String filename = "";
            String path = "";
            System.out.println("importing config...");
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
               File file = fileChooser.getSelectedFile();
               label.setText("File Selected: " + file.getName());
               path = file.getAbsolutePath();
               filename = file.getName();

            } else {
               label.setText("Open command canceled");
            }
            System.out.println("File Selected: " + path);

            // !!! Can't quite crack parsing the JSON as an input file. Will try next
            // week... !!! \\
            JSONParser jsonParser = new JSONParser();
            // try {
            // JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
            // } catch (FileNotFoundException fe) {
            // fe.printStackTrace();
            // } catch (IOException ie) {
            // ie.printStackTrace();
            // } catch (ParseException pe) {
            // pe.printStackTrace();
            // }

         }
      });

      // add menu items to menus
      fileMenu.add(importConfig);

      // add menu to menubar
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      menuBar.add(aboutMenu);
      menuBar.add(linkMenu);

      // add menubar to the frame
      frame.setJMenuBar(menuBar);
      frame.setVisible(true);

      return menuBar;
   }

   private static void createWindow() throws Exception {
      JFrame frame = new JFrame("Swing Tester");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      createUI(frame);
      JMenuBar menuBar = createMenuBar(frame);

      frame.setSize(700, 600);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   private static JPanel createPanel0(JPanel mainPanel, GridBagConstraints gbc) {
      JPanel panel0 = new JPanel();
      panel0.setName("panel0");
      GridLayout grid = new GridLayout(8, 0);
      Border border0 = BorderFactory.createTitledBorder("Dan's batch agent creation");
      panel0.setBorder(border0);

      panel0.setLayout(grid);
      JLabel l1, l2, l3, l4;

      l2 = new JLabel("Welcome, Aliens Are Killing The Monke. Who will win?", SwingConstants.CENTER);
      panel0.add(l2);
      panel0.add(new JLabel("How Many Aliens?", SwingConstants.CENTER));
      JTextField t1 = new JTextField(5);
      panel0.add(t1);
      // p.add(Box.createHorizontalStrut(15)); // a spacer
      panel0.add(new JLabel("How Many Monke?", SwingConstants.CENTER));
      JTextField t2 = new JTextField(5);
      panel0.add(t2);
      return panel0;

   }

   private static JPanel createPanel1(JPanel mainPanel, GridBagConstraints gbc) {
      JPanel panel1 = new JPanel();
      panel1.setName("panel1");
      panel1.setLayout(new GridLayout(0, 2));
      Border border1 = BorderFactory.createTitledBorder("Place Agents");
      ButtonGroup group = new ButtonGroup();
      JRadioButton MonkeButton = new JRadioButton("Monke");
      JRadioButton alienButton = new JRadioButton("Alien");
      MonkeButton.setName("MonkeButton");
      alienButton.setName("alienButton");
      alienButton.setSelected(true);
      alienButton.setActionCommand(alienButton.getText());
      JRadioButton targetButton = new JRadioButton("Target");
      JButton addButton = new JButton("ADD");
      JButton printButton = new JButton("print alien/Monke lists (placeholder)");
      JButton deleteButton = new JButton("DELETE (placeholder)");
      addButton.setName("addButton");
      printButton.setName("printButton");
      deleteButton.setName("deleteButton");
      group.add(MonkeButton);
      group.add(alienButton);
      group.add(targetButton);

      panel1.add(MonkeButton);
      panel1.add(addButton);
      panel1.add(alienButton);
      panel1.add(deleteButton);
      panel1.add(targetButton);
      panel1.add(printButton);

      panel1.setBorder(border1);

      return panel1;
   }

   private static JPanel createPanel2(JPanel mainPanel, GridBagConstraints gbc) {
      JPanel panel2 = new JPanel();
      panel2.setLayout(new GridLayout(0, 2));
      panel2.setName("panel2");
      Border border2 = BorderFactory.createTitledBorder("Edit Agent Ranges");
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.ipady = 20;
      gbc.ipadx = 200;
      gbc.gridx = 0;
      gbc.gridy = 1;
      JSlider talkSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
      talkSlider.setName("talkSlider");
      talkSlider.setPaintTicks(true);
      talkSlider.setMinorTickSpacing(10);
      talkSlider.setPaintTrack(true);
      talkSlider.setMajorTickSpacing(25);
      talkSlider.setPaintLabels(true);
      JSlider shootSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
      shootSlider.setName("shootSlider");
      shootSlider.setPaintTicks(true);
      shootSlider.setMinorTickSpacing(10);
      shootSlider.setPaintTrack(true);
      shootSlider.setMajorTickSpacing(25);
      shootSlider.setPaintLabels(true);
      panel2.add(new JLabel("The Monke Talk Range (m)"));
      panel2.add(talkSlider);
      panel2.add(new JLabel("El Monke Shoot Range (m)"));
      panel2.add(shootSlider);
      panel2.setBorder(border2);
      return panel2;
   }

   private static JPanel createPanel3(JPanel mainPanel, GridBagConstraints gbc) {
      JPanel panel3 = new JPanel();
      panel3.setName("panel3");
      panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
      Border border3 = BorderFactory.createTitledBorder("Select Le Distributed Algorithm");
      gbc.gridx = 0;
      gbc.gridy = 2;
      String algorithmSelect[] = { "-----", "Dan's Algorithm", "Sam's Algorithm", "Hayden's Algorthm" };
      JComboBox<String> comboBox = new JComboBox<>(algorithmSelect);
      comboBox.setSelectedIndex(0);
      panel3.add(comboBox);
      panel3.setBorder(border3);
      mainPanel.add(panel3, gbc);
      selectedAlgorithm = comboBox.getSelectedItem().toString();
      return panel3;
   }

   private static JButton createGenerateButton(JPanel mainPanel, GridBagConstraints gbc) {
      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridwidth = 2;
      JButton generateButton = new JButton("Generate config file!");
      generateButton.setName("generateButton");
      mainPanel.add(generateButton, gbc);
      generateButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JSONArray alienJSONArray = new JSONArray();
            JSONArray MonkeJSONArray = new JSONArray();

            // System.out.println(alienList.size());

            for (int i = 0; i < alienList.size(); i++) {
               // System.out.println(i);
               JSONObject myAlienJSON = new JSONObject();
               Alien myAlien = alienList.get(i);
               // System.out.println(myAlien);
               myAlienJSON.put("ID", myAlien.ID);
               myAlienJSON.put("xPos", myAlien.xPos);
               myAlienJSON.put("yPos", myAlien.yPos);
               alienJSONArray.add(myAlienJSON);
            }
            for (int i = 0; i < MonkeList.size(); i++) {
               // System.out.println(i);
               JSONObject myMonkeJSON = new JSONObject();
               Monke myMonke = MonkeList.get(i);
               // System.out.println(myAlien);
               myMonkeJSON.put("ID", myMonke.ID);
               myMonkeJSON.put("xPos", myMonke.xPos);
               myMonkeJSON.put("yPos", myMonke.yPos);
               myMonkeJSON.put("Talk Range", myMonke.talkRange);
               myMonkeJSON.put("Shoot Range", myMonke.shootRange);
               MonkeJSONArray.add(myMonkeJSON);
            }
            JSONObject mainObj = new JSONObject();
            mainObj.put("aliens", alienJSONArray);
            mainObj.put("Monkes", MonkeJSONArray);
            mainObj.put("Algorithm", selectedAlgorithm);

            System.out.println("Generating config file...");
            try {
               FileWriter file = new FileWriter("frontend/outputs/config.json");
               file.write(mainObj.toJSONString());
               file.close();
            } catch (IOException f) {
               // TODO Auto-generated catch block
               f.printStackTrace();
            }
            System.out.println("JSON file created: " + mainObj);
         }
      });
      return generateButton;
   }

   private static void placeAgentButtonFunctions(JButton addButton, JButton editButton, JButton printButton,
         JRadioButton alienButton, JRadioButton MonkeButton, JSlider talkSlider, JSlider shootSlider) {
      // Component[] components = mainPanel.getComponents();
      // for (int i = 0; i < components.length; i++) {
      // System.out.println("Component name - " + components[i].getName());
      // }
      // JPanel panel2 = (JPanel) components[2];
      // Component[] components2 = panel2.getComponents();
      // for (int i = 0; i < components2.length; i++) {
      // System.out.println("Component2 name - " + components2[i].getName());
      // }
      addButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (alienButton.isSelected()) {
               Alien myAlien = new Alien();
               Random alienxrand = new Random();
               Random alienyrand = new Random();

               myAlien.ID = alienList.size();
               myAlien.xPos = alienxrand.nextInt(100) + 1;
               myAlien.yPos = alienxrand.nextInt(100) + 1;
               alienList.add(myAlien);
               System.out.println("alien added. Alien count is");
               System.out.println(alienList.size());
            }
            if (MonkeButton.isSelected()) {
               Monke myMonke = new Monke();
               Random monkexrand = new Random();
               Random monkeyrand = new Random();
               myMonke.ID = MonkeList.size();
               myMonke.xPos = monkexrand.nextInt(100) + 1;
               myMonke.yPos = monkeyrand.nextInt(100) + 1;
               myMonke.talkRange = talkSlider.getValue();
               myMonke.shootRange = shootSlider.getValue();
               MonkeList.add(myMonke);
               System.out.println("Monke added. Monke count is");
               System.out.println(MonkeList.size());
            }
         }
      });
      printButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println("printing lists...");
            System.out.println(MonkeList);
            System.out.println(alienList);
            // int f1 = talkSlider.getValue();
            // int f2 = shootSlider.getValue();
         }
      });
   }

   private static void createUI(final JFrame frame) {

      JPanel mainPanel = new JPanel();
      GridBagLayout layout = new GridBagLayout();
      mainPanel.setLayout(layout);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.ipady = 20;
      gbc.ipadx = 80;
      gbc.gridx = 0;
      gbc.gridy = 0;

      JPanel panel0 = createPanel0(mainPanel, gbc);
      JPanel panel1 = createPanel1(mainPanel, gbc);
      JPanel panel2 = createPanel2(mainPanel, gbc);
      JPanel panel3 = createPanel3(mainPanel, gbc);
      JButton generateButton = createGenerateButton(mainPanel, gbc);
      // int f1 = mainPanel.panel2.talkSlider.getValue();
      gbc.gridx = 0;
      gbc.gridy = 0;
      mainPanel.add(panel0, gbc);
      gbc.gridx = 0;
      gbc.gridy = 1;
      mainPanel.add(panel1, gbc);
      gbc.gridx = 0;
      gbc.gridy = 2;
      mainPanel.add(panel2, gbc);
      gbc.gridx = 0;
      gbc.gridy = 3;
      mainPanel.add(panel3, gbc);
      gbc.gridx = 0;
      gbc.gridy = 4;
      mainPanel.add(generateButton, gbc);

      // Getting button components from panel 1 - Will put into function later
      Component[] components = panel1.getComponents();
      JButton addButton = new JButton();
      JButton printButton = new JButton();
      JButton deleteButton = new JButton();
      JRadioButton alienButton = new JRadioButton();
      JRadioButton MonkeButton = new JRadioButton();

      for (int i = 0; i < components.length; i++) {
         if (components[i].getName() == "addButton") {
            addButton = (JButton) components[i];
         }
         if (components[i].getName() == "printButton") {
            printButton = (JButton) components[i];
         }
         if (components[i].getName() == "deleteButton") {
            deleteButton = (JButton) components[i];
         }
         if (components[i].getName() == "alienButton") {
            alienButton = (JRadioButton) components[i];
         }
         if (components[i].getName() == "MonkeButton") {
            MonkeButton = (JRadioButton) components[i];
         }
      }

      // Getting slider components from panel 2 - Will put into function later
      Component[] components2 = panel2.getComponents();
      JSlider talkSlider = new JSlider();
      JSlider shootSlider = new JSlider();
      for (int i = 0; i < components2.length; i++) {
         if (components2[i].getName() == "talkSlider") {
            talkSlider = (JSlider) components2[i];
         }
         if (components2[i].getName() == "shootSlider") {
            shootSlider = (JSlider) components2[i];
         }
      }
      placeAgentButtonFunctions(addButton, deleteButton, printButton, alienButton, MonkeButton, talkSlider,
            shootSlider);
      frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
   }
}