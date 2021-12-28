import kim.pokemon.util.mojang.Mojang;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    static ArrayList<String> players = new ArrayList<>();

    public static void main(String[] args) {
    }

    /**
     * 获取所有在线玩家的正版UUID
     */
    public static void getAllPlayerInfo(){
        new Thread(()->{
            String test = new Mojang().getUUIDOfUsername("Red_Carl");
            players.add(test);
            System.out.println("OK");
        }).start();
    }
}
