package application.details.sukien;

import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.sukienlichsu.SuKienLichSu;

public class SuKienDetails {
	public SuKienDetails(SuKienLichSu curSelect) {
		AnchorPane root = new AnchorPane();
		Stage stage = new Stage();
		stage.setTitle("Sự Kiện Details");
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(309);
        imageView.setFitWidth(424);
        imageView.setLayoutX(327);
        imageView.setLayoutY(25);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setPickOnBounds(true);
        imageView.setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");
        
        InputStream inputStream = getClass().getResourceAsStream("/application/images/geneva.jpg");
        Image image = new Image(inputStream);
        imageView.setImage(image);

        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(245);
        gridPane.setLayoutY(345);
        gridPane.setPrefHeight(234.0);
        gridPane.setPrefWidth(588.0);
        gridPane.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");
        gridPane.setPadding(new Insets(0, 30, 0, 30));
        
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10);
        columnConstraints.setPrefWidth(100);
        gridPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setMinHeight(10);
        rowConstraints1.setPrefHeight(15);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints1);
        
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setMinHeight(10);
        rowConstraints2.setPrefHeight(15);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints2);
        
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setMinHeight(10);
        rowConstraints3.setPrefHeight(15);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints3);
        
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setMinHeight(10);
        rowConstraints4.setPrefHeight(15);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints4);

        Label ten = new Label("- Tên Sự Kiện: " + curSelect.getTen());
        Label thoiGian = new Label("- Thời Gian Diễn Ra: " + curSelect.getThoiGian());
        Label diaDiem = new Label("- Địa Điểm Xảy Ra: " + curSelect.getDiaDiem());
        Label thoiKy = new Label("- Thời Kỳ: " + curSelect.getThoiKy().getTen());
        
        ten.getStyleClass().add("text-color");
        thoiGian.getStyleClass().add("text-color");
        diaDiem.getStyleClass().add("text-color");
        thoiKy.getStyleClass().add("text-color");
        
        gridPane.add(ten, 0, 0);
        gridPane.add(thoiGian, 0, 1);
        gridPane.add(diaDiem, 0, 2);
        gridPane.add(thoiKy, 0, 3);
        
        root.getChildren().add(imageView);
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root,1100,600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
}
