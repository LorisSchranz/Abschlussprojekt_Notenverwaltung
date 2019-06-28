package sample.java;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import sample.java.model.Grade;

public class OpenGradeController {
    public TextField textFieldTitle;
    public TextField textFieldMark;
    public javafx.scene.control.Spinner Spinner;
    private OpenSubjectController parentController;
    private Grade grade;
    public void initialize(OpenSubjectController parentController, Grade grade) {
        this.parentController = parentController;
        this.grade = grade;
        System.out.println(grade.getTitle());
        System.out.println(grade.getMark());
        System.out.println(grade.getWeight());
        textFieldTitle.setText(grade.getTitle());
        textFieldMark.setText(Double.toString(grade.getMark()));
        Spinner.getEditor().setText(Double.toString(grade.getWeight()));
    }

    public void editGrade(ActionEvent actionEvent) {
    }

    public void deleteGrade(ActionEvent actionEvent) {
    }
}
