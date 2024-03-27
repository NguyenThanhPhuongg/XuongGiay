package Service;

import Model.MauSac;
import Model.Voucher;
import Repository.JdbcHelper;
import View.QuanLyVoucher;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import service.SellingApplicationImpl;

public class VoucherSevice extends SellingApplicationImpl<Voucher, Integer>{
   String insert = """
                   INSERT INTO [dbo].[Voucher]
                              ([Ma]
                              ,[Ten]
                              ,[NgayTao]
                              ,[GhiChu]
                              ,[TrangThai]
                              ,[NhanVien].[ID_NhanVien])
                        VALUES(?,?,?,?,?,?)
                   """;
   String update = """
                   UPDATE [dbo].[Voucher]
                      SET [Ma] = ?
                         ,[Ten] = ?
                         ,[NgayTao] = ?
                         ,[GhiChu] = ?
                         ,[TrangThai] = ?
                         ,[ID_NhanVien] = ?
                    WHERE ID = ?
                   """;
   String delede = """
                   DELETE FROM [dbo].[Voucher]
                         WHERE ID = ?
                   """;
   String selectAll = "SELECT * FROM Voucher";
   String selectById = "SELECT * FROM Voucher WHERE ID = ?";
   String selectByMa = "SELECT * FROM Voucher WHERE Ma = ?";

    @Override
    public void insert(Voucher entity) {
         JdbcHelper.update(insert, 
                 entity.getMa(),
                 entity.getTen(),
                 entity.isTrangThai(),
                 entity.getNgayTao(),
                 entity.getIdNhanVien(),
                 entity.getGhiChu()
                 );
    }
    @Override
    public void update(Voucher entity) {
        JdbcHelper.update(update, 
                entity.getMa(),
                 entity.getTen(),
                 entity.isTrangThai(),
                 entity.getNgayTao(),
                 entity.getIdNhanVien(),
                 entity.getGhiChu(),
                 entity.getId()
                );
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(delede, id);
    }
    
    @Override
    public Voucher selectById(Integer id) {
        List<Voucher> list = this.selectBySql(selectById, id);
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }
    public Voucher selectByMa(String ma){
        List<Voucher> list = this.selectBySql(selectByMa, ma);
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public List<Voucher> selectAll() {
        return this.selectBySql(selectAll);
    }

    @Override
    protected List<Voucher> selectBySql(String sql, Object... args) {
        List<Voucher> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                Voucher vc = new Voucher();
                vc.setId(rs.getInt("ID"));
                vc.setMa(rs.getString("Ma"));
                vc.setTen(rs.getString("Ten"));
                vc.setTrangThai(rs.getBoolean("TrangThai"));
                vc.setNgayTao(rs.getString("NgayTao"));
                vc.setIdNhanVien(rs.getInt("ID_NhanVien"));
                vc.setGhiChu(rs.getString("GhiChu"));
                list.add(vc);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Voucher> selectByKeyWord(String keyWord){
        String query = "SELECT * FROM Voucher WHERE Ten LIKE ?";
        return this.selectBySql(query, "%"+keyWord+"%%");
    }
    public List<Voucher> searchKeyWord(String keyWord, int pages, int limit){
        String sql = """
                     SELECT * 
                     FROM 
                     (
                              SELECT *
                                FROM [dbo].[Voucher]
                        WHERE Ten LIKE ?
                     ) AS FilteredResults
                     ORDER BY ID
                     OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                     """;
        return this.selectBySql(sql, 
                "%"+keyWord+"%%",(pages - 1)*limit,limit
                );
    }
}
