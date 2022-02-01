package kim.pokemon.database;


import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.entity.PlayerEventData;
import kim.pokemon.kimexpand.premium.entity.PlayerVIP;
import kim.pokemon.kimexpand.recharge.entity.PlayerRecharge;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理竞技场数据
 */
public class PlayerEventDataSQLReader {
    private static Connection conn;
    /**
     * 查询表
     */
    public static void selectTable(){
        conn = SQLConnection.conn;
        try {
            String select_Table = "SELECT * FROM " + Data.PLAYER_EVENT_DATA + "";
            PreparedStatement table = conn.prepareStatement(select_Table);
            table.execute();
            Main.log("/*/正在连接 " + Data.PLAYER_EVENT_DATA + " 数据库...");
        } catch (SQLException e) {
            Main.log("/*/未检测到 " + Data.PLAYER_EVENT_DATA + " 数据库表,正在创建...");
            createTable();
            Main.log("/*/创建 " + Data.PLAYER_EVENT_DATA + " 数据库表成功,连接数据库...");
        }
    }

    /**
     * 创建表
     */
    public static void createTable(){
        try {
            String create_table = "CREATE TABLE IF NOT EXISTS " + Data.PLAYER_EVENT_DATA + " (`id`  int UNSIGNED NOT NULL AUTO_INCREMENT ,`name`  varchar(40) NOT NULL ,`event`  varchar(40) NOT NULL ,`value`  varchar(40) NOT NULL  ,`server`  varchar(40) NOT NULL ,`time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,PRIMARY KEY (`id`) ,INDEX `name_key` (`name`) USING BTREE) DEFAULT CHARSET=utf8;";
            PreparedStatement create = conn.prepareStatement(create_table);
            create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询玩家指定时间段事件
     * @param onTime 开始
     * @param endTime 结束
     * @return 数额
     */
    public static List<PlayerEventData> getPlayerEventDataTime(String event,String server, String onTime, String endTime) {
        List<PlayerEventData> list = new ArrayList<>();
        try {

            String select_data;
            PreparedStatement select;

            select_data = "SELECT * FROM "+Data.PLAYER_EVENT_DATA+" WHERE time >= '"+onTime+"' AND time <= '"+endTime+"' AND `event` = ? AND `server` = ?;";
            select = conn.prepareStatement(select_data);
            select.setString(1, event);
            select.setString(2, server);

            ResultSet set = select.executeQuery();

            while (set.next()){
                PlayerEventData playerEventData = new PlayerEventData();
                playerEventData.setId(set.getInt("id"));
                playerEventData.setName(set.getString("name"));
                playerEventData.setEvent(set.getString("event"));
                playerEventData.setValue(set.getString("value"));
                playerEventData.setServer(set.getString("server"));
                playerEventData.setTime(set.getTimestamp("time"));
                list.add(playerEventData);
            }
            set.close();
            return list;
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        return list;
    }
    /**
     * 查询该玩家的所有事件
     * @param name 玩家ID
     * @return 玩家Rank信息
     */
    public static List<PlayerEventData> getPlayerEventData(String name, String event, String server) {
        List<PlayerEventData> list = new ArrayList<>();
        try {

            String select_data;
            PreparedStatement select;

            if (event!=null){
                select_data = "SELECT * FROM "+Data.PLAYER_EVENT_DATA+" WHERE `name` = ? AND `event` = ? AND `server` = ?;";
                select = conn.prepareStatement(select_data);
                select.setString(1, name);
                select.setString(2, event);
                select.setString(3,server);
            }else {
                select_data = "SELECT * FROM "+Data.PLAYER_EVENT_DATA+" WHERE `name` = ? AND `server` = ?;";
                select = conn.prepareStatement(select_data);
                select.setString(1, name);
                select.setString(2,server);
            }

            ResultSet set = select.executeQuery();

            while (set.next()){
                PlayerEventData playerEventData = new PlayerEventData();
                playerEventData.setId(set.getInt("id"));
                playerEventData.setName(set.getString("name"));
                playerEventData.setEvent(set.getString("event"));
                playerEventData.setValue(set.getString("value"));
                playerEventData.setServer(set.getString("server"));
                playerEventData.setTime(set.getTimestamp("time"));
                list.add(playerEventData);
            }
            set.close();
            return list;
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        return list;
    }

    /**
     * 添加玩家的事件
     */
    public static boolean addPlayerEvent(PlayerEventData playerEventData) {
        try {
            String select_data = "INSERT INTO " + Data.PLAYER_EVENT_DATA + "(name,event,value,server,time) VALUES (?,?,?,?,?);";
            PreparedStatement select = conn.prepareStatement(select_data);
            select.setString(1, playerEventData.getName());
            select.setString(2, playerEventData.getEvent());
            select.setString(3, playerEventData.getValue());
            select.setString(4, playerEventData.getServer());
            select.setTimestamp(5, playerEventData.getTime());
            return select.execute();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        return false;
    }
}
