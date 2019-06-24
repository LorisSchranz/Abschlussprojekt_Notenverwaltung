package sample.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public GridPane gridPaneSemester = new GridPane();

    @FXML
    public ScrollPane scrollPane = new ScrollPane();

    private List<Semester> subjects = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gridPaneSemester.setPadding(new Insets(25));
        gridPaneSemester.setHgap(25);
        gridPaneSemester.setVgap(25);

    }


    // Add subject
    public void newSemester() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/AddSemester.fxml"));
            Parent newSemester = fxmlLoader.load();
            AddSemesterController semesterController = fxmlLoader.getController();
            semesterController.setGridpane(gridPaneSemester);
            semesterController.setList(subjects);
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