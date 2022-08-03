package kim.pokemon.manager.recharge.shop.entity;

import org.bukkit.Material;

public class ItemInfo {
    public ItemInfo(String name,Material material,double money,double points){
        this.setName(name);
        this.setMaterial(material);
        this.setMoney(money);
        this.setPoints(points);
    }

    private String name;
    private Material material;
    private double money;
    private double points;


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

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
