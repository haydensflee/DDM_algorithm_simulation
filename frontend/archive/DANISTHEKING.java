package archive;

// Java program to create a blank text
// field of definite number of columns.
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.applet.Applet;

import java.io.File; // Import the File class
import java.io.IOException; // Import the IOException class to handle errors
import java.util.Random;
import java.io.FileWriter; // Import the FileWriter class

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class text extends JFrame implements ActionListener {

  // JTextField
  static JTextField t1, t2;

  // JFrame
  static JFrame f;

  // JButton
  static JButton b;

  // label to display text
  static JLabel l1, l2, l3, l4;

  // combo box
  static JComboBox c1;

  // variables
  static int x1, x2;
  static float f1, f2;
  static String s1, s2, s3;

  // sliders
  static JSlider talkSlider, shootSlider;

  // default constructor
  text() {
  }

  // main class
  public static void main(String[] args) {

    // create a new frame to store text field and button
    f = new JFrame("textfield");

    // create a label to display text
    l1 = new JLabel("  ");
    l2 = new JLabel("Welcome, Aliens Are Killing The Monke. Who will win?", SwingConstants.CENTER);

    // create a new button
    b = new JButton("Submit");

    // create a object of the text class
    text te = new text();

    // addActionListener to button
    b.addActionListener(te);

    // create a object
    text db = new text();

    // array of algorithms
    String s1[] = { "-----", "Dan's Algorithm", "Sam's Algorithm", "Hayden's Algorthm" };

    // create checkbox
    c1 = new JComboBox(s1);

    // add ItemListener
    c1.addActionListener(db);

    // create a object of JTextField with 5 columns
    t1 = new JTextField(5);
    t2 = new JTextField(5);

    // create a panel to add buttons and textfield
    JPanel p = new JPanel();
    GridLayout grid = new GridLayout(15, 0);
    p.setLayout(grid);

    // add buttons and textfield to panel
    p.add(l2);
    p.add(new JLabel("How Many Aliens?", SwingConstants.CENTER));
    p.add(t1);
    // p.add(Box.createHorizontalStrut(15)); // a spacer
    p.add(new JLabel("How Many Monke?", SwingConstants.CENTER));
    p.add(t2);

    p.add(new JLabel("What Algorithm Are Le Aliens Using?", SwingConstants.CENTER));
    p.add(c1);

    talkSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
    shootSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);

    talkSlider.setPaintTicks(true);
    talkSlider.setMinorTickSpacing(10);
    talkSlider.setPaintTrack(true);
    talkSlider.setMajorTickSpacing(25);
    talkSlider.setPaintLabels(true);

    shootSlider.setPaintTicks(true);
    shootSlider.setMinorTickSpacing(10);
    shootSlider.setPaintTrack(true);
    shootSlider.setMajorTickSpacing(25);
    shootSlider.setPaintLabels(true);

    p.add(new JLabel("What Is The Monk Talk Range (m)", SwingConstants.CENTER));
    p.add(talkSlider);
    p.add(new JLabel("What Is El Monk Shoot Range (m)", SwingConstants.CENTER));
    p.add(shootSlider);

    p.add(b);
    p.add(l1);

    // add panel to frame
    f.add(p);

    // set the size of frame
    f.setSize(600, 600);

    f.show();
  }

  // if the button is pressed
  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    if (s.equals("Submit")) {

      // set the text of the label to the text of the field
      x1 = Integer.parseInt(t1.getText());
      x2 = Integer.parseInt(t2.getText());
      f1 = talkSlider.getValue();
      f2 = shootSlider.getValue();
      s1 = c1.getSelectedItem().toString();

      // if (x1>x2){
      // l1.setText("Aliens win");
      // } else{
      // l1.setText("Monke win");
      // }

      l1.setText("Config Generated");

      // VARIATION 1 - TEXT BASED - Explicit statement of each varaible
      try {

        FileWriter myWriter = new FileWriter("config1.txt");
        myWriter.write("//VARIATION 1 - TEXT BASED - Explicit statement of each varaible\n\n");
        myWriter.write("Number of Aliens: " + x1 + "\n");
        myWriter.write("Number of Monke: " + x2 + "\n");
        myWriter.write("Talk Range: " + f1 + "\n");
        myWriter.write("Shoot Range: " + f2 + "\n");
        myWriter.write("Algorithm Choice: " + s1 + "\n");
        myWriter.close();
        System.out.println("Successfully wrote to the file.");

      } catch (IOException err) {
        System.out.println("An error occurred.");
        err.printStackTrace();
      }

      // VARIATION 2 - CODE BASED - Variables separated by " "
      // Output in form: ALIEN_NO COWBOY_NO TALK_RANGE SHOOT_RANGE ALGORITHM_NO
      try {

        FileWriter myWriter = new FileWriter("config2.txt");
        myWriter.write(
            "//VARIATION 2 - code based\n//Output in form: ALIEN_NO COWBOY_NO TALK_RANGE SHOOT_RANGE ALGORITHM_NO\n\n");
        myWriter.write(x1 + " " + x2 + " " + f1 + " " + f2 + " " + s1 + "\n");
        myWriter.close();
        System.out.println("Successfully wrote to the file.");

      } catch (IOException err) {
        System.out.println("An error occurred.");
        err.printStackTrace();
      }

      // //VARIATION 3 - JSON Format - Variables in JSON library
      // //Creating a JSONObject object

      JSONArray alienJSONArray = new JSONArray();
      JSONArray monkeyJSONArray = new JSONArray();

      for (int i = 0; i < x1; i++) {
        int alienID = i + 1;

        Random alienxrand = new Random();
        Random alienyrand = new Random();
        int alienxPos = alienxrand.nextInt(100) + 1;
        int alienyPos = alienyrand.nextInt(100) + 1;

        JSONObject myAlienJSON = new JSONObject();
        myAlienJSON.put("ID", alienID);
        myAlienJSON.put("X Position", alienxPos);
        myAlienJSON.put("Y Position", alienyPos);
        alienJSONArray.add(myAlienJSON);
      }
      for (int i = 0; i < x2; i++) {
        int monkeyID = i + 1;

        Random monkeyxrand = new Random();
        Random monkeyyrand = new Random();
        int monkeyxPos = monkeyxrand.nextInt(100) + 1;
        int monkeyyPos = monkeyyrand.nextInt(100) + 1;

        JSONObject mymonkeyJSON = new JSONObject();
        mymonkeyJSON.put("ID", monkeyID);
        mymonkeyJSON.put("X Position", monkeyxPos);
        mymonkeyJSON.put("Y Position", monkeyyPos);
        monkeyJSONArray.add(mymonkeyJSON);
      }

      JSONObject mainObj = new JSONObject();
      JSONObject generalattributes = new JSONObject();
      mainObj.put("Algorithm", s1);
      generalattributes.put("Talk Range", f1);
      generalattributes.put("Shoot Range", f2);
      mainObj.put("Monkey General Attributes", generalattributes);
      mainObj.put("Aliens", alienJSONArray);
      mainObj.put("Monkeys", monkeyJSONArray);

      try {
        FileWriter myWriter = new FileWriter("config3.json");
        // NOTE: comments cannot be included in JSON
        myWriter.write(mainObj.toJSONString());
        myWriter.close();
        System.out.println("Successfully wrote to the file.");

      } catch (IOException err) {
        System.out.println("An error occurred.");
        err.printStackTrace();
      }

    }
  }
}
