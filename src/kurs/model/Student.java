package kurs.model;

public class Student {

    private int id;
    private String FirstName;
    private String SecondName;
    private String Patronymic;
    private Group group;
    private boolean budget; // бюджет
    private boolean studyDebts;
    private boolean offset;
    private int marks;

    public Student(int id, String FirstName, String SecondName, String Patronymic, Group group, boolean budget, boolean studyDebts, boolean offset, int marks){
        this.id = id;
        this.setFirstName(FirstName);
        this.setSecondName(SecondName);
        this.setPatronymic(Patronymic);
        this.group = group;
        this.setBudget(budget);
        this.setStudyDebts(studyDebts);
        this.setOffset(offset);
        this.setMarks(marks);
    }

    public int getId() {return this.id;}

    public Group getGroup(){return group;}
    public void setGroup(Group group){ this.group = group;}
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getSecondName() {
        return SecondName;
    }
    public void setSecondName(String SecondName) {
        this.SecondName = SecondName;
    }

    public String getPatronymic() {
        return Patronymic;
    }
    public void setPatronymic(String Patronymic) {
        this.Patronymic = Patronymic;
    }

    public boolean isBudget() {return budget;}
    public void setBudget(boolean budget) {this.budget = budget;}

    public boolean isOffset() {return offset;}
    public void setOffset(boolean offset) {this.offset = offset;}
    public boolean getOffset(){return offset;}

    public boolean isStudyDebts() {return studyDebts;}
    public void setStudyDebts(boolean studyDebts) {this.studyDebts = studyDebts;}

    public int getMarks() {return marks;}
    public void setMarks(int marks) {this.marks = marks;}
    public String getFIO(){
        return getFirstName()+ " "+ getSecondName()+ " "+ getPatronymic();
    }
}

