package kurs.GUI;

import kurs.model.Student;

import javax.swing.*;
import java.awt.*;

public class WindowChange {
    private JFrame frame;

    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();
   // private JPanel panelBtnNorth = new JPanel();

    private  JLabel inputName = new JLabel("Изменение данных студента:", SwingConstants.CENTER);
    //private  JLabel FIO;
    private JLabel surname = new JLabel("Фамилия");
    private JLabel names = new JLabel("Имя");
    private JLabel patronymic = new JLabel("Отчество");
    private JLabel group = new JLabel("Группа");

    private JComboBox groupField;
    private JTextField surnameField;
    private JTextField nameField;
    private JTextField patronymicField;

    private JButton saveBtn = new JButton("Сохранить новые данные");

    private String[] m;

    public WindowChange(Controller.MyKeyListener myKeyListener, Controller.Listener listener, Student student, String[] mas){
        frame = new JFrame("Изменение данных");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 400) / 2, (screenSize.height - 380) / 2, 400, 380);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        surnameField = new JTextField(student.getFirstName());
        nameField = new JTextField(student.getSecondName());
        patronymicField = new JTextField(student.getPatronymic());
        groupField = new JComboBox(mas);
        m = mas;
        groupField.setSelectedIndex(getGroup(student));    // поставить нужную группу
        System.out.println(getGroup(student));
        frame.setLayout(new BorderLayout(15,15));
        panelNorth.setLayout(new GridLayout(1,1,15,15));

        panelNorth.add(inputName);

        panelCenter.setLayout(new GridLayout(4,2,15,15));
        panelCenter.add(surname);
        panelCenter.add(surnameField);
        panelCenter.add(names);
        panelCenter.add(nameField);
        panelCenter.add(patronymic);
        panelCenter.add(patronymicField);
        panelCenter.add(group);
        panelCenter.add(groupField);

        panelSouth.setLayout(new BorderLayout(15,15));
        panelSouth.add(saveBtn, BorderLayout.CENTER);

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth, BorderLayout.SOUTH);

        saveBtn.addActionListener(listener);

        surnameField.addKeyListener(myKeyListener);
        nameField.addKeyListener(myKeyListener);
        patronymicField.addKeyListener(myKeyListener);

        Font labelFont = inputName.getFont();
        inputName.setFont(new Font(labelFont.getName(), Font.BOLD, 22));
        surname.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        surnameField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        nameField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        names.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        patronymic.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        patronymicField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        group.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        groupField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        saveBtn.setFont(new Font(labelFont.getName(), Font.BOLD, 15));

        surnameField.setName("Text");
        nameField.setName("Text");
        patronymicField.setName("Text");
    }
    protected boolean isFill (){
        boolean flag = true;
        if(surnameField.getText().equals("") || nameField.getText().equals("") || patronymicField.getText().equals("") ){
            flag = false;
        }
        return flag;
    }
    private int getGroup(Student student){
        int index = 0;
        for (int i = 0; i < m.length; i++) {
            if (student.getGroup().getName().equals(m[i])){
                index = i;
            }
        }
        return index;
    }
    protected void visibleOff(){
        frame.setVisible(false);
    }
    protected String getSurname(){
        return surnameField.getText();
    }

    protected String getName(){
        return nameField.getText();
    }
    protected String getPatronymic(){
        return patronymicField.getText();
    }
    protected String getGroup(){
        return (String)groupField.getSelectedItem();
    }

}

