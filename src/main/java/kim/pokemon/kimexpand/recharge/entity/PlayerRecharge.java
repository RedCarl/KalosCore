package kim.pokemon.kimexpand.recharge.entity;

/**
 * 玩家充值订单和累计充值数量
 */
public class PlayerRecharge {
    private String player;
    private double amount;

    public PlayerRecharge(String player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int compareTo(PlayerRecharge o) {
        return Double.compare(o.amount, this.amount);
    }

    public String toString() {
        return "PlayerRankData@[Player=" + this.player + ", Amount=" + this.amount + "]";
    }

}
