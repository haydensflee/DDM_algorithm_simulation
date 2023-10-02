
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class agentProperties extends JPanel {

    private final ButtonGroup group = new ButtonGroup();
    private final JRadioButton cowboyButton = new JRadioButton("cowboy");
    private final JRadioButton alienButton = new JRadioButton("Alien");
    private final JRadioButton targetButton = new JRadioButton("Target (placeholder)");

    private final JTextField xTextField = new JTextField(3);
    private final JTextField yTextField = new JTextField(3);

    private final JButton addButton = new JButton("ADD");
    private final JButton editButton = new JButton("EDIT (placeholder)");
    private final JButton deleteButton = new JButton("DELETE");

    JLabel talkSliderLabel = new JLabel("Cowboy Talk Range (m)");
    JLabel shootSliderLabel = new JLabel("Cowboy ShootRange (m)");
    JLabel emptyLabel = new JLabel("-");

    private final JSlider talkSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
    private final JSlider shootSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);

    public JRadioButton getCowboyRadio() {
        return cowboyButton;
    }

    public JRadioButton getAlienRadio() {
        return alienButton;
    }

    public JTextField getXTextField() {
        return xTextField;
    }

    public JTextField getYTextField() {
        return yTextField;
    }

    public JSlider getTalkSlider() {
        return talkSlider;
    }

    public JSlider getShootSlider() {
        return shootSlider;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setAgentRadio(String agent) {
        if (agent == "cowboy") {
            cowboyButton.setSelected(true);
        } else if (agent == "alien") {
            alienButton.setSelected(true);
        } else if (agent == "target") {
            targetButton.setSelected(true);
        }
    }

    public void setSliderVisibility(Boolean visible) {
        talkSliderLabel.setVisible(visible);
        shootSliderLabel.setVisible(visible);
        talkSlider.setVisible(visible);
        shootSlider.setVisible(visible);
        emptyLabel.setVisible(!visible);
    }

    public agentProperties() {
        // panel properties
        setPreferredSize(new Dimension(200, 480));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder("Agent Properties"));

        // agent radio buttons

        cowboyButton.setName("cowboyButton");
        alienButton.setName("alienButton");
        cowboyButton.setSelected(true);
        group.add(cowboyButton);
        group.add(alienButton);
        group.add(targetButton);

        this.add(cowboyButton);
        this.add(alienButton);
        this.add(targetButton);

        // xy textboxes
        JLabel xLabel = new JLabel("X:");
        JLabel yLabel = new JLabel("Y:");
        xTextField.setName("xTextField");
        yTextField.setName("yTextField");
        this.add(xLabel);
        this.add(xTextField);
        this.add(yLabel);
        this.add(yTextField);

        // range sliders
        emptyLabel.setPreferredSize(new Dimension(50, 110));
        this.add(emptyLabel);
        emptyLabel.setVisible(false);

        talkSlider.setName("talkSlider");
        talkSlider.setValue(25);
        talkSlider.setPaintTicks(true);
        talkSlider.setMinorTickSpacing(10);
        talkSlider.setPaintTrack(true);
        talkSlider.setMajorTickSpacing(25);
        talkSlider.setPaintLabels(true);
        shootSlider.setName("shootSlider");
        shootSlider.setValue(25);
        shootSlider.setPaintTicks(true);
        shootSlider.setMinorTickSpacing(10);
        shootSlider.setPaintTrack(true);
        shootSlider.setMajorTickSpacing(25);
        shootSlider.setPaintLabels(true);

        this.add(talkSliderLabel);
        this.add(talkSlider);
        this.add(shootSliderLabel);
        this.add(shootSlider);

        // JPanel empty = new JPanel();
        // empty.setSize(100, 100);
        // this.add(empty);

        // buttons

        addButton.setName("addButton");
        editButton.setName("editButton");
        deleteButton.setName("deleteButton");
        this.add(addButton);
        this.add(editButton);
        this.add(deleteButton);

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changEvent) {
                AbstractButton aButton = (AbstractButton) changEvent.getSource();
                ButtonModel aModel = aButton.getModel();
                boolean armed = aModel.isArmed();
                boolean pressed = aModel.isPressed();
                boolean selected = aModel.isSelected();
                // System.out.println("Changed: " + armed + "/" + pressed + "/" +
                // selected);
                if (aButton.getText() == "cowboy" && selected) {
                    emptyLabel.setVisible(false);
                    setSliderVisibility(true);
                    talkSliderLabel.setText("Cowboy Talk Range (m)");
                    shootSliderLabel.setText("Cowboy Shoot Range (m)");
                } else if (aButton.getText() == "Alien" && selected) {
                    emptyLabel.setVisible(false);
                    setSliderVisibility(true);
                    talkSliderLabel.setText("Alien Vision Range (m)");
                    shootSliderLabel.setText("Alien Movement Speed (m/s)");
                } else if (aButton.getText() == "Target (placeholder)" && selected) {
                    emptyLabel.setVisible(true);
                    setSliderVisibility(false);
                }
            }
        };

        alienButton.addChangeListener(changeListener);
        cowboyButton.addChangeListener(changeListener);
        targetButton.addChangeListener(changeListener);

    }

}