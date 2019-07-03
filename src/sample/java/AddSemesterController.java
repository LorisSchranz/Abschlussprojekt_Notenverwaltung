package sample.java;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.java.model.Semester;
import sample.java.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class AddSemesterController{
    public TextField Semester;
    public TextField School;
    public int position = -1;

    private boolean canSave = true;
    private String ID;
    private List<Subject> SubjectList;

    private List<Semester> semesters;

    private Controller parentController;

    void initialize(Controller parentController) {
        this.parentController = parentController;
        this.semesters = parentController.getSemester();
    }

    public void confirmAddSemester(ActionEvent event) {
        ID = (Semester.getText() + "_" + School.getText()).trim();
        SubjectList = new ArrayList<>();
        if (!parentController.getSemester().isEmpty()) {
            canSave = true;
            for (Semester semester : parentController.getSemester()) {
                if (semester.getId().equals(ID)) {
                    canSave = false;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Duplicate Semester name");
                    alert.setContentText("There is already a subject called '" + Semester.getText() + ". " + School.getText() + " Semester" + "'");

                    alert.showAndWait();
                }
            }
            if (canSave && position == -1) {
                Semester newSemester = new Semester();
                newSemester.setId(ID);
                newSemester.setSubjects(SubjectList);
                semesters.add(newSemester);
                parentController.showSemester(newSemester, "add");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else if(canSave && position != -1){
                semesters.get(position).setId(ID);
                parentController.showSemester(null, "edit");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        } else {
            if (!Semester.getText().trim().isEmpty() && !School.getText().trim().isEmpty()) {
                Semester newSemester = new Semester();
                newSemester.setId(ID);
                newSemester.setSubjects(SubjectList);
                semesters.add(newSemester);
                parentController.showSemester(newSemester, "add");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }
    }

    public void discard(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}