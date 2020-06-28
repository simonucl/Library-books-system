import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame;
    JLabel label;
    JButton button;
    JPanel panel;
    public GUI(){
        frame = new JFrame();
        label = new JLabel();
        button = new JButton("FUCK ME");
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}

