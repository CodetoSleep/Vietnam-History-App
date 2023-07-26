package application.details.nhanvatlichsu;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.thoiky.ThoiKy;
import model.nhanvatlichsu.NhanVatLichSu;
import model.nhanvatlichsu.Vua;

public class NhanVatDetails {
	public NhanVatDetails(NhanVatLichSu curSelect, ObservableList<ThoiKy> listThoiKy, ObservableList<Vua> listVua) {
        AnchorPane root = new AnchorPane();
        Stage stage = new Stage();
        stage.setTitle("Nhân Vật Lịch Sử Details");
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        
        ImageView imageView = new ImageView();
        imageView.setFitHeight(490.0);
        imageView.setFitWidth(389.0);
        imageView.setLayoutX(44.0);
        imageView.setLayoutY(55.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");
        
        URL imageURL = null;
		try {
			imageURL = new File("Project/VietnamHistory/src/application/images/nhanvatLS.jpg").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Image image = new Image(imageURL.toExternalForm());
		imageView.setImage(image);
        
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(394.0);
        gridPane.setLayoutY(55.0);
        gridPane.setPrefHeight(490.0);
        gridPane.setPrefWidth(662.0);
        gridPane.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");
        gridPane.setPadding(new Insets(10, 30, 20, 30)); 

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
        rowConstraints6.setPrefHeight(10.0);
        rowConstraints6.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints6);

        RowConstraints rowConstraints7 = new RowConstraints();
        rowConstraints7.setMinHeight(10.0);
        rowConstraints7.setPrefHeight(200.0);
        rowConstraints7.setMaxHeight(200.0);
        rowConstraints7.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        gridPane.getRowConstraints().add(rowConstraints7);
        
        Label ten = new Label("- Họ và Tên: " + curSelect.getTen());
        Label tenKhac = new Label("- Tên Khác: " + curSelect.getTenKhac());
        Label namSinh = new Label("- Năm sinh: " + curSelect.getNamSinh());
        Label namMat = new Label("- Năm mất: " + curSelect.getNamMat());
        Label queQuan = new Label("- Quê Quán: " + curSelect.getQueQuan());
        String strThoiKy = "";
        ArrayList<ThoiKy> newThoiKy = new ArrayList<ThoiKy>();
        for (int i = 0; i < curSelect.getThoiKy().size(); i++) {
            strThoiKy += curSelect.getThoiKy().get(i).getTen() + ", ";
            for (int j = 0; j < listThoiKy.size(); j++) {

                if (curSelect.getThoiKy().get(i).getTen().toLowerCase()
                        .indexOf(listThoiKy.get(j).getTen().toLowerCase()) != -1) {
                    newThoiKy.add(listThoiKy.get(j));
//                    System.out.println(listThoiKy.get(j).getNguoiSangLap().getTen());
                }
            }
        }
        curSelect.setThoiKy(newThoiKy);
        Label thoiKy = new Label("- Thuộc Thời Kỳ: " + strThoiKy);
        Label ghiChu = new Label("- Ghi chú: " + curSelect.getGhiChu());

        ten.getStyleClass().add("text-color");
        tenKhac.getStyleClass().add("text-color");
        queQuan.getStyleClass().add("text-color");
        namSinh.getStyleClass().add("text-color");
        namMat.getStyleClass().add("text-color");
        thoiKy.getStyleClass().add("text-color");
        ghiChu.getStyleClass().add("text-color");
        ghiChu.setWrapText(true);
        
        gridPane.add(ten, 0, 0);
        gridPane.add(tenKhac, 0, 1);
        gridPane.add(queQuan, 0, 2);
        gridPane.add(namSinh, 0, 3);
        gridPane.add(namMat, 0, 4);
        gridPane.add(thoiKy, 0, 5);
        gridPane.add(ghiChu, 0, 6);

        root.getChildren().addAll(imageView, gridPane);

        Scene scene = new Scene(root, 1100, 600);
        
        URL cssURL = null;
		try {
			cssURL = new File("Project/VietnamHistory/src/application/details/nhanvatlichsu/style.css").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
