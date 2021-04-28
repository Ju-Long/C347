package sg.edu.rp.c346.julien.classjournal;

import java.io.Serializable;

public class DailyCA implements Serializable {

    private String dgGrade;
    private int week;

    public DailyCA(String dgGrade, int week) {
        this.dgGrade = dgGrade;
        this.week = week;
    }

    public String getDgGrade() {
        return dgGrade;
    }

    public void setDgGrade(String dgGrade) {
        this.dgGrade = dgGrade;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
