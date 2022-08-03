package kim.pokemon.manager.ranking.entity;

import org.jetbrains.annotations.NotNull;

public class MoneyEntity implements Comparable<MoneyEntity>{
    private String name;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public int compareTo(@NotNull MoneyEntity o) {
        int result;
        result = (int) (this.money-o.getMoney());

        return result;
    }
}
