package application.details.chientranh;

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
import model.sukienlichsu.ChienTranh;

public class ChienTranhDetails {
	public ChienTranhDetails(ChienTranh curSelect) {
		AnchorPane root = new AnchorPane();
		Stage stage = new Stage();
		stage.setTitle("Chiến Tranh Details");
	    root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	    root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
	
	    ImageView imageView = new ImageView();
	    imageView.setFitHeight(277);
	    imageView.setFitWidth(441);
	    imageView.setLayoutX(332);
	    imageView.setLayoutY(14);
	    imageView.setPreserveRatio(true);
	    imageView.setSmooth(true);
	    imageView.setPickOnBounds(true);
	    imageView.setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");
	    
	    InputStream inputStream = getClass().getResourceAsStream("/application/images/dienbienphu.jpg");
	    Image image = new Image(inputStream);
	    imageView.setImage(image);
	
	    GridPane gridPane = new GridPane();
	    gridPane.setLayoutX(170);
	    gridPane.setLayoutY(310);
	    gridPane.setPrefHeight(280.0);
        gridPane.setPrefWidth(770.0);
	    gridPane.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");
	    gridPane.setPadding(new Insets(10, 30, 10, 30));
	    
	    ColumnConstraints columnConstraints = new ColumnConstraints();
	    columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
	    columnConstraints.setMinWidth(10);
	    columnConstraints.setPrefWidth(100);
	    gridPane.getColumnConstraints().add(columnConstraints);
	
	    RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setMinHeight(10);
        rowConstraints1.setPrefHeight(10);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints1);
        
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setMinHeight(10);
        rowConstraints2.setPrefHeight(10);
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
        
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMinHeight(10);
        rowConstraints5.setPrefHeight(30);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints5);
	
	    Label ten = new Label("- Tên Cuộc Chiến: " + curSelect.getTen());
	    Label thoiGian = new Label("- Thời Gian Diễn Ra: " + curSelect.getThoiGian());
	    Label benThuNhat = new Label("- Bên Thứ Nhất: " + curSelect.getBenThuNhat());
	    Label benThuHai = new Label("- Bên Thứ Hai: " + curSelect.getBenThuHai());
	    Label ketQua = new Label("- Kết Quả: " + curSelect.getKetQua());
	    
	    ten.getStyleClass().add("text-color");
	    thoiGian.getStyleClass().add("text-color");
	    benThuNhat.getStyleClass().add("text-color");
	    benThuNhat.setWrapText(true);
	    benThuHai.getStyleClass().add("text-color");
	    benThuHai.setWrapText(true);
	    ketQua.getStyleClass().add("text-color");
	    ketQua.setWrapText(true);
	    
	    gridPane.add(ten, 0, 0);
	    gridPane.add(thoiGian, 0, 1);
	    gridPane.add(benThuNhat, 0, 2);
	    gridPane.add(benThuHai, 0, 3);
	    gridPane.add(ketQua, 0, 4);
	    
	    root.getChildren().addAll(imageView, gridPane);
	
	    Scene scene = new Scene(root,1100,600);
	    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	    stage.setScene(scene);
	    stage.show();
	}
}
