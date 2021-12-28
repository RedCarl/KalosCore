package kim.pokemon.database;


import kim.pokemon.Main;
import kim.pokemon.configFile.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 处理竞技场数据
 */
public class PokemonBanDataSQLReader {
    private static Connection conn;
    private static String[] bandrops = new String[0];
    private static String[] banpokemons = new String[0];
    /**
     * 查询表
     */
    public static void selectTable(){
        conn = SQLConnection.conn;
        try {
            String select_Table = "SELECT * FROM " + Data.POKEMON_BAN_DATA + "";
            PreparedStatement table = conn.prepareStatement(select_Table);
            table.execute();
            Main.log("/*/正在连接 " + Data.POKEMON_BAN_DATA + " 数据库...");
        } catch (SQLException e) {
            Main.log("/*/未检测到 " + Data.POKEMON_BAN_DATA + " 数据库表,正在创建...");
            createTable();
            Main.log("/*/创建 " + Data.POKEMON_BAN_DATA + " 数据库表成功,连接数据库...");
        }
    }

    /**
     * 创建表
     */
    public static void createTable(){
        try {
            String create_table = "CREATE TABLE IF NOT EXISTS "+Data.POKEMON_BAN_DATA+"(key_name TINYTEXT NOT NULL,data TEXT NOT NULL)";
            PreparedStatement create = conn.prepareStatement(create_table);
            create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void refreshBan() {
        try {
            ResultSet set_bandrops = conn.createStatement().executeQuery("SELECT * from " + Data.POKEMON_BAN_DATA + " where key_name = 'bandrops';");
            if (set_bandrops.next()) {
                bandrops = set_bandrops.getString("data").toUpperCase().split(",");
            } else {
                conn.createStatement().execute("insert into " + Data.POKEMON_BAN_DATA + "(key_name,data) values ('bandrops','');");
            }

            ResultSet set_banpokemons = conn.createStatement().executeQuery("SELECT * from " + Data.POKEMON_BAN_DATA + " where key_name = 'banpokemons';");
            if (set_banpokemons.next()) {
                banpokemons = set_banpokemons.getString("data").toUpperCase().split(",");
            } else {
                conn.createStatement().execute("insert into " + Data.POKEMON_BAN_DATA + "(key_name,data) values ('banpokemons','');");
            }
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public static String combine(String[] arg) {
        StringBuilder sb = new StringBuilder();

        for (String s : arg) {
            if (!s.isEmpty()) {
                sb.append(s);
                sb.append(",");
            }
        }

        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static void addDrops(String name) {
        refreshBan();
        bandrops = Arrays.copyOf(bandrops, bandrops.length + 1);
        bandrops[bandrops.length - 1] = name;

        try {
            conn.createStatement().execute("update " + Data.POKEMON_BAN_DATA + " set data='" + combine(bandrops) + "' where key_name = 'bandrops'");
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public static void removeDrops(String name) {
        refreshBan();
        List<String> s = new ArrayList(Arrays.asList(bandrops));
        s.remove(name.toUpperCase());
        bandrops = s.toArray(new String[0]);

        try {
            conn.createStatement().execute("update " + Data.POKEMON_BAN_DATA + " set data='" + combine(bandrops) + "' where key_name = 'bandrops'");
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public static void addPokemons(String name) {
        refreshBan();
        banpokemons = Arrays.copyOf(banpokemons, banpokemons.length + 1);
        banpokemons[banpokemons.length - 1] = name;

        try {
            conn.createStatement().execute("update " + Data.POKEMON_BAN_DATA + " set data='" + combine(banpokemons) + "' where key_name = 'banpokemons'");
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public static void removePokemons(String name) {
        refreshBan();
        List<String> s = new ArrayList(Arrays.asList(banpokemons));
        s.remove(name.toUpperCase());
        banpokemons = s.toArray(new String[0]);

        try {
            conn.createStatement().execute("update " + Data.POKEMON_BAN_DATA + " set data='" + combine(banpokemons) + "' where key_name = 'banpokemons'");
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public static List<String> getBanDrops() {
        return Arrays.asList(bandrops);
    }

    public static List<String> getBanPokemons() {
        return Arrays.asList(banpokemons);
    }
}
