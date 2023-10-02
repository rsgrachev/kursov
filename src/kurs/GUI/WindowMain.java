package kurs.GUI;

import javax.swing.*;
import java.awt.*;

public class WindowMain extends JFrame {
    private JButton startButton;
    private JButton studentListButton;
    private JButton infoButton;

    private JFrame frame;
    private JPanel panel = new JPanel();
public WindowMain(String s, Controller.Listener listener){
    frame = new JFrame(s);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setBounds((screenSize.width - 350) / 2, (screenSize.height - 200) / 2, 350, 200);
    frame.setLayout(new GridLayout(1,1));
    panel.setLayout(new GridLayout(3, 1));

    startButton = new JButton("Внести успеваемость");
    studentListButton = new JButton("Добавить студента");
    infoButton = new JButton("Расчет стипендии");
    frame.add(panel);

    panel.add(startButton);
    panel.add(studentListButton);
    panel.add(infoButton);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    startButton.addActionListener(listener);
    studentListButton.addActionListener(listener);
    infoButton.addActionListener(listener);
    }
    protected void visibleoff(){
        frame.setVisible(false);
    }
}

