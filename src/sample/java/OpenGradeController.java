package sample.java;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import sample.java.model.Grade;

import java.util.List;

public class OpenGradeController extends OpenSubjectController{
    public TextField textFieldTitle;
    public TextField textFieldMark;
    public javafx.scene.control.Spinner Spinner;

    private OpenSubjectController parentController;

    public List<Grade> grades;

    private Grade grade;
    public void initialize(OpenSubjectController parentController, Grade grade) {
        this.parentController = parentController;
        this.grade = grade;
        this.grades = parentController.getGrades();

        textFieldTitle.setText(grade.getTitle());
        textFieldMark.setText(Double.toString(grade.getMark()));
        Spinner.getEditor().setText(Double.toString(grade.getWeight()));
    }

    public void editGrade(ActionEvent actionEvent) {
    }

    public void deleteGrade(ActionEvent actionEvent) {
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getTitle().equals(grade.getTitle())) {
                grades.remove(i);
                parentController.showGrades(grade, "delete");
                textFieldMark.getScene().getWindow().hide();
            }
        }
    }
}