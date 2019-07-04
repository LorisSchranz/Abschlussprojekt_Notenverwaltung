package sample.java.model;


import java.util.List;

public class Semester {
    private String id;
    private double average;
    private List<Subject> subjects;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}