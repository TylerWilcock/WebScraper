package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Driver extends Application {
	
	public static void main(String[] args) {
		Scraper mainScraper = new Scraper(200);
		mainScraper.startParsing("https://en.wikipedia.org/wiki/Banana");
		//mainScraper.startParsing("http://en.wikipedia.org/w/index.php?title=banana&action=raw");
		//mainScraper.startParsing("http://tylernoip.ddns.net/");
		launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
	    Pane root=null;
	    
	    try {
	        root = (Pane) loader.load();
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }

        Scene scene = new Scene(root);

        primaryStage.setTitle("Wiki Scraper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}