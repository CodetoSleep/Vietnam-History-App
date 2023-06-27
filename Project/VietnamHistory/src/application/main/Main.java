package application.main;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private double x, y;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	URL fxmlURL = new File("src/application/main/Home.fxml").toURI().toURL();
    	Parent root = FXMLLoader.load(fxmlURL);
        Scene scene = new Scene(root);
        URL cssURL = new File("src/application/main/style.css").toURI().toURL();
        scene.getStylesheets().add(cssURL.toExternalForm());
        primaryStage.setScene(scene); 
        
        // //Set stage borderless
        // primaryStage.initStyle(StageStyle.UNDECORATED);

        // Drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.show();
        primaryStage.setTitle("Vietnam History");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
