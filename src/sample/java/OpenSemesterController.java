package sample.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenSemesterController implements Initializable {
    @FXML
    public GridPane gridPaneSubject;
    @FXML
    public Label labelSemester;

    private Semester mySemester;

    public void editSemester(ActionEvent event) {

    }

    public void deleteSemester(ActionEvent event) {
    }

    public void setGridpane(GridPane gridPane) {
        this.gridPaneSubject = gridPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setList(Semester semester) {
        mySemester = semester;
    }

    public void setTitle(String Title) {
        labelSemester.setText(Title.substring(0,Title.indexOf("_")) + ". " + Title.substring(Title.indexOf("_") + 1) + " Semester");
    }
}
