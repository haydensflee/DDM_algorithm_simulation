package archive;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FileA {
    public FileA() {
        JFrame jf = new JFrame();
        jf.setLayout(new FlowLayout());

        FileB b = new FileB();
        FileC c = new FileC();
        FileD d = new FileD();

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(100, 100, 1000, 600);
        jf.setVisible(true);

        jf.add(d);
        jf.add(c);
        jf.add(b);

    }

    public static void main(String[] args) {
        new FileA();
    }
}