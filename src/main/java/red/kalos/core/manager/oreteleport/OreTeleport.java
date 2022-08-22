package red.kalos.core.manager.oreteleport;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import red.kalos.core.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OreTeleport {
    private final Map<String, Boolean> IsOnline = new HashMap<>();
    private int X = 128;
    private int Y = 128;
    private int high = 50;
    private final List<ItemStack> ores = new ArrayList<>();
    private final ItemFactory it = Main.getInstance().getServer().getItemFactory();
    public void Replace(Block block) {
        if ("STONE".equals(block.getType().toString())) {
            if (!"AIR".equals(block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().isLiquid()) {
                if ("COAL_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("COAL_ORE"));
                }
                if ("DIAMOND_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("DIAMOND_ORE"));
                }
                if ("EMERALD_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("EMERALD_ORE"));
                }
                if ("GOLD_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("GOLD_ORE"));
                }
                if ("IRON_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("IRON_ORE"));
                }
                if ("REDSTONE_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("REDSTONE_ORE"));
                }
            }
        }
        else if ("COAL_ORE".equals(block.getType().toString())) {
            if (!"AIR".equals(block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().isLiquid() && !"AIR".equals(block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().isLiquid() && !"AIR".equals(block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().isLiquid() && !"AIR".equals(block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().isLiquid() && !"AIR".equals(block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().isLiquid() && !"AIR".equals(block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().getType().toString())) {
                block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().isLiquid();
            }
        }
        else if ("DIAMOND_ORE".equals(block.getType().toString())) {
            if (!"AIR".equals(block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().isLiquid()) {
                if (!"DIAMOND_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("STONE"));
                }
            }
        }
        else if ("EMERALD_ORE".equals(block.getType().toString())) {
            if (!"AIR".equals(block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().isLiquid()) {
                if (!"EMERALD_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("STONE"));
                }
            }
        }
        else if ("GOLD_ORE".equals(block.getType().toString())) {
            if (!"AIR".equals(block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().isLiquid()) {
                if (!"GOLD_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("STONE"));
                }
            }
        }
        else if ("IRON_ORE".equals(block.getType().toString())) {
            if (!"AIR".equals(block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().isLiquid()) {
                if (!"IRON_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("STONE"));
                }
            }
        }
        else if ("REDSTONE_ORE".equals(block.getType().toString())) {
            if (!"AIR".equals(block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(-1.0D, 0.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, 1.0D).getBlock().isLiquid() &&
                    !"AIR".equals(block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().getType().toString()) && !block.getLocation().add(0.0D, 0.0D, -1.0D).getBlock().isLiquid()) {
                if (!"REDSTONE_ORE".equals(block.getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    block.setType(Material.getMaterial("STONE"));
                }
            }
        }
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        this.IsOnline.put(event.getPlayer().getName(), Boolean.valueOf(true));
        BukkitTask time = (new BukkitRunnable() { public void run() {
            if (!(Boolean) OreTeleport.this.IsOnline.get(event.getPlayer().getName())) { cancel(); return; } if (event.getPlayer().getLocation().getY() < OreTeleport.this.high) for (int i = 2; i <= 10; i++) { for (int j = 2; j <= 10; j++) { for (int k = 2; k <= 10; k++) { if (!"AIR".equals(event.getPlayer().getLocation().add(-5.0D, -5.0D, -5.0D).add(i, j, k).getBlock().getType().toString())) OreTeleport.this.Replace(event.getPlayer().getLocation().add(-5.0D, -5.0D, -5.0D).add(i, j, k).getBlock());  }  }  }   } }).runTaskTimer((Plugin)this, 20L, 20L);

    }


    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        this.IsOnline.put(event.getPlayer().getName(), Boolean.valueOf(false));
    }


    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().getLocation().getY() < this.high) {

            if ("REDSTONE_ORE".equals(event.getBlock().getType().toString())) {
                if ("REDSTONE_ORE".equals(event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().setType(Material.getMaterial("STONE"));
                }

            } else if ("GLOWING_REDSTONE_ORE".equals(event.getBlock().getType().toString())) {
                if ("GLOWING_REDSTONE_ORE".equals(event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().setType(Material.getMaterial("STONE"));
                }

            } else if ("COAL_ORE".equals(event.getBlock().getType().toString())) {
                if ("COAL_ORE".equals(event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().setType(Material.getMaterial("STONE"));
                }

            } else if ("DIAMOND_ORE".equals(event.getBlock().getType().toString())) {
                if ("DIAMOND_ORE".equals(event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().setType(Material.getMaterial("STONE"));
                }

            } else if ("EMERALD_ORE".equals(event.getBlock().getType().toString())) {
                if ("EMERALD_ORE".equals(event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().setType(Material.getMaterial("STONE"));
                }

            } else if ("GOLD_ORE".equals(event.getBlock().getType().toString())) {
                if ("GOLD_ORE".equals(event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().setType(Material.getMaterial("STONE"));
                }

            } else if ("IRON_ORE".equals(event.getBlock().getType().toString()) &&
                    "IRON_ORE".equals(event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().getType().toString())) {
                event.getBlock().getLocation().add(this.X, 0.0D, this.Y).getBlock().setType(Material.getMaterial("STONE"));
            }
        }
    }
}
