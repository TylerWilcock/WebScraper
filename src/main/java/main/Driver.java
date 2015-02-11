package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    	
        Button scrapeBtn = new Button();
        scrapeBtn.setText("Scrape!");
        scrapeBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent event) {
                System.out.println("Scrape button pressed.");
            }
        });
        
        TextField console = new TextField();
        
        GridPane root = new GridPane();    
        
        GridPane.setConstraints(scrapeBtn, 2, 2, 1, 1);
        root.getChildren().add(scrapeBtn);

        root.getChildren().add(console);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Wiki Scraper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}