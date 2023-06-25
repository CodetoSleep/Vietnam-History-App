package application;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import model.nhanvatlichsu.Vua;
import model.nhanvatlichsu.NhanVatLichSu;
import model.thoiky.ThoiKy;
import model.sukienlichsu.SuKienLichSu;
import model.sukienlichsu.ChienTranh;
import model.lehoi.LeHoi;
import model.ditichlichsu.DiTichLichSu;
import application.details.chientranh.ChienTranhDetails;
import application.details.ditich.DiTichDetails;
import application.details.lehoi.LeHoiDetails;
import application.details.nhanvatlichsu.NhanVatDetails;
import application.details.sukien.SuKienDetails;
import application.details.vua.VuaDetails;
import application.details.thoiky.ThoiKyDetails;
import application.readdata.ReadData;
import application.search.Search;

public class Controller {
    @FXML
    private Button btnHomepage;
    
    @FXML
    private Button btnVua;
    
    @FXML
    private Button btnNhanVat;
    
    @FXML
    private Button btnThoiKy;
    
    @FXML
    private Button btnSuKien;
    
    @FXML
    private Button btnChienTranh;
    
    @FXML
    private Button btnDiTich;
    
    @FXML
    private Button btnLeHoi;
    
    @FXML
    private Pane pnlHomepage;
    
    @FXML
    private TextField textField;
    
    @FXML
    private BorderPane borderPane;
    
    // Swap between pages 
    public void handleClicks(ActionEvent actionEvent) throws IOException {
    	ObservableList<NhanVatLichSu> listObservablesNhanVatLichSu = new ReadData<NhanVatLichSu>()
                .FromJsonToArray("src/data/nhanvatlichsu/nhanVatLichSu.json", NhanVatLichSu.class);
    	ObservableList<Vua> listObservablesVua = new ReadData<Vua>()
                .FromJsonToArray("src/data/nhanvatlichsu/vua.json", Vua.class);
    	ObservableList<ThoiKy> listObservablesThoiKy = new ReadData<ThoiKy>()
                .FromJsonToArray("src/data/thoiky/thoiKy.json", ThoiKy.class);
    	ObservableList<SuKienLichSu> listObservablesSuKienLichSu = new ReadData<SuKienLichSu>()
                .FromJsonToArray("src/data/sukienlichsu/SuKien.json", SuKienLichSu.class);
    	ObservableList<ChienTranh> listObservablesChienTranh = new ReadData<ChienTranh>()
                .FromJsonToArray("src/data/sukienlichsu/ChienTranh.json", ChienTranh.class);
    	ObservableList<LeHoi> listObservablesLeHoi = new ReadData<LeHoi>()
                .FromJsonToArray("src/data/lehoi/leHoi.json", LeHoi.class);
    	ObservableList<DiTichLichSu> listObservablesDiTichLichSu = new ReadData<DiTichLichSu>()
                .FromJsonToArray("src/data/ditichlichsu/DiTich.json", DiTichLichSu.class);
    	
        if (actionEvent.getSource() == btnHomepage) {
            pnlHomepage.toFront();
        }
        
        if (actionEvent.getSource() == btnVua) {
        	String[] colNameVua = { "Tên Vua", "Năm Trị Vì", "Miếu Hiệu", "Thụy Hiệu", "Niên Hiệu" };
            String[] vuaStr = { "ten", "namTriVi", "mieuHieu", "thuyHieu", "nienHieu" };
            TableView<Vua> tableViewVua = new TableViewCustom<Vua>().makeTableView(colNameVua, vuaStr);
            tableViewVua.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            	if (e.getClickCount() > 1) {
                    Vua curSelect = tableViewVua.getSelectionModel().getSelectedItem();
                    new VuaDetails(curSelect);
                }
            });
            Search<Vua> searchVua = new Search<Vua>();
            tableViewVua.setItems(searchVua.searchList(listObservablesVua, textField, Vua.class));
            borderPane.setCenter(tableViewVua);
            borderPane.toFront();
        }
        
        if (actionEvent.getSource() == btnNhanVat) {
        	String[] colNameNhanVat = { "Tên Nhân Vật", "Năm Sinh", "Năm Mất", "Quê Quán" };
            String[] nhanVatStr = { "ten", "namSinh", "namMat",  "queQuan" };
            TableView<NhanVatLichSu> tableViewNhanVat = new TableViewCustom<NhanVatLichSu>().makeTableView(colNameNhanVat, nhanVatStr);
            tableViewNhanVat.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            	if (e.getClickCount() > 1) {
                    NhanVatLichSu curSelect = tableViewNhanVat.getSelectionModel().getSelectedItem();
                    new NhanVatDetails(curSelect, listObservablesThoiKy, listObservablesVua);
                }
            });
            Search<NhanVatLichSu> searchNhanVat = new Search<NhanVatLichSu>();
            tableViewNhanVat.setItems(searchNhanVat.searchList(listObservablesNhanVatLichSu, textField, NhanVatLichSu.class));
            borderPane.setCenter(tableViewNhanVat);
            borderPane.toFront();
        }
        
        if(actionEvent.getSource() == btnThoiKy) {
        	String[] colNameThoiKy = { "Tên Thời Kỳ","Thời Gian", "Quốc Hiệu", "Kinh Đô" };
            String[] thoiKyStr = { "ten", "thoiGianTonTai", "quocHieu", "kinhDo" };
            TableView<ThoiKy> tableViewThoiKy = new TableViewCustom<ThoiKy>().makeTableView(colNameThoiKy, thoiKyStr);
            tableViewThoiKy.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            	if (e.getClickCount() > 1) {
                    ThoiKy curSelect = tableViewThoiKy.getSelectionModel().getSelectedItem();
                    new ThoiKyDetails(curSelect, listObservablesVua);
                }
            });
            Search<ThoiKy> searchThoiKy = new Search<ThoiKy>();
            tableViewThoiKy.setItems(searchThoiKy.searchList(listObservablesThoiKy, textField, ThoiKy.class));
            borderPane.setCenter(tableViewThoiKy);
            borderPane.toFront();
        }
        
        if(actionEvent.getSource() == btnSuKien) {
        	String[] colNameSuKien = { "Tên Sự Kiện", "Thời gian diễn ra", "Địa điểm" };
            String[] suKienStr = { "ten", "thoiGian", "diaDiem" };
            TableView<SuKienLichSu> tableViewSuKien = new TableViewCustom<SuKienLichSu>().makeTableView(colNameSuKien, suKienStr);
            tableViewSuKien.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if (e.getClickCount() > 1) {
                    SuKienLichSu curSelect = tableViewSuKien.getSelectionModel().getSelectedItem();
                    new SuKienDetails(curSelect);
                }
            });
            Search<SuKienLichSu> searchSuKien = new Search<SuKienLichSu>();
            tableViewSuKien.setItems(searchSuKien.searchList(listObservablesSuKienLichSu, textField, SuKienLichSu.class));
            borderPane.setCenter(tableViewSuKien);
        	borderPane.toFront();
        }
    
        if(actionEvent.getSource()==btnChienTranh) {
        	String[] colNameChienTranh = { "Tên Cuộc Chiến", "Thời Gian", "Bên Thứ Nhất", "Bên Thứ Hai" };
            String[] chienTranhStr = { "ten", "thoiGian", "benThuNhat", "benThuHai" };
            TableView<ChienTranh> tableViewChienTranh = new TableViewCustom<ChienTranh>().makeTableView(colNameChienTranh, chienTranhStr);
            tableViewChienTranh.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if (e.getClickCount() > 1) {
                    ChienTranh curSelect = tableViewChienTranh.getSelectionModel().getSelectedItem();
                    new ChienTranhDetails(curSelect);
                }
            });
            Search<ChienTranh> searchChienTranh = new Search<ChienTranh>();
            tableViewChienTranh.setItems(searchChienTranh.searchList(listObservablesChienTranh, textField, ChienTranh.class));
            borderPane.setCenter(tableViewChienTranh);
        	borderPane.toFront();
        }
        
        if(actionEvent.getSource()==btnDiTich) {
        	String[] colNameDiTich = { "Tên Di tích", "Địa điểm", "Loại Xếp Hạng", "Xếp Hạng" };
            String[] diTichStr = { "ten", "diaDiem", "loaiXepHang", "xepHang" };
            TableView<DiTichLichSu> tableViewDiTich = new TableViewCustom<DiTichLichSu>().makeTableView(colNameDiTich, diTichStr);
            tableViewDiTich.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if (e.getClickCount() > 1) {
                    DiTichLichSu curSelect = tableViewDiTich.getSelectionModel().getSelectedItem();
                    new DiTichDetails(curSelect, listObservablesNhanVatLichSu);
                }
            });
            Search<DiTichLichSu> searchDiTich = new Search<DiTichLichSu>();
            tableViewDiTich.setItems(searchDiTich.searchList(listObservablesDiTichLichSu, textField, DiTichLichSu.class));
            borderPane.setCenter(tableViewDiTich);
            borderPane.toFront();
        }
        
        if(actionEvent.getSource()==btnLeHoi) {
        	String[] colNameLeHoi = { "Tên Lễ Hội", "Thời Gian", "Địa điểm" };
            String[] leHoiStr = { "ten", "thoiGian", "diaDiem" };
            TableView<LeHoi> tableViewLeHoi = new TableViewCustom<LeHoi>().makeTableView(colNameLeHoi, leHoiStr);
            tableViewLeHoi.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if (e.getClickCount() > 1) {
                    LeHoi curSelect = tableViewLeHoi.getSelectionModel().getSelectedItem();
                    new LeHoiDetails(curSelect, listObservablesNhanVatLichSu);
                }
            });
            Search<LeHoi> searchLeHoi = new Search<LeHoi>();
            tableViewLeHoi.setItems(searchLeHoi.searchList(listObservablesLeHoi, textField, LeHoi.class));
            borderPane.setCenter(tableViewLeHoi);
            borderPane.toFront();
        }
    }
}

class TableViewCustom<T> {
    public TableView<T> makeTableView(String[] colNameT, String[] TStr) {
        TableView<T> tableViewT = new TableView<T>();
        tableViewT.getColumns().clear();
        for (int i = 0; i < TStr.length; i++) {
            TableColumn<T, String> ColT = new TableColumn<T, String>(colNameT[i]);
            ColT.prefWidthProperty().bind(tableViewT.widthProperty().multiply((double) 1 / TStr.length));
            ColT.setCellValueFactory(new PropertyValueFactory<T, String>(TStr[i]));
            tableViewT.getColumns().add(ColT);
        }
        return tableViewT;
    }
}
