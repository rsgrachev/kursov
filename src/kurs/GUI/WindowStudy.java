package kurs.GUI;

import javax.swing.*;
import java.awt.*;

public class WindowStudy {
    private  JFrame frame;

    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();
    private JPanel panelBtnNorth = new JPanel();

    private  JLabel inputName = new JLabel("Введите успеваемость студента:", SwingConstants.CENTER);
    private  JLabel FIO;
    private JLabel offset = new JLabel("  Сданы ли все зачеты");
//    private JLabel examMarks = new JLabel("  Оценки за экзамены");
  private JLabel kursMarks = new JLabel("  Оценка за курc. работу");
   private JLabel diffZachetMarks = new JLabel("  Оценка за диф.зачет");

    private String[] mas = {"Сданы", "Не сданы"};
    private JComboBox offsetField = new JComboBox(mas);
    private String[] mas2 = {"2", "3", "4", "5"};
    private JComboBox kursMarksField = new JComboBox(mas2);
    private JComboBox diffZachetMarksField = new JComboBox(mas2);
//    private JTextField examMarksField = new JTextField();
// private JTextField kursMarksField = new JTextField("0");
//    private JTextField diffZachetMarksField = new JTextField("0");

    private JButton saveBtn = new JButton("Продолжить");
  //  private JButton infoBtn = new JButton("Правила ввода");

    public WindowStudy(Controller.Listener listener, String name){
        FIO = new JLabel(name,SwingConstants.CENTER);
        frame = new JFrame("Ввод данных");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 430) / 2, (screenSize.height - 330) / 2, 430, 330);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        frame.setLayout(new BorderLayout(15,15));
        panelNorth.setLayout(new GridLayout(2,1,15,15));


        panelBtnNorth.setLayout(new BorderLayout(15,15));
        //panelBtnNorth.add(infoBtn, BorderLayout.EAST);

        panelNorth.add(inputName);
        panelNorth.add(FIO);

        panelCenter.setLayout(new GridLayout(3,2,15,15));
        panelCenter.add(offset);
        panelCenter.add(offsetField);
//        panelCenter.add(examMarks);
//        panelCenter.add(examMarksField);
        panelCenter.add(kursMarks);
        panelCenter.add(kursMarksField);
      panelCenter.add(diffZachetMarks);
        panelCenter.add(diffZachetMarksField);

        panelSouth.setLayout(new BorderLayout(15,15));
        panelSouth.add(saveBtn, BorderLayout.CENTER);

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth, BorderLayout.SOUTH);

        saveBtn.addActionListener(listener);
//        infoBtn.addActionListener(listener);
//
//        examMarksField.addKeyListener(myKeyListener);
//        kursMarksField.addKeyListener(myKeyListener);
//        diffZachetMarksField.addKeyListener(myKeyListener);

        Font labelFont = inputName.getFont();
        inputName.setFont(new Font(labelFont.getName(), Font.BOLD, 22));
        FIO.setFont(new Font(labelFont.getName(), Font.PLAIN, 22));
        offset.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        offsetField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        kursMarksField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        diffZachetMarksField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
//     examMarksField.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
//        examMarks.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
     kursMarks.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        diffZachetMarks.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        saveBtn.setFont(new Font(labelFont.getName(), Font.BOLD, 15));

//        examMarksField.setName("Exam");
//        kursMarksField.setName("Kurs");
//        diffZachetMarksField.setName("Diff");

    }
//    protected boolean isFill (){
//        boolean flag = true;
//        if(examMarksField.getText().equals("") || kursMarksField.getText().equals("") || diffZachetMarksField.getText().equals("") ){
//            flag = false;
//        }
//        return flag;
//    }
//    public String getExamMarks(){
//        return examMarksField.getText();
//    }
//    public String getKursMarks(){
//        return kursMarksField.getText();
//    }
//    public String getDiffZachetMarks(){
//        return diffZachetMarksField.getText();
//    }

    protected String getOffset(){
        return (String) offsetField.getSelectedItem();
    }
    protected String getKursMarks(){
       return (String) kursMarksField.getSelectedItem();
    }
    protected String getDiffZachetMarks(){
        return (String) diffZachetMarksField.getSelectedItem();
    }
    protected void visibleOff(){
        frame.setVisible(false);
    }

}
