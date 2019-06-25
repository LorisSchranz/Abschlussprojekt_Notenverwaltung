package sample.java;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddSubjectController implements Initializable {
    public TextField Subject;
    public Label ErrorLabel;
    private GridPane gridPane;
    private List<Subject> myList = FXCollections.observableArrayList();

    private int counter = 0;

    void setGridpane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    void setList(List list) {
        myList = list;
    }

    public void confirmAddSubject(ActionEvent event) {
        if (!Subject.getText().isEmpty()) {
            String name = (Subject.getText()).trim();
            Boolean canSave = true;
            for(int i = 0; i<myList.size(); i++){
                try {
                    if(name.equals(myList.get(i).getName())){
                        canSave = false;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(canSave) {
                Button button = new Button(Subject.getText());
                button.setOnAction(e -> openSubject());
                if ((counter % 2) == 0) {
                    gridPane.add(button, 0, gridPane.getChildren().size());
                } else {
                    gridPane.add(button, 1, gridPane.getChildren().size() - 1);
                }
                counter++;
                Subject subject = new Subject();
                subject.setName(name);
                myList.add(subject);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                ErrorLabel.setText("Dieses Fach gibt es schon");
            }
        }
    }

    private EventHandler<ActionEvent> openSubject() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/Subject.fxml"));
            Parent openSubject = fxmlLoader.load();
            OpenSubjectController subjectController = fxmlLoader.getController();
            for (int i = 0; i<myList.size(); i++){
                if(myList.get(i).getName().equals(Subject.getText())){
                    subjectController.setTitle(myList.get(i).getName());
                    subjectController.setSubject(myList.get(i));
                }
            }
            Scene scene1 = new Scene(openSubject, 600, 400);
            Stage addSubjectStage = new Stage();
            addSubjectStage.setTitle("Open Subject");
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
