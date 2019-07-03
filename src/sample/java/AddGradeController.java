package sample.java;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.java.model.Grade;

import java.util.List;

public class AddGradeController {
    public TextField tfTitle;
    public TextField tfMark;
    public javafx.scene.control.Spinner Spinner;
    private OpenSubjectController parentController;
    public int position = -1;

    public Button Save;
    private Boolean canSave = true;

    private Double finalMark;
    private Double finalWeight;

    private List<Grade> grades;

    void initialize(OpenSubjectController parentController) {
        this.parentController = parentController;
        this.grades = parentController.getGrades();
    }

    public void confirmAddSubject(ActionEvent event) {
        if (!parentController.getGrades().isEmpty()) {
            canSave = true;
            checkInputs();
            if (canSave && position == -1) {
                Grade newGrade = new Grade();
                newGrade.setTitle(tfTitle.getText());
                newGrade.setMark(Double.valueOf(tfMark.getText().trim()));
                newGrade.setWeight(Double.valueOf(Spinner.getValue().toString().trim()));
                grades.add(newGrade);
                parentController.showGrades(newGrade, "add");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else if (canSave && position != -1) {
                grades.get(position).setTitle(tfTitle.getText());
                grades.get(position).setMark(Double.valueOf(tfMark.getText().trim()));
                grades.get(position).setWeight(Double.valueOf(Spinner.getValue().toString().trim()));
                parentController.showGrades(null, "edit");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        } else {
            canSave = true;
            checkInputs();
            if (canSave) {
                Grade newGrade = new Grade();
                newGrade.setTitle(tfTitle.getText());
                newGrade.setMark(Double.valueOf(tfMark.getText().trim()));
                newGrade.setWeight(Double.valueOf(Spinner.getEditor().getText().trim()));
                grades.add(newGrade);
                parentController.showGrades(newGrade, "add");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }
    }

    public void checkInputs() {
        if (!parentController.getGrades().isEmpty()) {
            for (Grade grade : parentController.getGrades()) {
                if (grade.getTitle().equals(tfTitle.getText().trim())) {
                    canSave = false;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Duplicate Grade name");
                    alert.setContentText("There is already a subject called '" + tfTitle.getText() + "'");

                    alert.showAndWait();
                }
            }
        }
        if (tfTitle.getText().trim().isEmpty() || tfMark.getText().trim().isEmpty() || Spinner.getValue().toString().trim().isEmpty()) {
            canSave = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No input");
            alert.setContentText("You can't have an empty field");

            alert.showAndWait();
        }

        try {
            finalMark = Double.valueOf(tfMark.getText().trim());
        } catch (Exception e) {
            canSave = false;
            finalMark = 5.0;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Your mark must be a number");

            alert.showAndWait();
        }

        if (finalMark < 1.0 || finalMark > 6.0) {
            canSave = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Your mark can't be under 1 or over 6");

            alert.showAndWait();
        }

        try {
            finalWeight = Double.parseDouble(Spinner.getEditor().getText().trim());
        } catch (Exception e) {
            canSave = false;
            finalWeight = 1.00;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("The weight of your grade must be a number");

            alert.showAndWait();
        }

        if (finalWeight < 0.0) {
            canSave = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("The weight of your grade can't be under 0");

            alert.showAndWait();
        }
    }

    public void discard(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}