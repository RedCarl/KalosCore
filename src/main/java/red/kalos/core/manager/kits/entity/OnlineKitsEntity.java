package red.kalos.core.manager.kits.entity;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: carl0
 * @DATE: 2022/8/17 17:30
 */
@Data
public class OnlineKitsEntity {
    String id;
    String name;
    String[] lores;
    long time;
    ItemStack[] items;
    double money;
    int points;
    String permission;

    public OnlineKitsEntity(String id, String name, String[] lores, long time, ItemStack[] items, double money, int points, String permission) {
        this.id = id;
        this.name = name;
        this.lores = lores;
        this.time = time;
        this.items = items;
        this.money = money;
        this.points = points;
        this.permission = permission;
    }
}
