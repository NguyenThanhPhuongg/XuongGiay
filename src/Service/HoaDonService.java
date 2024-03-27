package Service;

import Model.HoaDon;
import Repository.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import service.SellingApplicationImpl;

public class HoaDonService extends SellingApplicationImpl<HoaDon, Integer> {

    String them = """
                  INSERT INTO [dbo].[HoaDon]
                             ([Ma]
                             ,[NgayTao]
                             ,[TongTien]
                             ,[TrangThai]
                             ,[GhiChu])
                       VALUES(?,?,?,?,?)
                  """;
    String sua = """
                 UPDATE [dbo].[HoaDon]
                    SET [Ma] = ?
                       ,[NgayTao] = ?
                       ,[TongTien] = ?
                       ,[TrangThai] = ?
                       ,[GhiChu] = ?
                  WHERE ID = ?
                 """;
    String xoa = "DELETE * FROM HoaDon";
    String selectAll = "SELECT * FROM HoaDon";
    String selectById = "SELECT * FROM HoaDon WHERE ID = ?";
    String selectByMa = "SELECT * FROM HoaDon WHERE Ma = ?";

    @Override
    public void insert(HoaDon entity) {
        JdbcHelper.update(them,
                entity.getMa(),
                entity.getNgayTao(),
                entity.getTongTien(),
                entity.isTrangThai(),
                entity.getGhiChu()
        );
    }

    @Override
    public void update(HoaDon entity) {
        JdbcHelper.update(sua,
                entity.getMa(),
                entity.getNgayTao(),
                entity.getTongTien(),
                entity.isTrangThai(),
                entity.getGhiChu(),
                entity.getID()
        );
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(xoa, id);
    }

    @Override
    public HoaDon selectById(Integer id) {
        List<HoaDon> list = this.selectBySql(selectById, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public HoaDon selectByMa(String ma) {
        List<HoaDon> list = this.selectBySql(selectByMa, ma);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(selectAll);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setID(rs.getInt("ID"));
                hd.setMa(rs.getString("Ma"));
                hd.setNgayTao(rs.getString("NgayTao"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setGhiChu(rs.getString("GhiChu"));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<HoaDon> selectByKeyWord(String keyword) {
        String sql = """
                           SELECT *
                            FROM [dbo].[HoaDon]
                             WHERE Ma LIKE ?
                     """;
        return this.selectBySql(sql, "%" + keyword + "%%");
    }

    public List<HoaDon> searchKeyWord(String keyWord, int pages, int limit) {
        String sql = """
                     SELECT * 
                     FROM 
                     (
                              SELECT *
                                FROM [dbo].[HoaDon]
                        WHERE Ma LIKE ?
                     ) AS FilteredResults
                     ORDER BY ID
                     OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                     """;
        return this.selectBySql(sql,
                "%" + keyWord + "%%", (pages - 1) * limit, limit);
    }

}
