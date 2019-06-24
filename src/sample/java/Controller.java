package sample.java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

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
    }
}