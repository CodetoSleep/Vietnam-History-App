module VietnamHistory {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires java.sql;
    requires javafx.graphics;
	requires org.json;
	requires org.jsoup;

    exports application.main;
    exports model.ditichlichsu;
    exports model.lehoi;
    exports model.nhanvatlichsu;
    exports model.sukienlichsu;
    exports model.thoiky;
	
    opens model.ditichlichsu to gson;
    opens model.lehoi to gson;
    opens model.nhanvatlichsu to gson;
    opens model.sukienlichsu to gson;
    opens model.thoiky to gson;
    opens application.main to javafx.fxml;
}
