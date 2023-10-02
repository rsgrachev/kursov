package kurs.GUI;

import kurs.Table.StudentTable;
import kurs.model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WindowList extends JFrame {
    private JButton deleteBtn = new JButton("Удалить студента");
    private JButton changeBtn = new JButton("Изменить данные о студенте");
    private JButton addBtn = new JButton("Внести успеваемость студента");
    private JFrame frame;
    private JPanel panel = new JPanel();
    private JPanel panelBtn = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JTable model = new JTable();
    private JScrollPane jScrollPane;
    private StudentTable studentModel;
    private JLabel nameLabel;


    public WindowList(List<Student> student,String name, Controller.Listener listener){
        frame = new JFrame("Данные");
        frame.setDefaultCloseOperation(WindowList.DISPOSE_ON_CLOSE);
        frame.setSize(900,450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        nameLabel = new JLabel(name,SwingConstants.CENTER);
        Font labelFont = nameLabel.getFont();
        nameLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 24));

        studentModel = new StudentTable(student);
        model.setModel(studentModel);
        jScrollPane = new JScrollPane(model);

        panel.setLayout(new GridLayout(1,1,15,15));
        panel.add(nameLabel);

        panelCenter.setLayout(new GridLayout(1,1,15,15));
        panelCenter.add(jScrollPane);

        panelBtn.setLayout(new GridLayout(1,3,15,15));

        panelBtn.add(addBtn);
        panelBtn.add(changeBtn);
        panelBtn.add(deleteBtn);

        frame.setLayout(new BorderLayout(15,15));
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panelBtn, BorderLayout.SOUTH);
        frame.add(panelCenter, BorderLayout.CENTER);

        addBtn.addActionListener(listener);
        changeBtn.addActionListener(listener);
        deleteBtn.addActionListener(listener);

        model.getTableHeader().setFont(new Font("Times New Roman" , Font.BOLD, 16));
        model.setFont(new Font("Times New Roman" , Font.PLAIN, 14));
        model.getColumnModel().getColumn(3).setPreferredWidth(30);
        model.getColumnModel().getColumn(7).setPreferredWidth(40);


        deleteBtn.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        changeBtn.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        addBtn.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
    }
    protected void Visible(){
        frame.setVisible(false);
        frame.setVisible(true);
    }
    protected Student getStudent(){
        Student student = studentModel.getStudent(model.getSelectedRow());
        return student;
    }

}
