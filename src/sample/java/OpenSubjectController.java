package sample.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OpenSubjectController implements Initializable {
    @FXML
    public GridPane gridPaneGrade;
    @FXML
    public Label labelSubject;

    @FXML
    public ScrollPane scrollPane = new ScrollPane();

    private Subject mySubject;
    private List<Subject> subjects = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gridPaneGrade.setPadding(new Insets(25));
        gridPaneGrade.setHgap(25);
        gridPaneGrade.setVgap(25);

    }
    public void editSubject(ActionEvent event) {

    }

    public void deleteSubject(ActionEvent event) {
    }

    public void setSubject(Subject subject) {
        this.mySubject = subject;
    }

    public void setTitle(String Title) {
        labelSubject.setText(Title);
    }

    // Add grade
    public void newGrade() {
    }

    public void editSemester(ActionEvent event) {
    }

    public void deleteSemester(ActionEvent event) {
    }

    public void newSubject(ActionEvent event) {
    }
}
