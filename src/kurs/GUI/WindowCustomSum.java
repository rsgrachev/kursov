package kurs.GUI;

import javax.swing.*;
import java.awt.*;

public class WindowCustomSum extends JFrame {
    private JFrame frame;
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel("Введите сумму стипендиального фонда:",SwingConstants.CENTER);
    private JButton button = new JButton("Сохранить сумму");
    private JTextField textField = new JTextField();

    public WindowCustomSum(Controller.Listener listener, Controller.MyKeyListener keyListener){
        frame = new JFrame("Размер стипендиального фонда");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 400) / 2, (screenSize.height - 200) / 2, 400, 200);
        frame.setLayout(new GridLayout(1,1));
        frame.setLayout(new GridLayout(1,1));
        panel.setLayout(new GridLayout(3, 1,15,15));
        frame.add(panel);

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        textField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        button.setFont(new Font(labelFont.getName(), Font.BOLD, 12));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        textField.setName("number");
        textField.addKeyListener(keyListener);
        button.addActionListener(listener);
    }
    protected int getSum(){
        return Integer.parseInt(textField.getText());
    }
    protected void isVisiable(){
        frame.setVisible(false);
    }
    protected boolean checkField(){
        boolean flag = true;
        if(textField.getText().equals("")){
            flag = false;
        }
        return flag;
    }
}
