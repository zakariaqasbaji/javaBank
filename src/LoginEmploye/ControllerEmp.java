package LoginEmploye;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerEmp {

        @FXML
	private AnchorPane anchorPaneEmp;
    
	@FXML
        private Button goBack;
        @FXML
        private PasswordField passwordEmp;

        @FXML
        private TextField loginEmp;


    @FXML
    void goHome(ActionEvent event) throws IOException {

    	java.net.URL url = new File("src\\application\\Acueil.fxml").toURI().toURL();
    	Parent root = FXMLLoader.load(url);
        
        
        Scene scene = anchorPaneEmp.getScene();
        StackPane stackPane = (StackPane) scene.getRoot();
        
        
        root.translateYProperty().set(-scene.getHeight());
        
        
        stackPane.getChildren().add(root);
 
        
        Timeline timeline = new Timeline();
        
        KeyValue kv = new KeyValue(root.translateYProperty(),0 , Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
       
        timeline.setOnFinished(t -> {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });
        timeline.play();
    	
 }
    @FXML
    void loginEmp(ActionEvent event) throws MalformedURLException, IOException {
        
        if((loginEmp.getText().equals("bk000")&&passwordEmp.getText().equals("123")))
       {
        java.net.URL url = new File("src\\interfaceEmp\\interfaceEmploye.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("/applicaion/Acueil.fxml"));
	Scene scene = new Scene(root,1100,583);
        Stage stage =new Stage();
	stage.setScene(scene);
	stage.show();
        Stage stage1 = (Stage) goBack.getScene().getWindow();
        stage1.close();
       }
       else
       {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur de connection");
         alert.setHeaderText("login ou password incorrect!");
         alert.setContentText("veuillez verifier et reessayez...");
         alert.showAndWait();
       }
    }
    
}