/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locarfx.Infra;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FabricaConexao {
     //Utilizado Singleton para a conexão, pois não é necessário mais de uma instância da classe para utilizá-la.
    private FabricaConexao() {}
        public static Connection getConexao(){
            try {
                SQLServerDataSource datasource = new SQLServerDataSource();
                
                datasource.setUser("sa");  
                datasource.setPassword("1toledo*");  
                datasource.setServerName("localhost");
                datasource.setInstanceName("localhost\\SQLSERVER");
                datasource.setPortNumber(1433);
                datasource.setDatabaseName("LocacaoVeiculo");  
                
                Connection connection = datasource.getConnection();
                return connection;
                
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
}
