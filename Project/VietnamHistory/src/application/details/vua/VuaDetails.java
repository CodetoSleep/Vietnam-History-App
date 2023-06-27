package application.details.vua;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;
import model.nhanvatlichsu.Vua;

public class VuaDetails {
	public VuaDetails(Vua curSelect) {
		AnchorPane root = new AnchorPane();
		Stage stage = new Stage();
		stage.setTitle("Vua Details");
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(490.0);
        imageView.setFitWidth(389.0);
        imageView.setLayoutX(60.0);
        imageView.setLayoutY(55.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");
        
        URL imageURL = null;
		try {
			imageURL = new File("src/application/images/vuabaodai.jpg").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Image image = new Image(imageURL.toExternalForm());
		imageView.setImage(image);

        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(450.0);
        gridPane.setLayoutY(79.0);
        gridPane.setPrefHeight(450.0);
        gridPane.setPrefWidth(590.0);
        gridPane.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");
        gridPane.setPadding(new Insets(20)); 

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);
        gridPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(20.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints1);
        
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(20.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints2);
        
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(20.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints3);
        
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(20.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints4);
        
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMinHeight(10.0);
        rowConstraints5.setPrefHeight(20.0);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints5);
        
        RowConstraints rowConstraints6 = new RowConstraints();
        rowConstraints6.setMinHeight(10.0);
        rowConstraints6.setPrefHeight(20.0);
        rowConstraints6.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints6);
        
        RowConstraints rowConstraints7 = new RowConstraints();
        rowConstraints7.setMinHeight(10.0);
        rowConstraints7.setPrefHeight(20.0);
        rowConstraints7.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints7);

        Label ten = new Label("- Tên Vua: " + curSelect.getTen());
        Label namTriVi = new Label("- Năm Trị Vì: " + curSelect.getNamTriVi());
        Label theThu = new Label("- Tên Thứ: " + curSelect.getTheThu());
        Label tenHuy = new Label("- Tên Húy: " + curSelect.getTenHuy());
        Label nienHieu = new Label("- Niên Hiệu: " + curSelect.getNienHieu());
        Label thuyHieu = new Label("- Thụy Hiệu: " + curSelect.getThuyHieu());
        Label mieuHieu = new Label("- Miếu Hiệu: " + curSelect.getMieuHieu());
        
        ten.getStyleClass().add("text-color");
	    namTriVi.getStyleClass().add("text-color");
	    theThu.getStyleClass().add("text-color");
	    theThu.setWrapText(true);
	    tenHuy.getStyleClass().add("text-color");
	    tenHuy.setWrapText(true);
	    nienHieu.getStyleClass().add("text-color");
	    thuyHieu.getStyleClass().add("text-color");
	    thuyHieu.setWrapText(true);
	    mieuHieu.getStyleClass().add("text-color");

        gridPane.add(ten, 0, 0);
        gridPane.add(namTriVi, 0, 1);
        gridPane.add(theThu, 0, 2);
        gridPane.add(tenHuy, 0, 3);
        gridPane.add(nienHieu, 0, 4);
        gridPane.add(thuyHieu, 0, 5);
        gridPane.add(mieuHieu, 0, 6);

        root.getChildren().addAll(imageView, gridPane);

        Scene scene = new Scene(root, 1100, 600);
        
        URL cssURL = null;
		try {
			cssURL = new File("src/application/details/vua/style.css").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
