package sample.java;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.java.model.Grade;
import sample.java.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class AddSubjectController {
    private OpenSemesterController parentController;
    public TextField Subject;
    public int position = -1;

    public Button Save;
    private Boolean canSave = true;
    private List<Subject> subjects;

    private List<Grade> GradeList;

    void initialize(OpenSemesterController parentController) {
        this.parentController = parentController;
        this.subjects = parentController.getSubject();
    }

    public void confirmAddSubject(ActionEvent event) {
        if (!parentController.getSubject().isEmpty()) {
            canSave = true;
            for (Subject subject : parentController.getSubject()) {
                if (subject.getName().equals(Subject.getText().trim())) {
                    canSave = false;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Duplicate Subject name");
                    alert.setContentText("There is already a subject called '" + Subject.getText() + "'");

                    alert.showAndWait();
                }
            }
            if(canSave && position == -1){
                GradeList = new ArrayList<>();
                Subject newSubject = new Subject();
                newSubject.setName(Subject.getText());
                newSubject.setGrades(GradeList);
                subjects.add(newSubject);
                parentController.showSubject(newSubject, "add");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else if(canSave && position != -1){
                subjects.get(position).setName(Subject.getText());
                parentController.showSubject(null, "edit");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }
        else {
            if (!Subject.getText().isEmpty()) {
                GradeList = new ArrayList<>();
                Subject newSubject = new Subject();
                newSubject.setName(Subject.getText());
                newSubject.setGrades(GradeList);
                subjects.add(newSubject);
                parentController.showSubject(newSubject, "add");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }
    }

    public void discard(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}