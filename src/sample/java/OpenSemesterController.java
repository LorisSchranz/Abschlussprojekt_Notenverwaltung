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
import sample.java.model.Semester;
import sample.java.model.Subject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OpenSemesterController extends Controller implements Initializable {
    private Controller parentController;
    private Semester semester;

    @FXML
    public GridPane gridPaneSubject;
    @FXML
    public Label labelSemester;

    public List<Semester> semesters;
    @FXML
    public ScrollPane scrollPane = new ScrollPane();

    private int counter = 0;

    List<Subject> subjects;

    public List<Subject> getSubject() {
        return subjects;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gridPaneSubject.setPadding(new Insets(25));
        gridPaneSubject.setHgap(25);
        gridPaneSubject.setVgap(25);
    }

    public void initialize(Controller parentController, Semester semester) {
        this.parentController = parentController;
        this.semester = semester;
        this.subjects = semester.getSubjects();
        this.semesters = parentController.getSemester();

        showSubject(null,"null");

        labelSemester.setText(semester.getId().substring(0, semester.getId().indexOf("_"))  + ". " + semester.getId().substring(semester.getId().indexOf("_") +1) + " Semester");
    }

    public void editSemester(ActionEvent event) {

    }

    public void deleteSemester(ActionEvent event) {
        for (int i = 0; i < semesters.size(); i++) {
            if (semesters.get(i).getId().equals(semester.getId())) {
                semesters.remove(i);
                parentController.showSemester(semester, "delete");
                labelSemester.getScene().getWindow().hide();
            }
        }
    }


    // Add subject
    public void newSubject() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/AddSubject.fxml"));
            Parent newSubject = fxmlLoader.load();
            AddSubjectController subjectController = fxmlLoader.getController();
            Scene scene1 = new Scene(newSubject, 500, 200);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("New Subject");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            subjectController.initialize(OpenSemesterController.this);
            addSubjectStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showSubject(final Subject subject, String method) {
        if (method.equals("add")) {
            subjects.add(subject);
        }
        gridPaneSubject.getChildren().clear();
        counter = 0;
        for (int i = 0; i < subjects.size(); i++) {
            int index = i;
            Button button = new Button();
            button.setText(subjects.get(index).getName());

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/Subject.fxml"));
                        Parent addSemester = fxmlLoader.load();
                        OpenSubjectController openSubjectController = fxmlLoader.getController();
                        openSubjectController.initialize(OpenSemesterController.this, subjects.get(index));
                        Scene subjectOverviewScene = new Scene(addSemester, 800, 600);
                        Stage subjectOverviewStage = new Stage();
                        subjectOverviewStage.setTitle(subjects.get(index).getName());
                        subjectOverviewStage.setResizable(true);
                        subjectOverviewStage.setScene(subjectOverviewScene);
                        subjectOverviewStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            if ((counter % 2) == 0) {
                gridPaneSubject.add(button, 0, gridPaneSubject.getChildren().size());
            } else {
                gridPaneSubject.add(button, 1, gridPaneSubject.getChildren().size() - 1);
            }
            counter++;
        }
    }
}