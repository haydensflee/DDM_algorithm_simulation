
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Arrays;

public class mainWindow {
    Object sel;
    private static ArrayList<alien> alienList = new ArrayList<alien>();
    private static ArrayList<cowboy> cowboyList = new ArrayList<cowboy>();
    private static String selectedAlgorithm = "";
    private static double[] x_cowboys={50.}, y_cowboys={50.}, x_aliens={50.}, y_aliens={50.}, x_base={50.}, y_base={50.};
    private static JPanel map_frame;
    private static DefaultListModel agentListModel;

    public mainWindow() {
        
        JFrame jf = new JFrame();
        jf.setLayout(new FlowLayout());
        // Construct panels
        agentProperties agentPropertiesPanel = new agentProperties();
        agentList agentListPanel = new agentList();
        
        map mapPanel = new map(x_cowboys, y_cowboys,x_aliens, y_aliens,x_base, y_base);
  
        JButton generateButton = new JButton("Generate config file!");
        generateButton.setName("generateButton");
        
        String algorithmSelect[] = { "-----", "Ring Algorithm", "PAXOS Algorithm", "RAFT Algorthm" };
        JComboBox<String> comboBox = new JComboBox<>(algorithmSelect);
        comboBox.setSelectedIndex(0);
        selectedAlgorithm = comboBox.getSelectedItem().toString();

        agentListModel = agentListPanel.getListModel();
        JList myList = agentListPanel.getList();

        map_frame = new JPanel();
        jf.add(map_frame);
        map_frame.add(mapPanel);
        
        jf.add(agentListPanel);
        jf.add(agentPropertiesPanel);
        jf.add(comboBox);
        jf.add(generateButton);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = createMenuBar(jf);
        jf.setBounds(100, 100, 1000, 700);
        jf.setVisible(true);

        // Add button action listener
        agentPropertiesPanel.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer tempXPos = 0;
                Integer tempYPos = 0;
                Random randomX = new Random();
                Random randomY = new Random();
                if (agentPropertiesPanel.getXTextField().getText().equals("")) {
                    System.out.println("empty fields");
                    tempXPos = randomX.nextInt(100) + 1;
                    tempYPos = randomY.nextInt(100) + 1;
                }
                else{
                    tempXPos = Integer.parseInt(agentPropertiesPanel.getXTextField().getText());
                    tempYPos = Integer.parseInt(agentPropertiesPanel.getYTextField().getText());
                }
                if (agentPropertiesPanel.getAlienRadio().isSelected()) {
                    alien myAlien = new alien();
                    
                    // System.out.println(agentPropertiesPanel.getXTextField().getText());
                    // Random alienxrand = new Random();
                    // Random alienyrand = new Random();

                    myAlien.ID = alienList.size();
                    myAlien.xPos = tempXPos;
                    myAlien.yPos = tempYPos;
                    myAlien.visionRange = agentPropertiesPanel.getTalkSlider().getValue();
                    myAlien.moveSpeed = agentPropertiesPanel.getShootSlider().getValue();
                    alienList.add(myAlien);
                    System.out.println("alien added. Alien count is");
                    System.out.println(alienList.size());
                    agentListModel.addElement(myAlien);
                    
                    map_frame.removeAll();
                    map_frame.add(repaint_map());
                    map_frame.revalidate();
                    map_frame.repaint();
                    

                }
                if (agentPropertiesPanel.getCowboyRadio().isSelected()) {
                    cowboy myCowboy = new cowboy();
                    myCowboy.ID = cowboyList.size();
                    myCowboy.xPos = tempXPos;
                    myCowboy.yPos = tempYPos;
                    myCowboy.talkRange = agentPropertiesPanel.getTalkSlider().getValue();
                    myCowboy.shootRange = agentPropertiesPanel.getShootSlider().getValue();
                    cowboyList.add(myCowboy);
                    System.out.println("cowboy added. cowboy count is");
                    System.out.println(cowboyList.size());
                    agentListModel.addElement(myCowboy);
                    
                    map_frame.removeAll();
                    map_frame.add(repaint_map());
                    map_frame.revalidate();
                    map_frame.repaint();
                }
                agentPropertiesPanel.getXTextField().setText("");
                agentPropertiesPanel.getYTextField().setText("");
            }
        });

        // "DELETE" button action listener
        agentPropertiesPanel.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agentType;

                System.out.println("delete " + sel);
                agentType = sel.getClass().getSimpleName();

                if (agentType.toString() == "alien") {
                    // System.out.println(alienList);
                    alienList.remove(sel);
                    // System.out.println(alienList);
                } else if (agentType.toString() == "cowboy") {
                    cowboyList.remove(sel);
                }
                agentListModel.removeElement(sel);

                map_frame.removeAll();
                map_frame.add(repaint_map());
                map_frame.revalidate();
                map_frame.repaint();
            }
        });

        // listener for selecting from agent list panel
        myList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String agentType;
                String xPosString = "";
                String yPosString = "";
                int talkRange = 0;
                int shootRange = 0;
                int firstSelIx = agentListPanel.getList().getSelectedIndex();
                sel = myList.getModel().getElementAt(firstSelIx);
                System.out.println("clicked " + sel);
                agentType = sel.getClass().getSimpleName();
                agentPropertiesPanel.setAgentRadio(agentType);

                if (agentType.toString() == "alien") {
                    alien myAlien = (alien) sel;
                    xPosString = Integer.toString(myAlien.xPos);
                    yPosString = Integer.toString(myAlien.yPos);
                } else if (agentType.toString() == "cowboy") {
                    cowboy myCowboy = (cowboy) sel;
                    xPosString = Integer.toString(myCowboy.xPos);
                    yPosString = Integer.toString(myCowboy.yPos);

                    talkRange = myCowboy.talkRange;
                    shootRange = myCowboy.shootRange;
                }

                // test = sel.getXPos();
                // System.out.print(test);

                agentPropertiesPanel.getXTextField().setText(xPosString);
                agentPropertiesPanel.getYTextField().setText(yPosString);
                // sel.getSelectedValue();

            }
        });

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JSONArray alienJSONArray = new JSONArray();
                JSONArray cowboyJSONArray = new JSONArray();
                JSONArray otherJSONArray = new JSONArray();

                // System.out.println(alienList.size());

                for (int i = 0; i < alienList.size(); i++) {
                    // System.out.println(i);
                    JSONObject myAlienJSON = new JSONObject();
                    alien myAlien = alienList.get(i);
                    // System.out.println(myAlien);
                    myAlienJSON.put("ID", myAlien.ID);
                    myAlienJSON.put("xPos", myAlien.xPos);
                    myAlienJSON.put("yPos", myAlien.yPos);
                    myAlienJSON.put("Vision Range", myAlien.visionRange);
                    myAlienJSON.put("Speed",  0.5);
                    alienJSONArray.add(myAlienJSON);
                }
                for (int i = 0; i < cowboyList.size(); i++) {
                    // System.out.println(i);
                    JSONObject myCowboyJSON = new JSONObject();
                    cowboy myCowboy = cowboyList.get(i);
                    // System.out.println(myAlien);
                    myCowboyJSON.put("ID", myCowboy.ID);
                    myCowboyJSON.put("xPos", myCowboy.xPos);
                    myCowboyJSON.put("yPos", myCowboy.yPos);
                    myCowboyJSON.put("Talk Range", myCowboy.talkRange);
                    myCowboyJSON.put("Shoot Range", myCowboy.shootRange);
                    cowboyJSONArray.add(myCowboyJSON);
                }
                selectedAlgorithm = comboBox.getSelectedItem().toString();
                JSONObject mainObj = new JSONObject();
                mainObj.put("Aliens", alienJSONArray);
                mainObj.put("Cowboys", cowboyJSONArray);

                JSONObject otherJSON = new JSONObject();
                otherJSON.put("Algorithm", selectedAlgorithm);
                otherJSON.put("Headerless", true);
                otherJSONArray.add(otherJSON);
                mainObj.put("Other", otherJSONArray);
                
                System.out.println("Generating config file...");
                try {
                    FileWriter file = new FileWriter("outputs/config.json");
                    file.write(mainObj.toJSONString());
                    file.close();
                } catch (IOException f) {
                    f.printStackTrace();
                }
                System.out.println("JSON file created: " + mainObj);
            }
        });

    }
    
    public static map repaint_map(){

        ArrayList<alien> temp_alienList = new ArrayList<alien>();
        ArrayList<cowboy> temp_cowboyList = new ArrayList<cowboy>();

        if (alienList.size()==0){
            alien dummyAlien = new alien();
            dummyAlien.ID = 0;
            dummyAlien.xPos = 50;
            dummyAlien.yPos = 50;
            temp_alienList.add(dummyAlien);
        }else{
            temp_alienList=alienList;
        }

        if (cowboyList.size()==0){
            cowboy dummyCowboy = new cowboy();
            dummyCowboy.ID = 0;
            dummyCowboy.xPos = 50;
            dummyCowboy.yPos = 50;
            dummyCowboy.talkRange = 25;
            dummyCowboy.shootRange = 25;
            temp_cowboyList.add(dummyCowboy);
        }else{
            temp_cowboyList=cowboyList;
        }

        double[] new_x_cowboys=new double[temp_cowboyList.size()];
        double[] new_y_cowboys=new double[temp_cowboyList.size()];
        double[] new_x_aliens=new double[temp_alienList.size()];
        double[] new_y_aliens=new double[temp_alienList.size()];


        for (int i = 0; i < temp_cowboyList.size(); i++) {
            cowboy myCowboy = temp_cowboyList.get(i);
            new_x_cowboys[i]=myCowboy.xPos;
            new_y_cowboys[i]=myCowboy.yPos;
        }

        for (int i = 0; i < temp_alienList.size(); i++) {
            alien myAlien = temp_alienList.get(i);
            new_x_aliens[i]=myAlien.xPos;
            new_y_aliens[i]=myAlien.yPos;
        }

        return new map(new_x_cowboys, new_y_cowboys,new_x_aliens, new_y_aliens,x_base, y_base);
    }

    private static JMenuBar createMenuBar(JFrame frame) {
        // create a menu bar
        final JMenuBar menuBar = new JMenuBar();
  
        // create menus
        JMenu fileMenu = new JMenu("File");
        // JMenu editMenu = new JMenu("Edit");
        // final JMenu aboutMenu = new JMenu("About");
        // final JMenu linkMenu = new JMenu("Links");
  
        // create menu items
        JMenuItem importConfig = new JMenuItem("Import config");
        importConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                config_menu_select(evt);
            }

            private void config_menu_select(java.awt.event.ActionEvent evt) {
                JFileChooser fileChooser=new JFileChooser();
                int result;

                System.out.println("The menu item is selected"); 
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                  File selectedFile = fileChooser.getSelectedFile();
   
                  // parsing file "JSONExample.json"
                  JSONParser jsonParser = new JSONParser();
           
                  try (FileReader reader = new FileReader(selectedFile.getAbsolutePath()))
                  {
                      //Read JSON file
                      Object obj = jsonParser.parse(reader);
          
                      JSONObject jo = (JSONObject) obj;
                      // System.out.println(jo);
   
                     //  String import_algorithm = (String) jo.get("Algorithm");
                     JSONArray import_aliens = (JSONArray) jo.get("aliens");
                     import_aliens.forEach( imp_alien -> parseAlienImport( (JSONObject) imp_alien ) );
                     JSONArray import_cowboys = (JSONArray) jo.get("Monkes");
                     import_cowboys.forEach( imp_cowboy -> parseCowboyImport( (JSONObject) imp_cowboy ) );
   
                     String import_algorthm = (String) jo.get("Algorithm");
                     selectedAlgorithm=import_algorthm;
                     System.out.println("Imported conifg file: " + selectedFile.getAbsolutePath());
 
                     map_frame.removeAll();
  
                     // map mapPanel = new map(x_cowboys, y_cowboys,x_aliens, y_aliens,x_base, y_base);
                     map_frame.add(repaint_map());
                     map_frame.revalidate();
                     map_frame.repaint();
 
   
   
                  } catch (FileNotFoundException e) {
                      e.printStackTrace();
                  } catch (IOException e) {
                      e.printStackTrace();
                  } catch (org.json.simple.parser.ParseException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  } 
              
   
                }
   
            }
                

            private static void parseAlienImport(JSONObject imp_alien) {
                //Get alien data
                Long alien_imp_yPos = (Long) imp_alien.get("yPos");
                Long alien_imp_xPos = (Long) imp_alien.get("xPos");    
                Long alien_imp_ID = (Long) imp_alien.get("ID");
        
                alien myAlien = new alien();
                myAlien.ID = alien_imp_ID.intValue();
                myAlien.xPos = alien_imp_xPos.intValue();
                myAlien.yPos = alien_imp_yPos.intValue();
                alienList.add(myAlien);
                agentListModel.addElement(myAlien);
                System.out.println("alien added. Alien count is");
                System.out.println(alienList.size());
            }
            private static void parseCowboyImport(JSONObject imp_cowboy) {
                //Get cowboy data
                Long cowboy_imp_yPos = (Long) imp_cowboy.get("yPos");
                Long cowboy_imp_xPos = (Long) imp_cowboy.get("xPos");    
                Long cowboy_imp_ID = (Long) imp_cowboy.get("ID");
                Long cowboy_imp_shootRange = (Long) imp_cowboy.get("Shoot Range");
                Long cowboy_imp_talkRange = (Long) imp_cowboy.get("Talk Range");
        
                cowboy myCowboy = new cowboy();
                myCowboy.ID = cowboy_imp_ID.intValue();
                myCowboy.xPos = cowboy_imp_xPos.intValue();
                myCowboy.yPos = cowboy_imp_yPos.intValue();
                myCowboy.shootRange = cowboy_imp_shootRange.intValue();
                myCowboy.talkRange = cowboy_imp_talkRange.intValue();
                cowboyList.add(myCowboy);
                agentListModel.addElement(myCowboy);
                System.out.println("cowboy added. Cowboy count is");
                System.out.println(cowboyList.size());
            }



        });

          
    
  
        // add menu items to menus
        fileMenu.add(importConfig);
  
        // add menu to menubar
        menuBar.add(fileMenu);
        // menuBar.add(editMenu);
        // menuBar.add(aboutMenu);
        // menuBar.add(linkMenu);
  
        // add menubar to the frame
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
         
        return menuBar;
     }
    
    public static void main(String[] args) {
        new mainWindow();
    }
}
