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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.java.model.Grade;
import sample.java.model.Subject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OpenSubjectController extends OpenSemesterController implements Initializable {
    private OpenSemesterController parentController;
    private Subject subject;
    public int counter = 0;

    @FXML
    public GridPane gridPaneGrade;

    @FXML
    public Label labelSubject;

    @FXML
    public ScrollPane scrollPane = new ScrollPane();

    public List<Subject> subjects;

    List<Grade> grades;
    public List<Grade> getGrades() { return grades; }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    public void initialize(OpenSemesterController parentController, Subject subject) {
        this.parentController = parentController;
        this.subject = subject;
        this.grades = subject.getGrades();
        this.subjects = parentController.getSubject();

        gridPaneGrade.setPadding(new Insets(25));
        gridPaneGrade.setHgap(25);
        gridPaneGrade.setVgap(25);

        showGrades(null, "null");

        labelSubject.setText(subject.getName());
    }

    // Add grade
    public void newGrade() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/AddGrade.fxml"));
            Parent newSubject = fxmlLoader.load();
            AddGradeController addGradeController = fxmlLoader.getController();
            Scene scene1 = new Scene(newSubject, 500, 400);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("New Subject");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            addGradeController.initialize(OpenSubjectController.this);
            addSubjectStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showGrades(final Grade grade, String method) {
        gridPaneGrade.getChildren().clear();
        counter = 0;
        for (int i = 0; i < grades.size(); i++) {
            int index = i;
            Button button = new Button();
            button.setText(grades.get(index).getTitle());

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/Grade.fxml"));
                        Parent addGrade = fxmlLoader.load();
                        OpenGradeController openGradeController = fxmlLoader.getController();
                        openGradeController.initialize(OpenSubjectController.this, grades.get(index));
                        Scene subjectOverviewScene = new Scene(addGrade, 800, 600);
                        Stage subjectOverviewStage = new Stage();
                        subjectOverviewStage.setTitle(grades.get(index).getTitle());
                        subjectOverviewStage.setResizable(true);
                        subjectOverviewStage.setScene(subjectOverviewScene);
                        subjectOverviewStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            if ((counter % 2) == 0) {
                gridPaneGrade.add(button, 0, gridPaneGrade.getChildren().size());
            } else {
                gridPaneGrade.add(button, 1, gridPaneGrade.getChildren().size() - 1);
            }
            counter++;
        }
    }

    public void editSubject(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/AddSubject.fxml"));
            Parent editSubject = fxmlLoader.load();
            AddSubjectController subjectController = fxmlLoader.getController();
            subjectController.Subject.setText(subject.getName());
            for (int i = 0; i < subjects.size(); i++) {
                if (subjects.get(i).getName().equals(subject.getName())) {
                    subjectController.position = i;
                }
            }
            Scene scene1 = new Scene(editSubject, 500, 200);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("Edit Semester");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            subjectController.initialize(parentController);
            addSubjectStage.show();
            labelSubject.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSubject(ActionEvent event) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getName().equals(subject.getName())) {
                subjects.remove(i);
                parentController.showSubject(subject, "delete");
                labelSubject.getScene().getWindow().hide();
            }
        }
    }
}