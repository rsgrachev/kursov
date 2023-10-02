package kurs.Table;

import kurs.model.Fond;
import kurs.model.Group;
import kurs.model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FondTable extends AbstractTableModel {
    private List<Fond> data;
    public FondTable(List<Fond> fonds) {data = fonds;}

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fond fond = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return fond.getStudent().getId();
            case 1:
                return fond.getStudent().getFirstName();
            case 2:
                return fond.getStudent().getSecondName();
            case 3:
                return fond.getStudent().getPatronymic();
            case 4:
                return fond.getStudent().getGroup().getName();
            case 5:
                return fond.getSum();

        }
        return "";
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id студента";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Отчество";
            case 4:
                return "Группа";
            case 5:
                return "Сумма стипендии";

        }
        return "";
    }

}
