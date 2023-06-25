package crawler.processfull;

import java.io.IOException;

import crawler.ditichlichsu.DiTichFull;
import crawler.lehoi.LeHoiFull;
import crawler.nhanvatlichsu.NhanVatLichSuFull;
import crawler.sukienlichsu.ChienTranhFull;
import crawler.sukienlichsu.SuKienLichSuFull;
import crawler.thoiky.ThoiKyFull;
import crawler.util.dataout.ICombine;

public class ProcessFull implements ICombine {
    public ProcessFull() {
        super();
    }
    
    @Override
    public void combine() throws IOException{
        ThoiKyFull thoiKyFull = new ThoiKyFull();
        thoiKyFull.combine();
        thoiKyFull.writeJson();

        NhanVatLichSuFull nhanVatLichSuFull = new NhanVatLichSuFull();
        nhanVatLichSuFull.combine();
        nhanVatLichSuFull.writeJson();

        SuKienLichSuFull suKienLichSuFull = new SuKienLichSuFull();
        suKienLichSuFull.combine();
        suKienLichSuFull.writeJson();

        ChienTranhFull chienTranhFull = new ChienTranhFull();
        chienTranhFull.combine();
        chienTranhFull.writeJson();

        DiTichFull diTichFull = new DiTichFull();
        diTichFull.combine();
        diTichFull.writeJson();

        LeHoiFull leHoiFull = new LeHoiFull();
        leHoiFull.combine();
        leHoiFull.writeJson();
    }

    public static void main(String[] args) throws IOException {
        ProcessFull prc = new ProcessFull();
        prc.combine();
    }
}
