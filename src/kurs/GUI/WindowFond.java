package kurs.GUI;

import kurs.Table.FondTable;
import kurs.model.Fond;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WindowFond {
    private JFrame frame;

    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();

    private JTable model = new JTable();

    private JScrollPane jScrollPane;
    private FondTable fondTable;

    private JLabel sumLabel;
    private JLabel customSum;
    private JLabel remainSum;
    private int sum = 0;
    private int remainsSum = 0;
    private JButton setCustomSumBtn = new JButton("Задать стипендиальный фонд");
    private int sumDefaults = 0;


    public WindowFond(int sumDefault, List<Fond> fond, Controller.Listener listener){
        frame = new JFrame("Расчет стипендии");
        frame.setSize(1060,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        this.sumDefaults = sumDefault;
        sumLabel = new JLabel("Общая сумма выплат: " + String.valueOf(sumDefault));
        customSum = new JLabel("Стипендиальный фонд:  " + String.valueOf(sum));
        remainSum = new JLabel("Остаток:  " + String.valueOf(remainsSum));
        Font labelFont = sumLabel.getFont();
        sumLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        customSum.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        remainSum.setFont(new Font(labelFont.getName(), Font.BOLD, 20));

        fondTable = new FondTable(fond);
        model.setModel(fondTable);
        jScrollPane = new JScrollPane(model);


        panelNorth.setLayout(new GridLayout(1,3,15,15));
        panelNorth.add(sumLabel);
        panelNorth.add(customSum);
        panelNorth.add(remainSum);

        panelCenter.setLayout(new GridLayout(1,1,15,15));
        panelCenter.add(jScrollPane);

        panelSouth.setLayout(new GridLayout(1,1,15,15));
        panelSouth.add(setCustomSumBtn);

        setCustomSumBtn.addActionListener(listener);
        frame.setLayout(new BorderLayout(15,15));
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth, BorderLayout.SOUTH);

        model.getTableHeader().setFont(new Font("Times New Roman" , Font.BOLD, 16));
        model.setFont(new Font("Times New Roman" , Font.PLAIN, 14));
        model.getColumnModel().getColumn(0).setPreferredWidth(20);
        model.getColumnModel().getColumn(4).setPreferredWidth(30);
        setCustomSumBtn.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
    }
    protected void setSum(int sum){
        this.sum = sum;
    }

    protected void visibleOff(){
        frame.setVisible(false);
        customSum = new JLabel("Стипендиальный фонд =  " + String.valueOf(sum));

        remainsSum = sum - sumDefaults;
        remainSum = new JLabel("Остаток:  " + String.valueOf(remainsSum));
        panelNorth.removeAll();
        panelNorth.setLayout(new GridLayout(1,3,15,15));
        panelNorth.add(sumLabel);
        panelNorth.add(customSum);
        panelNorth.add(remainSum);
        frame.setVisible(true);

        Font labelFont = sumLabel.getFont();
        sumLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        customSum.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        remainSum.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
    }
}
