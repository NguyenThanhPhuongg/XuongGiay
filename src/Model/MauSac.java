
package Model;


public class MauSac {
    private int id ;
    private String mauSac ;
    private boolean trangThai ;

    public MauSac() {
    }

    public MauSac(int id, String mauSac, boolean trangThai) {
        this.id = id;
        this.mauSac = mauSac;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    
}
