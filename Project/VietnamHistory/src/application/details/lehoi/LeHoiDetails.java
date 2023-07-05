package application.details.lehoi;

import javafx.collections.ObservableList;
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

import model.lehoi.LeHoi;
import model.nhanvatlichsu.NhanVatLichSu;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LeHoiDetails {
	public LeHoiDetails(LeHoi curSelect, ObservableList<NhanVatLichSu> listNhanVat) {
		AnchorPane root = new AnchorPane();
		Stage stage = new Stage();
		stage.setTitle("Lễ Hội Details");
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(450);
        imageView.setLayoutX(314);
        imageView.setLayoutY(25);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setPickOnBounds(true);
        imageView.setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");
        
        URL imageURL = null;
		try {
			imageURL = new File("src/application/images/hoi_chua_bai_dinh.jpg").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Image image = new Image(imageURL.toExternalForm());
		imageView.setImage(image);

        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(245);
        gridPane.setLayoutY(345);
        gridPane.setPrefSize(588, 234);
        gridPane.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");
        gridPane.setPadding(new Insets(0, 30, 0, 30));
        
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10);
        columnConstraints.setPrefWidth(100);
        gridPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setMinHeight(10);
        rowConstraints1.setPrefHeight(30);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints1);
        
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setMinHeight(10);
        rowConstraints2.setPrefHeight(30);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints2);
        
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setMinHeight(10);
        rowConstraints3.setPrefHeight(30);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints3);
        
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setMinHeight(10);
        rowConstraints4.setPrefHeight(30);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints4);
        
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMinHeight(10);
        rowConstraints5.setPrefHeight(30);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints5);
        
        Label ten = new Label("- Tên Lễ Hội: " + curSelect.getTen());
        Label thoiGian = new Label("- Thời Gian Diễn Ra: " + curSelect.getThoiGian());
        Label diaDiem = new Label("- Địa Điểm Tổ Chức: " + curSelect.getDiaDiem());
        Label noiDung = new Label("- Nội Dung: " + curSelect.getNoiDung());
        String strNhanVatLienQuan = "";
        ArrayList<NhanVatLichSu> newNhanVat = new ArrayList<NhanVatLichSu>();
        Label nhanVatLienQuan = new Label();
        if (curSelect.getNhanVatLienQuan() != null) {
	        for (int i = 0; i < curSelect.getNhanVatLienQuan().size(); i++) {
	            strNhanVatLienQuan += curSelect.getNhanVatLienQuan().get(i).getTen() + ",";
	            for (int j = 0; j < listNhanVat.size(); j++) {
	                if (curSelect.getNhanVatLienQuan().get(i).getTen().toLowerCase().indexOf(listNhanVat.get(j).getTen().toLowerCase()) != -1) {
	                    newNhanVat.add(listNhanVat.get(j));
	                }
	            }
	        }
	        curSelect.setNhanVatLienQuan(newNhanVat);
	        nhanVatLienQuan = new Label("- Nhân Vật Liên Quan: " + (strNhanVatLienQuan == "" ? "Không có" : strNhanVatLienQuan));
        }
        else {
        	nhanVatLienQuan = new Label("- Nhân Vật Liên Quan: Không có");
        }

        ten.getStyleClass().add("text-color");
        thoiGian.getStyleClass().add("text-color");
        diaDiem.getStyleClass().add("text-color");
        noiDung.getStyleClass().add("text-color");
        nhanVatLienQuan.getStyleClass().add("text-color");
        
        gridPane.add(ten, 0, 0);
        gridPane.add(thoiGian, 0, 1);
        gridPane.add(diaDiem, 0, 2);
        gridPane.add(noiDung, 0, 3);
        gridPane.add(nhanVatLienQuan, 0, 4);
        
        root.getChildren().addAll(imageView, gridPane);

        Scene scene = new Scene(root,1100,600);
        
        URL cssURL = null;
		try {
			cssURL = new File("src/application/details/lehoi/style.css").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
