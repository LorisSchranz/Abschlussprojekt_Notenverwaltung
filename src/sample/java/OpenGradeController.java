package sample.java;

import sample.java.model.Grade;

public class OpenGradeController {
    private OpenSubjectController parentController;
    private Grade grade;
    public void initialize(OpenSubjectController parentController, Grade grade) {
        this.parentController = parentController;
        this.grade = grade;
    }
}
