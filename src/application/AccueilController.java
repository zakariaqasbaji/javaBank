package application;





import java.io.File;
import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;




public class AccueilController{
	@FXML
    public AnchorPane anchorPane;

    @FXML
    private Button employe;

    @FXML
    private Button directeur;
    @FXML
    private StackPane stackPane;

    @FXML
    void goToDir(ActionEvent event) throws IOException {
    	java.net.URL url = new File("src\\DirecteurLogin\\Dire.fxml").toURI().toURL();
    	Parent root = FXMLLoader.load(url);
        Scene scene = anchorPane.getScene();
        root.translateYProperty().set(scene.getHeight());    
        stackPane.getChildren().add(root);
        

        Timeline timeline = new Timeline();
        
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        
        timeline.setOnFinished(t -> {
            stackPane.getChildren().remove(anchorPane);
        });
        timeline.play();

    }

    @FXML
    void goToEmp(ActionEvent event) throws IOException {
    	
    	java.net.URL url = new File("src\\LoginEmploye\\Employe.fxml").toURI().toURL();
    	Parent root = FXMLLoader.load(url);
        Scene scene = anchorPane.getScene();
        
        root.translateYProperty().set(scene.getHeight());

        stackPane.getChildren().add(root);
 
        
       
        Timeline timeline = new Timeline();
        
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
       
        timeline.setOnFinished(t -> {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
            
        });
        timeline.play();

    }
}
