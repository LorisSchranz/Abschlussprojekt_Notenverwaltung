package sample.java;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.java.model.Semester;
import sample.java.model.Subject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public GridPane gridPaneSemester;

    @FXML
    public ScrollPane scrollPane;

    private int counter = 0;

    List<Semester> semesters = new ArrayList<>();

    public List<Semester> getSemester() {
        return semesters;
    }

    public ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            File filepath = new File(".").getCanonicalFile();
            InputStream inputStream = new FileInputStream(new File(filepath + "\\src\\sample\\java\\file\\data.json"));
            TypeReference<List<Semester>> typeReference = new TypeReference<List<Semester>>() {};
            semesters = mapper.readValue(inputStream,typeReference);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gridPaneSemester.setPadding(new Insets(25));
        gridPaneSemester.setHgap(25);
        gridPaneSemester.setVgap(25);
        showSemester(null,"null");
    }

    // Add semester
    public void newSemester() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/AddSemester.fxml"));
            Parent newSemester = fxmlLoader.load();
            AddSemesterController semesterController = fxmlLoader.getController();
            Scene scene1 = new Scene(newSemester, 600, 400);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("New Semester");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            addSubjectStage.getIcons().add(new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("sample/resources/image/logo.png"))));
            semesterController.initialize(Controller.this);
            addSubjectStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showSemester(final Semester semester, String method) {
        gridPaneSemester.getChildren().clear();
        counter = 0;
        for (int i = 0; i < semesters.size(); i++) {
            int index = i;
            Button button = new Button();
            String Text = semesters.get(i).getId().substring(0, semesters.get(i).getId().indexOf("_")) + ". " + semesters.get(i).getId().substring(semesters.get(i).getId().indexOf("_") + 1) + " Semester";
            Double average = calculateAverageSemester(semesters.get(i));
            semesters.get(i).setAverage(average);
            try {
                File filepath = new File(".").getCanonicalFile();
                mapper.writeValue(new File(filepath + "\\src\\sample\\java\\file\\data.json"), semesters);
            } catch (IOException e) {
                e.printStackTrace();
            }
            button.setText(Text + "\n" + "Average: " + average);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/Semester.fxml"));
                        Parent addSemester = fxmlLoader.load();
                        OpenSemesterController openSemesterController = fxmlLoader.getController();
                        openSemesterController.initialize(Controller.this, semesters.get(index));
                        openSemesterController.labelAverage.setText(Double.toString(calculateAverageSemester(semesters.get(index))));
                        Scene subjectOverviewScene = new Scene(addSemester, 600, 400);
                        Stage subjectOverviewStage = new Stage();
                        subjectOverviewStage.getIcons().add(new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("sample/resources/image/logo.png"))));
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
                gridPaneSemester.add(button, 1, gridPaneSemester.getChildren().size() - 1);
            }
            counter++;
        }
    }

    Double calculateAverageSemester(Semester semester) {
        double average = 0;
        int countSubjects = 0;
        for (Subject subject : semester.getSubjects()) {
            if(subject.getAverage() != 0){
                average = average + subject.getAverage();
                countSubjects++;
            }
        }
        if(average != 0){
            average = average / countSubjects;
        }
        return average;
    }
}