package red.kalos.core.manager.shop.entity;

import org.bukkit.Material;

public class ItemInfo {
    public ItemInfo(String name,Material material,double money,int points){
        this.setName(name);
        this.setMaterial(material);
        this.setMoney(money);
        this.setPoints(points);
    }

    private String name;
    private Material material;
    private double money;
    private int points;


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
