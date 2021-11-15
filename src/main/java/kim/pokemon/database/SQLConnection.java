package kim.pokemon.database;

import kim.pokemon.configFile.GlazedPay.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection conn;

    public static void initialize() {
        try {

            Class.forName(Data.DRIVER);
            conn = DriverManager.getConnection("jdbc:mysql://" + Data.URL + ":" + Data.PORT + "/" + Data.DATABASE, Data.USER, Data.PASS);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
