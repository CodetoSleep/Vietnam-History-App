package application.details.thoiky;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import model.thoiky.ThoiKy;
import model.nhanvatlichsu.Vua;

public class ThoiKyDetails {
	public ThoiKyDetails(ThoiKy curSelect, ObservableList<Vua> listVua) {
		
		AnchorPane root = new AnchorPane();
		Stage stage = new Stage();
		stage.setTitle("Thời Kỳ Details");
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(490.0);
        imageView.setFitWidth(320.0);
        imageView.setLayoutX(64.0);
        imageView.setLayoutY(95.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

        
        URL imageURL = null;
		try {
			imageURL = new File("Project/VietnamHistory/src/application/images/bandovietnam.jpg").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Image image = new Image(imageURL.toExternalForm());
		imageView.setImage(image);
		
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(407.0);
        gridPane.setLayoutY(132.0);
        gridPane.setPrefHeight(336.0);
        gridPane.setPrefWidth(620.0);
        gridPane.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");
        gridPane.setPadding(new Insets(10, 20, 20, 20)); 

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);
        gridPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(10.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints1);
        
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(10.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints2);
        
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(10.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints3);
        
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(10.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints4);
        
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMinHeight(10.0);
        rowConstraints5.setPrefHeight(10.0);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints5);
        
        RowConstraints rowConstraints6 = new RowConstraints();
        rowConstraints6.setMinHeight(10.0);
        rowConstraints6.setPrefHeight(70.0);
        rowConstraints6.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints6);

        Label ten = new Label("- Tên Thời Kỳ: " + curSelect.getTen());
        Label thoiGianTonTai = new Label("- Thời Gian Tồn Tại: " + curSelect.getThoiGianTonTai());
        Label kinhDo = new Label("- Kinh Đô: " + curSelect.getKinhDo());
        Label quocHieu = new Label("- Quốc Hiệu: " + curSelect.getQuocHieu());
        Label nguoiSangLap = new Label();
        if (curSelect.getNguoiSangLap() != null) {
        	nguoiSangLap = new Label("- Người Sáng Lập: " + curSelect.getNguoiSangLap().getTen());
        }
        else {
        	nguoiSangLap = new Label("- Người Sáng Lâp: Không có");
        }
        String strVua = "";
        ArrayList<Vua> newVua = new ArrayList<Vua>();
        for (int i = 0; i < curSelect.getCacDoiVua().size(); i++) {
            strVua += curSelect.getCacDoiVua().get(i).getTen() + ", ";
            for (int j = 0; j < listVua.size(); j++) {
                if (curSelect.getCacDoiVua().get(i).getTen().toLowerCase()
                        .indexOf(listVua.get(j).getTen().toLowerCase()) != -1) {
                    newVua.add(listVua.get(j));
                }
            }
        }
        Label kings = new Label("- Các Đời Vua: " + strVua);
        curSelect.setCacDoiVua(newVua);
        
        ten.getStyleClass().add("text-color");
	    thoiGianTonTai.getStyleClass().add("text-color");
	    kinhDo.getStyleClass().add("text-color");
	    quocHieu.getStyleClass().add("text-color");
	    nguoiSangLap.getStyleClass().add("text-color");
	    kings.getStyleClass().add("text-color");
	    kings.setWrapText(true);

        gridPane.add(ten, 0, 0);
        gridPane.add(thoiGianTonTai, 0, 1);
        gridPane.add(kinhDo, 0, 2);
        gridPane.add(quocHieu, 0, 3);
        gridPane.add(nguoiSangLap, 0, 4);
        gridPane.add(kings, 0, 5);
        
        root.getChildren().addAll(imageView, gridPane);

        Scene scene = new Scene(root, 1100, 600);
        
        URL cssURL = null;
		try {
			cssURL = new File("Project/VietnamHistory/src/application/details/thoiky/style.css").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
