package kim.pokemon.database;


import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.recharge.entity.PlayerRecharge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理竞技场数据
 */
public class GlazedPayDataSQLReader {
    private static Connection conn;
    /**
     * 查询表
     */
    public static void selectTable(){
        conn = SQLConnection.conn;
        try {
            String select_Table = "SELECT * FROM " + Data.GLAZED_PAY_DATA + "";
            PreparedStatement table = conn.prepareStatement(select_Table);
            table.execute();
            Main.log("/*/正在连接 " + Data.GLAZED_PAY_DATA + " 数据库...");
        } catch (SQLException e) {
            Main.log("/*/未检测到 " + Data.GLAZED_PAY_DATA + " 数据库表,正在创建...");
            createTable();
            Main.log("/*/创建 " + Data.GLAZED_PAY_DATA + " 数据库表成功,连接数据库...");
        }
    }

    /**
     * 创建表
     */
    public static void createTable(){
        try {
            String create_table = "CREATE TABLE IF NOT EXISTS " + Data.GLAZED_PAY_DATA + " (`id`  int UNSIGNED NOT NULL AUTO_INCREMENT ,`name`  varchar(40) NOT NULL ,`amount`  double NOT NULL ,`time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,PRIMARY KEY (`id`),INDEX `name_key` (`name`) USING BTREE) DEFAULT CHARSET=utf8;";
            PreparedStatement create = conn.prepareStatement(create_table);
            create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询玩家的充值信息
     * @param name 玩家ID
     * @return 充值信息
     */
    public static PlayerRecharge getPlayer(String name) {
        double amount = 0.0D;
        try {
            String select_data = "SELECT SUM(`amount`) FROM "+Data.GLAZED_PAY_DATA+" WHERE `name` = ?;";
            PreparedStatement select = conn.prepareStatement(select_data);
            select.setString(1, name);
            ResultSet set = select.executeQuery();

            if (set.next()) {
                amount = set.getDouble(1);
            }

            set.close();
            return new PlayerRecharge(name, amount);
        } catch (SQLException var5) {
            var5.printStackTrace();
            return new PlayerRecharge(name,-1);
        }
    }
}
