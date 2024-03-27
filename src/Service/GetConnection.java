
package Service;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;

public class GetConnection {
    public static Connection getConnection(){
         SQLServerDataSource dataSource = new SQLServerDataSource();
         dataSource.setUser("sa");
         dataSource.setPassword("123456789");
         dataSource.setEncrypt(Boolean.FALSE);
         dataSource.setDatabaseName("QUANLYBANGIAY");
         dataSource.setPortNumber(1433);
         Connection connection = null;
         try {
             connection = dataSource.getConnection();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return connection;
     }
}
