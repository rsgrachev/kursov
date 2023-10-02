package kurs.model;

public class SummaFond {
    int ID;
    int summa;

    public SummaFond(int _ID, int _summa){
        this.ID = _ID;
        this.summa = _summa;
    }

    public int getSumma() {
        return summa;
    }

    public int getID() {
        return ID;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }
}
