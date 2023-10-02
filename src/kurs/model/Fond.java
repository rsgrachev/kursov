package kurs.model;

public class Fond {
    private int id;
    private Student student;
    private int sum;




    public Fond(int id, Student student, int sum) {
        this.id = id;
        this.student = student;
        this.sum = sum;
    }

    public Student getStudent(){
        return student;
    }
    public int getSum() { return sum; }
}
