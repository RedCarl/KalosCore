package kim.pokemon.entity;

import kim.pokemon.Main;
import kim.pokemon.database.PlayerEventDataSQLReader;
import org.bukkit.Bukkit;

import java.sql.Timestamp;

public class PlayerEventData {
    private int id;
    private String name;
    private String event;
    private String value;
    private String server;
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public static PlayerEventData setPlayerEventData(String name,String event,String value,Timestamp time){
        PlayerEventData playerEventData = new PlayerEventData();
        playerEventData.setName(name);
        playerEventData.setEvent(event);
        playerEventData.setValue(value);
        playerEventData.setServer(Main.luckPerms.getServerName());
        playerEventData.setTime(time);
        return playerEventData;
    }

}
