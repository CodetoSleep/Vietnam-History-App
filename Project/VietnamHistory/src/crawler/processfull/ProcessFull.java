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
    public void Combine() throws IOException{
        ThoiKyFull thoiKyFull = new ThoiKyFull();
        thoiKyFull.Combine();
        thoiKyFull.WriteJson();

        NhanVatLichSuFull nhanVatLichSuFull = new NhanVatLichSuFull();
        nhanVatLichSuFull.Combine();
        nhanVatLichSuFull.WriteJson();

        SuKienLichSuFull suKienLichSuFull = new SuKienLichSuFull();
        suKienLichSuFull.Combine();
        suKienLichSuFull.WriteJson();

        ChienTranhFull chienTranhFull = new ChienTranhFull();
        chienTranhFull.Combine();
        chienTranhFull.WriteJson();

        DiTichFull diTichFull = new DiTichFull();
        diTichFull.Combine();
        diTichFull.WriteJson();

        LeHoiFull leHoiFull = new LeHoiFull();
        leHoiFull.Combine();
        leHoiFull.WriteJson();
    }

    public static void main(String[] args) throws IOException {
        ProcessFull prc = new ProcessFull();
        prc.Combine();
    }
}
