package kurs.model;

public class Group {
    private int id;
    private String name;
    public Group(){

    }
    public Group(int id, String _name){
        this.id = id;
        this.name = _name;
    }

   // public String toString() {return getId()+": " + getName();}
    public int getId() {
        return this.id;
    }
    public String getName() {
        return name;
    }
//    public void setName(String name) {
//        this.name = name;
//    }
}
