package sample.java;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.java.model.Subject;

public class AddSubjectController {
    private OpenSemesterController parentController;
    public TextField Subject;
    public Button Save;
    private Boolean canSave = true;

    void initialize(OpenSemesterController parentController) {
        this.parentController = parentController;
    }
    
    public void confirmAddSubject(ActionEvent event) {
        System.out.println(parentController.getSubject());
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
            if(canSave){
                Subject newSubject = new Subject();
                newSubject.setName(Subject.getText());
                parentController.showSubject(newSubject);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }
        else {
            if (!Subject.getText().isEmpty()) {
                Subject newSubject = new Subject();
                newSubject.setName(Subject.getText());
                parentController.showSubject(newSubject);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }
    }

    public void discard(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
