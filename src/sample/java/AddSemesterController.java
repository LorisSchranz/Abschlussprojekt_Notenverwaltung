package sample.java;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddSemesterController implements Initializable {
    public TextField Semester;
    public TextField School;
    public Label ErrorLabel;
    private GridPane gridPane;
    private List<Semester> myList = FXCollections.observableArrayList();

    private int counter = 0;

    void setGridpane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    void setList(List list) {
        myList = list;
    }

    public void confirmAddSubject(ActionEvent event) {
        if (!Semester.getText().isEmpty() && !School.getText().isEmpty()) {
            String ID = (Semester.getText() + "_" + School.getText()).trim();
            Boolean canSave = true;
            for(int i = 0; i<myList.size(); i++){
                try {
                    if(ID.equals(myList.get(i).getId())){
                        canSave = false;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(canSave) {
                Button button = new Button(Semester.getText() + ". " + School.getText() + " Semester");
                button.setOnAction(e -> openSemester());
                if ((counter % 2) == 0) {
                    gridPane.add(button, 0, gridPane.getChildren().size());
                } else {
                    gridPane.add(button, 1, gridPane.getChildren().size() - 1);
                }
                counter++;
                Semester semester = new Semester();
                semester.setId(ID);
                myList.add(semester);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                ErrorLabel.setText("Dieses Semester gibt es schon");
            }
        }
    }

    private EventHandler<ActionEvent> openSemester() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/Semester.fxml"));
            Parent openSemester = fxmlLoader.load();
            OpenSemesterController semesterController = fxmlLoader.getController();
            semesterController.setGridpane(gridPane);
            for (int i = 0; i<myList.size(); i++){
                if(myList.get(i).getId().equals(Semester.getText() + "_" + School.getText())){
                    semesterController.setTitle(myList.get(i).getId());
                    semesterController.setList(myList.get(i));
                }
            }
            Scene scene1 = new Scene(openSemester, 600, 400);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("Open Semester");
            addSubjectStage.setResizable(false);
            addSubjectStage.setScene(scene1);
            addSubjectStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void discard(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
