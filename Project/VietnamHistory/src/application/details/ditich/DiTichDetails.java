package application.details.ditich;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

import model.nhanvatlichsu.NhanVatLichSu;
import model.ditichlichsu.DiTichLichSu;

public class DiTichDetails {
	public DiTichDetails(DiTichLichSu curSelect, ObservableList<NhanVatLichSu> listNhanVat) {
		AnchorPane root = new AnchorPane();
		Stage stage = new Stage();
		stage.setTitle("Di Tích Details");
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(490);
        imageView.setFitWidth(389);
        imageView.setLayoutX(83);
        imageView.setLayoutY(52);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setPickOnBounds(true);
        imageView.setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");
        
        URL imageURL = null;
		try {
			imageURL = new File("Project/VietnamHistory/src/application/images/relic.jpg").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Image image = new Image(imageURL.toExternalForm());
		imageView.setImage(image);

        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(488);
        gridPane.setLayoutY(69);
        gridPane.setPrefHeight(456.0);
        gridPane.setPrefWidth(529.0);
        gridPane.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");
        gridPane.setPadding(new Insets(10, 30, 10, 20));
        
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
        
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMinHeight(10);
        rowConstraints5.setPrefHeight(15);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints5);
        
        RowConstraints rowConstraints6 = new RowConstraints();
        rowConstraints6.setMinHeight(10);
        rowConstraints6.setPrefHeight(15);
        rowConstraints6.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints6);
        
        RowConstraints rowConstraints7 = new RowConstraints();
        rowConstraints7.setMinHeight(10);
        rowConstraints7.setPrefHeight(30);
        rowConstraints7.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().addAll(rowConstraints7);
        
        Label ten = new Label("- Tên Di Tích: " + curSelect.getTen());
        Label diaDiem= new Label("- Địa Điểm: " + curSelect.getDiaDiem());
        Label loaiDiTich = new Label("- Loại Di Tích: " + curSelect.getLoaiDiTich());
        Label namCongNhan = new Label("- Năm Công Nhận: " + curSelect.getNamCongNhan());
        Label loaiXepHang = new Label("- Loại Xếp Hạng: " + curSelect.getLoaiXepHang());
        Label xepHang = new Label("- Xếp Hạng: " + curSelect.getXepHang());
        String strNhanVatLienQuan = "";
        ArrayList<NhanVatLichSu> newNhanVat = new ArrayList<NhanVatLichSu>();
        for (int i = 0; i < curSelect.getNhanVatLienQuan().size(); i++) {
            strNhanVatLienQuan += curSelect.getNhanVatLienQuan().get(i).getTen() + ",";
            for (int j = 0; j < listNhanVat.size(); j++) {
                if (curSelect.getNhanVatLienQuan().get(i).getTen().toLowerCase()
                		.indexOf(listNhanVat.get(j).getTen().toLowerCase()) != -1) {
                    newNhanVat.add(listNhanVat.get(j));
                }
            }
        }
        curSelect.setNhanVatLienQuan(newNhanVat);
        Label nhanVatLienQuan = new Label("- Nhân Vật Liên Quan: " + (strNhanVatLienQuan == "" ? "Không có" : strNhanVatLienQuan));

        ten.getStyleClass().add("text-color");
        diaDiem.getStyleClass().add("text-color");
        loaiDiTich.getStyleClass().add("text-color");
        namCongNhan.getStyleClass().add("text-color");
        loaiXepHang.getStyleClass().add("text-color");
        xepHang.getStyleClass().add("text-color");
        nhanVatLienQuan.getStyleClass().add("text-color");
        nhanVatLienQuan.setWrapText(true);
        
        gridPane.add(ten, 0, 0);
        gridPane.add(diaDiem, 0, 1);
        gridPane.add(loaiDiTich, 0, 2);
        gridPane.add(namCongNhan, 0, 3);
        gridPane.add(loaiXepHang, 0, 4);
        gridPane.add(xepHang, 0, 5);
        gridPane.add(nhanVatLienQuan, 0, 6);
        
        root.getChildren().addAll(imageView, gridPane);

        Scene scene = new Scene(root,1100,600);
        
        URL cssURL = null;
		try {
			cssURL = new File("Project/VietnamHistory/src/application/details/ditich/style.css").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
}
