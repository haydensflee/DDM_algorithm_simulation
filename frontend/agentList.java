
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class agentList extends JPanel {
    private JList list;
    private DefaultListModel listModel;

    public DefaultListModel getListModel() {
        return listModel;
    }

    public JList getList() {
        return list;
    }

    public agentList() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);

        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        setPreferredSize(new Dimension(200, 480));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder("Agent List"));

        this.add(listScrollPane);

    }
}