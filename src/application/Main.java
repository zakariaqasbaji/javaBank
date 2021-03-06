package application;
	
import java.io.File;
import java.io.IOException;


//import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			java.net.URL url = new File("src\\application\\Acueil.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
		//	Parent root = FXMLLoader.load(getClass().getResource("/applicaion/Acueil.fxml"));
			Scene scene = new Scene(root,400,450);
                        primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e) {
		}
	}
	
	public static void main(String[] args) {
            launch(args);
	}
}