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

public class OpenSemesterController implements Initializable {
    @FXML
    public GridPane gridPaneSubject;
    @FXML
    public Label labelSemester;

    @FXML
    public ScrollPane scrollPane = new ScrollPane();

    private Semester mySemester;
    private List<Subject> subjects = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gridPaneSubject.setPadding(new Insets(25));
        gridPaneSubject.setHgap(25);
        gridPaneSubject.setVgap(25);

    }
    public void editSemester(ActionEvent event) {

    }

    public void deleteSemester(ActionEvent event) {
    }

    public void setSemester(Semester semester) {
        this.mySemester = semester;
    }

    public void setTitle(String Title) {
        labelSemester.setText(Title.substring(0,Title.indexOf("_")) + ". " + Title.substring(Title.indexOf("_") + 1) + " Semester");
    }

    // Add subject
    public void newSubject() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/AddSubject.fxml"));
            Parent newSemester = fxmlLoader.load();
            AddSubjectController subjectController = fxmlLoader.getController();
            subjectController.setGridpane(gridPaneSubject);
            subjectController.setList(subjects);
            Scene scene1 = new Scene(newSemester, 500, 200);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("New Semester");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            addSubjectStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
