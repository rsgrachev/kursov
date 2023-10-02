package kurs.Table;

import kurs.model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTable extends AbstractTableModel {
    private List<Student> data;
    public StudentTable(List<Student> students){
        data = students;

    }
    private String budget(boolean flag){
        String res = "Бюджет";
        if(flag == false){
            res = "Коммерция";
        }
        return res;
    }
    private String debts(boolean flag){
        String res = "-";
        if(flag == false){
            res = "+";
        }
        return res;
    }
    private String offset(Student student){
        String res = "-";
        boolean flag = student.getOffset();
        if(flag == true){
            res = "+";
        } else if (flag == false && student.getMarks() == 0){
            res = "";
        }
        return res;
    }
    private String marks(int num){
        String res = "";
        if (num != 0){
            res = String.valueOf(num);
        }
        return res;
    }


    @Override
    public int getRowCount() {return data.size();}


    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student st = data.get(rowIndex);
        switch (columnIndex){
            case 0: return st.getFirstName();
            case 1: return st.getSecondName();
            case 2: return st.getPatronymic();
            case 3: return st.getGroup().getName();
            case 4: return budget(st.isBudget());
            case 5: return debts(st.isStudyDebts());
            case 6: return offset(st);
            case 7: return marks(st.getMarks());
        }
        return "";
    }

    public String getColumnName(int column) {
        switch (column){
            case 0: return "Фамилия";
            case 1: return "Имя";
            case 2: return "Отчество";
            case 3: return  "Группа";
            case 4: return "Бюджет/Коммерция";
            case 5: return  "Наличие долгов";
            case 6: return "Сданы все зачёты";
            case 7: return "Оценки";
        }
        return "";
    }
    public Student getStudent(int selectedRow) {
        return data.get(selectedRow);
    }
}
