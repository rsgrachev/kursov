package kurs.GUI;

import javax.swing.*;
import java.awt.*;

public class WindowExam extends JFrame {
    private  JFrame frame;

    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();

    private  JLabel inputName = new JLabel("Введите успеваемость студента:", SwingConstants.CENTER);
    private  JLabel FIO;



    private String[] mas2 = {"2", "3", "4", "5"};

    private JButton saveBtn = new JButton("Сохранить экзамены");
    private int countExams;


    public WindowExam(Controller.Listener listener, String name, int countExam){
        this.countExams = countExam;
        FIO = new JLabel(name,SwingConstants.CENTER);
        frame = new JFrame("Ввод данных экзамена");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 430) / 2, (screenSize.height - 500) / 2, 430, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        saveBtn.addActionListener(listener);
        Font labelFont = inputName.getFont();

        frame.setLayout(new BorderLayout(15,15));
        panelNorth.setLayout(new GridLayout(2,1,15,15));
        panelNorth.add(inputName);
        panelNorth.add(FIO);


        panelCenter.setLayout(new GridLayout(countExam, 2,15,15));
        for(int i = 0; i < countExam; i++){
            JLabel label = new JLabel("    Оценка за экзамен " + String.valueOf(i+1));
            panelCenter.add(label);
            JComboBox comboBox = new JComboBox(mas2);
            panelCenter.add(comboBox);
            comboBox.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 15));

        }


//        panelCenter.setLayout(new GridLayout(5,2,15,15));
//
//
//
//        panelCenter.add(examMarks1);
//        panelCenter.add(examMarksField1);
//        panelCenter.add(examMarks2);
//        panelCenter.add(examMarksField2);
//        panelCenter.add(examMarks3);
//        panelCenter.add(examMarksField3);
//        panelCenter.add(examMarks4);
//        panelCenter.add(examMarksField4);
//        panelCenter.add(examMarks5);
//        panelCenter.add(examMarksField5);

        panelSouth.setLayout(new GridLayout(1,1,15,15));
        panelSouth.add(saveBtn);

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth, BorderLayout.SOUTH);


        inputName.setFont(new Font(labelFont.getName(), Font.BOLD, 22));
        FIO.setFont(new Font(labelFont.getName(), Font.PLAIN, 22));
        saveBtn.setFont(new Font(labelFont.getName(), Font.BOLD, 15));


    }

    protected String getExamsMarrs(){
        String result = "";
        Component[] components = panelCenter.getComponents();
        for(int i = 1; i < components.length; i=i+2){
            JComboBox comboBox = (JComboBox) components[i];
            result = result + (String) comboBox.getSelectedItem();
        }
        return result;
    }

    protected void visibleOff(){
        frame.setVisible(false);
    }

}
