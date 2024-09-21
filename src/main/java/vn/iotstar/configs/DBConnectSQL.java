package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQL {
	private final String serverName = "localhost";
    private final String dbName = "ltweb";
    private final String portNumber = "1433";
    private final String instance = ""; // MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "sa";
    private final String password = "tangthanh";

    public Connection getConnection() throws Exception {
    	Connection conn = null;
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";

            conn = DriverManager.getConnection(url, userID, password);
            if (instance == null || instance.trim().isEmpty()) {
                url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            }

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    public static void main(String [] args)
    {
    	try
    	{
    		System.out.println(new DBConnectSQL().getConnection());

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}
