package kurs.GUI;

import kurs.model.Fond;
import kurs.model.Group;
import kurs.model.Student;

import kurs.DBWorker;
import kurs.model.SummaFond;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Controller {
    private Listener listener = new Listener();

    private WindowMain main;
    private WindowList windowList;
    private WindowStudy windowStudy;
    private WindowChange windowChange;
    private WindowAdd windowAdd;
    private WindowFond windowFond;
    private WindowExam windowExam;

    private MyKeyListener keyListener = new MyKeyListener();

    private List<Group> groupList = new ArrayList<>();
    private  List<Student> studentList = new ArrayList<>();
    private List<Student> lowScholarship = new ArrayList<>();
    private List<Student> scholarship = new ArrayList<>();
    private List<Student> highScholarship = new ArrayList<>();
    private SummaFond summaFond;

    private List<Fond> fondList = new ArrayList<>();
    private int countExam = 0;
    private int kurs;
    private int difzach;
    private int countKurs;
    private int countDifZhachet;
    private WindowCustomSum windowCustomSum;

    public Controller(String s) {
        main = new WindowMain(s, listener);
        DBWorker.initDB();
        try {
            groupList = DBWorker.getAllGroups();
            studentList = DBWorker.getAllStudents();
            summaFond = DBWorker.getSummaFond();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    private List<Student> studentsGroup(String group){
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++){
           // System.out.println(studentList.get(i).getGroup());
            if(studentList.get(i).getGroup().getName().equals(group)){
                list.add(studentList.get(i));
            }
        }
        return list;
    }
    private String[] getGroups(){
        String[] mas = new String[groupList.size()];
        for (int i = 0; i < groupList.size(); i++){
            mas[i] = groupList.get(i).getName();
        }
        return mas;
    }
    private void errorLine(){
        JOptionPane.showMessageDialog(null, "Вы не выбрали запись!", "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }
    private String groupChoose(){
        String[] mas = getGroups();
        String res = mas[0];
            res = (String) JOptionPane.showInputDialog(null, "Выберите группу", "Выбор группы", JOptionPane.QUESTION_MESSAGE, null, mas, mas[0]);
        return res;
    }
    private String examChoose(){
        String[] mas = {"1", "2", "3", "4","5"};
        String res = mas[0];
        res = (String) JOptionPane.showInputDialog(null, "Выберите количество экзаменов:",
                "Выбор экзаменов", JOptionPane.QUESTION_MESSAGE, null, mas, mas[0]);
        return res;
    }
    private void notFilled(){
        JOptionPane.showMessageDialog(null, "Заполните все поля!", "Ошибка", JOptionPane.ERROR_MESSAGE, null);
    }

    private void deleteStudent(){
        JOptionPane.showMessageDialog(null, "Вы успешно удалили студента!", "Удаление",
                JOptionPane.INFORMATION_MESSAGE);
    }
    private void updateStudent(){
        JOptionPane.showMessageDialog(null, "Вы успешно обновили данные!", "Изменение",
                JOptionPane.INFORMATION_MESSAGE);
    }
    private void addStudent(){
        JOptionPane.showMessageDialog(null, "Вы успешно добавили студента!", "Добавление",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkFond(){
        JOptionPane.showMessageDialog(null, "Вы не заполнили успеваемость студентов. Заполните поля успеваемости!", "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }
    private void getSum(){
        JOptionPane.showMessageDialog(null, "Размер стипендального фонда недостаточен!", "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }

    private Group getGroup(String name){
        Group group = new Group();
        for (int i = 0; i< groupList.size(); i++){
            if (groupList.get(i).getName().equals(name)) {
                group = groupList.get(i);
                }
        }
        return group;
    }

    private boolean isOffset(String s){
        boolean flag = true;
        if (s.equals("Не сданы")){
            flag = false;
        }
        return flag;
    }
    private boolean isBudget(String s){
        boolean flag = true;
        if (s.equals("Комерция")){
            flag = false;
        }
        return flag;
    }
    private boolean isDolgi(String s){
        boolean flag = true;
        if (s.equals("Долги есть")){
            flag = false;
        }
        return flag;
    }
    private boolean isCalcFond(){
        boolean flag = true;
        for (int i=0; i<studentList.size();i++){
            if (studentList.get(i).getMarks() == 0){
                flag = false;
            }
        }
        return flag;
    }

    private boolean checkNumFond(Student student){
        boolean flag = true;
        String temp = Integer.toString(student.getMarks());
        int[] newGuess = new int[temp.length()];

        for (int i = 0; i < temp.length(); i++) {
            newGuess[i] = temp.charAt(i) - '0';
        }
        for (int i = 0; i < newGuess.length; i++){
            if (newGuess[i] == 3 || newGuess[i] == 2 || !student.isBudget() ||
                    !student.isOffset() || !student.isStudyDebts()){
                flag = false;
            }
        }
        return flag;
    }
    private void calcFond(){
        lowScholarship = new ArrayList<>();
        scholarship = new ArrayList<>();
        highScholarship = new ArrayList<>();

        boolean flag4 = false;
        boolean flag5 = false;
        int foursCount = 0;
        int fivesCount = 0;


        List<Student> fond = new ArrayList<>();
        for (int i = 0; i<studentList.size(); i++){
            if (checkNumFond(studentList.get(i))){
                fond.add(studentList.get(i));
            }
        }

        List<int []> marksList = new ArrayList<>();
        for (int i = 0; i < fond.size(); i++){
            String temp = Integer.toString(fond.get(i).getMarks());
            int[] newGuess = new int[temp.length()];

            for (int k = 0; k < temp.length(); k++) {
                newGuess[k] = temp.charAt(k) - '0';
            }
            marksList.add(newGuess);
        }
        for (int i = 0; i < fond.size(); i++){
            foursCount = 0;
            fivesCount = 0;
            flag4 = false;
            flag5 = false;

            for (int k = 0; k < marksList.get(i).length; k++){

                if (marksList.get(i)[k] == 4){
                    foursCount++;
                }else {
                    fivesCount++;
                }
            }

            if (foursCount == marksList.get(i).length){
                flag4 = true;
            }
            if (fivesCount == marksList.get(i).length){
                flag5 = true;
            }
            if (flag4 == true){
                lowScholarship.add(fond.get(i));
            }
            else if (flag5 == true){
                highScholarship.add(fond.get(i));
            }
            else {
                scholarship.add(fond.get(i));
            }
        }

    }

    private void addDBWorker(){
        try {

            DBWorker.deleteFondData();

            for (int i = 0; i < highScholarship.size(); i++){
                DBWorker.addHighScholarship(highScholarship.get(i));
            }
            for (int i = 0; i < scholarship.size(); i++){
                DBWorker.addScholarship(scholarship.get(i));
            }
            for (int i = 0; i < lowScholarship.size(); i++){
                DBWorker.addLowScholarship(lowScholarship.get(i));
            }
            fondList = DBWorker.getAllFonds();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int sum(){
        int sum = 4000 * lowScholarship.size() + 5000 * scholarship.size() + 6000 * highScholarship.size();
        return sum;
    }



    public class Listener implements ActionListener {
        private String result = "";
        int sumCustom = 0;
        public Listener() {
        }


        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("Внести успеваемость")) {
                result = groupChoose();

                if (result != null){
                    String a = examChoose();
                    if(a != null){
                        countExam = Integer.parseInt(a);
                        windowList = new WindowList(studentsGroup(result), result, listener);
                    }
                }

            }
            if (e.getActionCommand().equals("Далее")) {
                if (windowAdd.isFill()){
                    try {


                        Student student = new Student(studentList.size()+1 ,windowAdd.getSurname(),windowAdd.getName(),
                                windowAdd.getPatronymic(),getGroup(windowAdd.getGroup()),isBudget(windowAdd.getBudget()),
                                isDolgi(windowAdd.getDolgi()),false,0 );
                        DBWorker.addStudent(student);
                        windowAdd.visibleOff();
                        studentList = DBWorker.getAllStudents();
                        addStudent();
                        windowAdd.visibleOff();

                    } catch (IndexOutOfBoundsException ex){
                        errorLine();
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    notFilled();
                }
            }
            if (e.getActionCommand().equals("Добавить студента")) {
                windowAdd = new WindowAdd(keyListener, listener, getGroups());
            }
            if (e.getActionCommand().equals("Внести успеваемость студента")){
                try {
                    Student student = windowList.getStudent();
                    windowStudy = new WindowStudy(listener, student.getFIO());
                } catch (IndexOutOfBoundsException ex){
                    errorLine();
                }
            }

            if (e.getActionCommand().equals("Продолжить")) {
                try {
                    Student student = windowList.getStudent();
                    student.setOffset(isOffset(windowStudy.getOffset()));
                    kurs = Integer.parseInt(windowStudy.getKursMarks());
                    difzach = Integer.parseInt(windowStudy.getDiffZachetMarks());
                    windowStudy.visibleOff();
                    windowExam = new WindowExam(listener, student.getFIO(), countExam);
                } catch (IndexOutOfBoundsException ex){
                    errorLine();
                }
            }
            if (e.getActionCommand().equals("Расчет стипендии")) {
                if (!isCalcFond()){
                    checkFond();
                }else {
                    calcFond();
                    int sumDefault = sum();
                    addDBWorker();
                    sumCustom = summaFond.getSumma();
                        windowFond = new WindowFond(sumDefault, fondList, listener);
                        summaFond.setSumma(sumCustom);
                    try {
                        DBWorker.updateSummaFond(summaFond);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    windowFond.setSum(sumCustom);
                        windowFond.visibleOff();
//                    }
                }
            }
            if (e.getActionCommand().equals("Сохранить новые данные")){
                if (windowChange.isFill()){
                    try {
                        Student student = windowList.getStudent();
                        student.setFirstName(windowChange.getSurname());
                        student.setSecondName(windowChange.getName());
                        student.setPatronymic(windowChange.getPatronymic());
                        student.setGroup(getGroup(windowChange.getGroup()));

                        DBWorker.updateStudent(student);
                        windowChange.visibleOff();
                        studentList = DBWorker.getAllStudents();
                        updateStudent();
                        windowList.Visible();

                    } catch (IndexOutOfBoundsException ex){
                        errorLine();
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    notFilled();
                }
                windowList = new WindowList(studentsGroup(result), result, listener);
            }

            if (e.getActionCommand().equals("Далее")){
                if (windowAdd.isFill()){
                    System.out.println("Далее");
                } else {
                    notFilled();
                }
            }
            if (e.getActionCommand().equals("Задать стипендиальный фонд")){
                windowCustomSum = new WindowCustomSum(listener,keyListener);
              //
            }
            if (e.getActionCommand().equals("Сохранить сумму")) {
                if(windowCustomSum.checkField()) {
                    sumCustom = windowCustomSum.getSum();
                    int sumDefault = sum();
                    boolean flag = true;
                    while(flag){
                        if(sumDefault > sumCustom){
                            sumCustom = windowCustomSum.getSum();
                            getSum();
                            sumDefault = 0;
                            sumCustom = summaFond.getSumma();
                        }
                        else{
                            flag = false;
                        }
                    }

                    summaFond.setSumma(sumCustom);
                    try {
                        DBWorker.updateSummaFond(summaFond);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    windowCustomSum.isVisiable();
                    windowFond.setSum(sumCustom);
                    windowFond.visibleOff();
                }
            }
            if (e.getActionCommand().equals("Изменить данные о студенте")){
                try {
                    Student student = windowList.getStudent();
                    windowChange = new WindowChange(keyListener, listener,student,getGroups());
                } catch (IndexOutOfBoundsException ex){
                    errorLine();
                }
            }

            if (e.getActionCommand().equals("Удалить студента")) {
                try {
                    Student student = windowList.getStudent();
                    DBWorker.deleteStudent(student);
                    studentList = DBWorker.getAllStudents();
                    deleteStudent();
                    windowList.Visible();

                } catch (IndexOutOfBoundsException ex) {
                    errorLine();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                windowList = new WindowList(studentsGroup(result), result, listener);
            }

            if (e.getActionCommand().equals("Сохранить экзамены")){
                try {
                    Student student = windowList.getStudent();
                    String result = String.valueOf(kurs) + String.valueOf(difzach) + windowExam.getExamsMarrs();
                    student.setMarks(Integer.parseInt(result));
                    DBWorker.updateDataMarks(student);
                    studentList = DBWorker.getAllStudents();
                    windowExam.visibleOff();
                    updateStudent();
                    windowList.Visible();
                }catch (IndexOutOfBoundsException ex){
                        errorLine();
                }catch (SQLException ex) {
                      ex.printStackTrace();
                }
            }

        }

    }

    public class MyKeyListener implements KeyListener {
        public MyKeyListener(){

        }

        @Override
        public void keyTyped(KeyEvent e) {
            JTextField text = (JTextField) e.getSource();
            try {
                if(text.getName().equals("Text")){
                    char c = e.getKeyChar();
                    if  (((c < 'А') || (c > 'Я'))  && (c != KeyEvent.VK_BACK_SPACE)){
                        if((c < 'а') || (c > 'я')){
                            e.consume();
                        }
                    }
                }
                if(text.getName().equals("number")){
                    char c = e.getKeyChar();
                    if  (((c < '0') || (c > '9'))  && (c != KeyEvent.VK_BACK_SPACE)){
                        e.consume();
                    }
                }


            }catch (NullPointerException ex){

            }

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}

