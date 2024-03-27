
package Model;

public class SanPhamChiTiet {
    private int id ;
    private boolean trangThai;
    private double gia ;
    private int SoLuong;
    private String TenSP;
    private double Size ;
    private String mauSac ;
    private String chatLieu;
    private String xuatXu ;
    private String danhMuc ;

    public SanPhamChiTiet() {
    }  

    public SanPhamChiTiet(int id, boolean trangThai, double gia, int SoLuong, String TenSP, double Size, String mauSac, String chatLieu, String xuatXu, String danhMuc) {
        this.id = id;
        this.trangThai = trangThai;
        this.gia = gia;
        this.SoLuong = SoLuong;
        this.TenSP = TenSP;
        this.Size = Size;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.xuatXu = xuatXu;
        this.danhMuc = danhMuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public double getSize() {
        return Size;
    }

    public void setSize(double Size) {
        this.Size = Size;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }
    
}
