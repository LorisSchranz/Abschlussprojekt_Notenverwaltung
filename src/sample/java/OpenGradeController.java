package sample.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/addGrade.fxml"));
            Parent editGrade = fxmlLoader.load();
            AddGradeController gradeController = fxmlLoader.getController();
            gradeController.tfTitle.setText(grade.getTitle());
            gradeController.tfMark.setText(Double.toString(grade.getMark()));
            gradeController.Spinner.getEditor().setText(Double.toString(grade.getWeight()));
            gradeController.EditString = grade.getTitle();
            for (int i = 0; i < grades.size(); i++) {
                if (grades.get(i).getTitle().equals(grade.getTitle())) {
                    gradeController.position = i;
                }
            }
            Scene scene1 = new Scene(editGrade, 500, 400);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("Edit Grade");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            gradeController.initialize(parentController);
            textFieldTitle.getScene().getWindow().hide();
            addSubjectStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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