package kurs.GUI;

import javax.swing.*;
import java.awt.*;

public class WindowAdd {
    private JFrame frame;

    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();
    // private JPanel panelBtnNorth = new JPanel();

    private  JLabel inputName = new JLabel("Добавление студента:", SwingConstants.CENTER);
    //private  JLabel FIO;
    private JLabel surname = new JLabel("  Фамилия");
    private JLabel names = new JLabel("  Имя");
    private JLabel patronymic = new JLabel("  Отчество");
    private JLabel group = new JLabel("  Группа");
    private JLabel budget = new JLabel("  Вид обучения");
    private JLabel dolgi = new JLabel("  Есть ли долги");

    private String[] mas = {"Бюджет", "Комерция"};
    private JComboBox budgetField = new JComboBox(mas);
    private String[] mas1 = {"Долги есть", "Долгов нет"};
    private JComboBox dolgiField = new JComboBox(mas1);
    private JComboBox groupField;
    private JTextField surnameField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField patronymicField = new JTextField();

    private JButton saveBtn = new JButton("Далее");

    public WindowAdd(Controller.MyKeyListener myKeyListener, Controller.Listener listener, String[] groups){

        frame = new JFrame("Добавление");
        frame.setSize(403,433);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        groupField = new JComboBox(groups);

        frame.setLayout(new BorderLayout(15,15));
        panelNorth.setLayout(new GridLayout(1,1,15,15));

        panelNorth.add(inputName);

        panelCenter.setLayout(new GridLayout(6,2,15,15));
        panelCenter.add(surname);
        panelCenter.add(surnameField);
        panelCenter.add(names);
        panelCenter.add(nameField);
        panelCenter.add(patronymic);
        panelCenter.add(patronymicField);
        panelCenter.add(group);
        panelCenter.add(groupField);
        panelCenter.add(budget);
        panelCenter.add(budgetField);
        panelCenter.add(dolgi);
        panelCenter.add(dolgiField);

        panelSouth.setLayout(new BorderLayout(15,15));
        panelSouth.add(saveBtn, BorderLayout.CENTER);

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth, BorderLayout.SOUTH);

        saveBtn.addActionListener(listener);

        surnameField.addKeyListener(myKeyListener);
        nameField.addKeyListener(myKeyListener);
        patronymicField.addKeyListener(myKeyListener);

        surnameField.setName("Text");
        nameField.setName("Text");
        patronymicField.setName("Text");

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
        budget.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        budgetField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        dolgi.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        dolgiField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));

    }
    protected boolean isFill (){
        boolean flag = true;
        if(surnameField.getText().equals("") || nameField.getText().equals("") || patronymicField.getText().equals("") ){
            flag = false;
        }
        return flag;
    }
    public void visibleOff(){
        frame.setVisible(false);
    }
    public String getSurname(){
        return surnameField.getText();
    }

    public String getName(){
        return nameField.getText();
    }
    public String getPatronymic(){
        return patronymicField.getText();
    }
    public String getGroup(){return (String)groupField.getSelectedItem();   }
    public String getBudget(){return (String) budgetField.getSelectedItem();}
    public String getDolgi(){return (String) dolgiField.getSelectedItem();}
}
