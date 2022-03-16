package kim.pokemon.database;


import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.premium.entity.PlayerVIP;
import org.bukkit.entity.Player;

import java.sql.*;

/**
 * 处理竞技场数据
 */
public class PremiumPlayerDataSQLReader {
    private Connection conn;

    public void initialize() {
        try {

            Class.forName(Data.DRIVER);
            conn = DriverManager.getConnection("jdbc:mysql://" + Data.URL + ":" + Data.PORT + "/" + Data.DATABASE, Data.USER, Data.PASS);
            selectTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询表
     */
    public void selectTable(){

        try {
            String select_Table = "SELECT * FROM " + Data.PREMIUM_DATA + "";
            PreparedStatement table = conn.prepareStatement(select_Table);
            table.execute();
            Main.log("/*/正在连接 " + Data.PREMIUM_DATA + " 数据库...");
        } catch (SQLException e) {
            Main.log("/*/未检测到 " + Data.PREMIUM_DATA + " 数据库表,正在创建...");
            createTable();
            Main.log("/*/创建 " + Data.PREMIUM_DATA + " 数据库表成功,连接数据库...");
        }
    }

    /**
     * 创建表
     */
    public void createTable(){
        try {
            String create_table = "CREATE TABLE IF NOT EXISTS " + Data.PREMIUM_DATA + " (`id`  int UNSIGNED NOT NULL AUTO_INCREMENT ,`name`  varchar(40) NOT NULL ,`rank`  varchar(40) NOT NULL ,`time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,PRIMARY KEY (`id`) ,`server`  varchar(40) NOT NULL ,INDEX `name_key` (`name`) USING BTREE) DEFAULT CHARSET=utf8;";
            PreparedStatement create = conn.prepareStatement(create_table);
            create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询玩家VIP到期时间
     * @param name 玩家ID
     * @return 玩家Rank信息
     */
    public PlayerVIP getRank(String name,String rank,String server) {
        PlayerVIP playerVIP = new PlayerVIP();
        try {

            if (rank==null){
                String select_data = "SELECT * FROM "+Data.PREMIUM_DATA+" WHERE `name` = ? AND `server` = ?;";
                PreparedStatement select = conn.prepareStatement(select_data);
                select.setString(1, name);
                select.setString(2, server);
                ResultSet set = select.executeQuery();

                if (set.next()) {
                    playerVIP.setId(set.getInt("id"));
                    playerVIP.setName(set.getString("name"));
                    playerVIP.setRank(set.getString("rank"));
                    playerVIP.setTime(set.getTimestamp("time"));
                    playerVIP.setServer(set.getString("server"));
                    set.close();
                    return playerVIP;
                }
            }
            String select_data = "SELECT * FROM "+Data.PREMIUM_DATA+" WHERE `name` = ? AND `rank` = ? AND `server` = ?;";
            PreparedStatement select = conn.prepareStatement(select_data);
            select.setString(1, name);
            select.setString(2, rank);
            select.setString(3, server);
            ResultSet set = select.executeQuery();

            if (set.next()) {
                playerVIP.setId(set.getInt("id"));
                playerVIP.setName(set.getString("name"));
                playerVIP.setRank(set.getString("rank"));
                playerVIP.setTime(set.getTimestamp("time"));
                playerVIP.setServer(set.getString("server"));
                set.close();
                return playerVIP;
            }

        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        return null;
    }

    /**
     * 删除玩家Rank
     * @return 玩家Rank信息
     */
    public void deleteRank(Player player) {
        try {
            String select_data = "DELETE FROM "+Data.PREMIUM_DATA+" WHERE name=?";
            PreparedStatement select = conn.prepareStatement(select_data);
            select.setString(1, player.getName());
            select.execute();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
    }

    /**
     * 开通VIP项目
     * @param playerVIP 信息
     */
    public boolean addRank(PlayerVIP playerVIP) {
        try {
            String select_data = "INSERT INTO " + Data.PREMIUM_DATA + "(name,rank,time,server) VALUES (?,?,?,?);";
            PreparedStatement select = conn.prepareStatement(select_data);
            select.setString(1, playerVIP.getName());
            select.setString(2, playerVIP.getRank());
            select.setTimestamp(3, playerVIP.getTime());
            select.setString(4, playerVIP.getServer());
            return select.execute();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        return false;
    }

    /**
     * 续费项目
     * @param playerVIP 信息
     */
    public boolean updateRank(PlayerVIP playerVIP) {
        try {
            String select_data = "UPDATE " + Data.PREMIUM_DATA + " SET rank=?,time=? WHERE name = ? AND server = ?";
            PreparedStatement select = conn.prepareStatement(select_data);
            select.setString(1, playerVIP.getRank());
            select.setTimestamp(2, playerVIP.getTime());
            select.setString(3, playerVIP.getName());
            select.setString(4, playerVIP.getServer());
            return select.execute();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        return false;
    }
}
