
package Model;

public class HoaDon {
    private int ID ;
    private String ma ;
    private String ngayTao ;
    private double tongTien ;
    private boolean TrangThai ;
    private String GhiChu ;

    public HoaDon() {
    }

    public HoaDon(int ID, String ma, String ngayTao, double tongTien, boolean TrangThai, String GhiChu) {
        this.ID = ID;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    
    
}
