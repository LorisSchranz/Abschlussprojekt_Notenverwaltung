package sample.java;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.java.model.Semester;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public GridPane gridPaneSemester;

    @FXML
    public ScrollPane scrollPane;
    public Stage subjectOverviewStage = new Stage();


    private int counter = 0;

    List<Semester> semesters = new ArrayList<>();

    public List<Semester> getSemester() {
        return semesters;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gridPaneSemester.setPadding(new Insets(25));
        gridPaneSemester.setHgap(25);
        gridPaneSemester.setVgap(25);
    }

    // Add semester
    public void newSemester() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/AddSemester.fxml"));
            Parent newSemester = fxmlLoader.load();
            AddSemesterController semesterController = fxmlLoader.getController();
            Scene scene1 = new Scene(newSemester, 500, 200);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("New Semester");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            semesterController.initialize(Controller.this);
            addSubjectStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showSemester(final Semester semester, String method) {
        if (method.equals("add")) {
            semesters.add(semester);
        }
        gridPaneSemester.getChildren().clear();
        counter = 0;
        for (int i = 0; i < semesters.size(); i++) {
            int index = i;
            Button button = new Button();
            String Text = semesters.get(i).getId().substring(0, semesters.get(i).getId().indexOf("_"))  + ". " + semesters.get(i).getId().substring(semesters.get(i).getId().indexOf("_") +1) + " Semester";
            button.setText(Text);
//open subject overview
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/Semester.fxml"));
                        Parent addSemester = fxmlLoader.load();
                        OpenSemesterController openSemesterController = fxmlLoader.getController();
                        openSemesterController.initialize(Controller.this, semesters.get(index));
                        Scene subjectOverviewScene = new Scene(addSemester, 800, 600);
                        Stage subjectOverviewStage = new Stage();
                        subjectOverviewStage.setTitle(Text);
                        subjectOverviewStage.setResizable(true);
                        subjectOverviewStage.setScene(subjectOverviewScene);
                        subjectOverviewStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            if ((counter % 2) == 0) {
                gridPaneSemester.add(button, 0, gridPaneSemester.getChildren().size());
            } else {
                gridPaneSemester.add(button, 1, gridPaneSemester.getChildren().size() -1 );
            }
            counter++;
        }
    }
}