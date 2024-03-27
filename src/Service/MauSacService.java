package Service;

import Model.MauSac;
import Repository.JdbcHelper;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import service.SellingApplicationImpl;

public class MauSacService extends SellingApplicationImpl<MauSac, Integer>{

    String insert_sql = """
                        INSERT INTO [dbo].[MauSac]
                                   ([Ten]
                                   ,[TrangThai])
                             VALUES(?,?)
                        """;
    String update_sql = """
                        UPDATE [dbo].[MauSac]
                           SET [Ten] = ?
                              ,[TrangThai] = ?
                         WHERE ID = ?
                        """;
    String delete_sql = """
                        DELETE FROM [dbo].[MauSac]
                        WHERE ID = ?
                        """;
    String select_all = """
                        select * from MauSac
                        """;
    String selectById = """
                        select * from MauSac
                        WHERE ID = ?
                        """;

    @Override
    public void insert(MauSac entity) {
        JdbcHelper.update(insert_sql,
                entity.getMauSac(), entity.isTrangThai());
                
    }

    @Override
    public void update(MauSac entity) {
        JdbcHelper.update(update_sql,
                entity.getMauSac(), entity.isTrangThai());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public MauSac selectById(Integer id) {
        List<MauSac> list = this.selectBySql(selectById, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<MauSac> selectAll() {
                return this.selectBySql(select_all);
    }

    @Override
    protected List<MauSac> selectBySql(String sql, Object... args) {
        List<MauSac> list = new ArrayList<>();
        try {

            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("ID"));
                ms.setMauSac(rs.getString("Ten"));
                ms.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(ms);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<MauSac> selectByKeyWord(String keyword) {
        String sql = """
                           SELECT *
                            FROM [dbo].[MauSac]
                             WHERE Ten LIKE ?
                     """;
        return this.selectBySql(sql, "%" + keyword + "%%");
    }

    public List<MauSac> searchKeyWord(String keyWord, int pages, int limit) {
        String sql = """
                     SELECT * 
                     FROM 
                     (
                              SELECT *
                                FROM [dbo].[MauSac]
                        WHERE Ten LIKE ?
                     ) AS FilteredResults
                     ORDER BY ID
                     OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                     """;
        return this.selectBySql(sql,
                "%" + keyWord + "%%", (pages - 1) * limit, limit);
    }
}
