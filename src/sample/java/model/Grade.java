package sample.java.model;


public class Grade {
    private String title;
    private double mark;
    private double weight;
    public Grade(String title, double mark, double weight) {
        this.title = title;
        this.mark = mark;
        this.weight = weight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
