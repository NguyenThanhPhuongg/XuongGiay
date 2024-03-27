
package Service;

import Model.SanPhamChiTiet;
import Model.Voucher;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SPCTService {
    PreparedStatement statement = null;
    Connection connection = null;
    ResultSet result = null;
    public List<SanPhamChiTiet> getAll(){
        List<SanPhamChiTiet> list = new ArrayList<>();
        Connection connection = GetConnection.getConnection();
        try {
            String query = """
                           SELECT SanPhamChiTiet.ID,SanPhamChiTiet.TrangThai,Gia,SoLuong,TenSP,Size.Ten as 'Size', MauSac.Ten as 'Mau Sac',ChatLieu.Ten as 'Chat Lieu',XuatXu.Ten as 'Xuat Xu',DanhMuc.Ten as 'Danh muc' from SanPhamChiTiet 
                             join SanPham on SanPhamChiTiet.ID_SP = SanPham.ID
                             join Size on SanPhamChiTiet.ID_Size = Size.ID
                             join MauSac on SanPhamChiTiet.ID_MauSac = MauSac.ID
                             join ChatLieu on SanPhamChiTiet.ID_ChatLieu = ChatLieu.ID
                             join XuatXu on SanPhamChiTiet.ID_XuatXu = XuatXu.ID
                             join DanhMuc on SanPhamChiTiet.ID_DanhMuc = DanhMuc.ID
                           """;
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()){
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(result.getInt("ID"));
                spct.setTrangThai(result.getBoolean("TrangThai"));
                spct.setGia(result.getDouble("Gia"));
                spct.setSoLuong(result.getInt("SoLuong"));
                spct.setTenSP(result.getString("TenSP"));
                spct.setSize(result.getDouble("Size"));
                spct.setMauSac(result.getString("Mau Sac"));
                spct.setChatLieu(result.getString("Chat Lieu"));
                spct.setXuatXu(result.getString("Xuat Xu"));
                spct.setDanhMuc(result.getString("Danh Muc"));
                list.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                // Thực hiện giải phóng bộ nhớ
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SPCTService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    public List<SanPhamChiTiet> getTrangThai() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            connection = GetConnection.getConnection();
            String query = "SELECT TrangThai FROM SanPhamChiTiet";
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
            SanPhamChiTiet spct;
            while (result.next()) {
                spct = new SanPhamChiTiet();
                spct.setTrangThai(result.getBoolean("TrangThai"));
                list.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<SanPhamChiTiet> getSize() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            connection = GetConnection.getConnection();
            String query = "SELECT Ten FROM Size";
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
            SanPhamChiTiet spct;
            while (result.next()) {
                spct = new SanPhamChiTiet();
                spct.setSize(result.getDouble("Ten"));
                list.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<SanPhamChiTiet> getDanhMuc() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            connection = GetConnection.getConnection();
            String query = "SELECT Ten FROM DanhMuc";
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
            SanPhamChiTiet spct;
            while (result.next()) {
                spct = new SanPhamChiTiet();
                spct.setDanhMuc(result.getString("Ten"));
                list.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean Them(SanPhamChiTiet spct) {
        boolean check = false;
        try {
            connection = GetConnection.getConnection();
            String query = """
                           INSERT INTO [dbo].[SanPhamChiTiet]
                                           ([TrangThai]
                                           ,[Gia]
                                           ,[SoLuong]
                                           ,[ID_SP]
                                           ,[ID_Size]
                                           ,[ID_MauSac]
                                           ,[ID_ChatLieu]
                                           ,[ID_XuatXu]
                                           ,[ID_DanhMuc])
                                     VALUES(?,?,?,?,?,?,?,?,?)
                           """;
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, spct.isTrangThai());
            statement.setDouble(2, spct.getGia());
            statement.setInt(3, spct.getSoLuong());
            statement.setString(4, spct.getTenSP());
            statement.setDouble(5, spct.getSize());
            statement.setString(6, spct.getMauSac());
            statement.setString(7, spct.getChatLieu());
            statement.setString(8, spct.getXuatXu());
            statement.setString(9, spct.getDanhMuc());
            check = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
}
