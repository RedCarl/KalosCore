package red.kalos.core.manager.questmanager.listener;

import catserver.api.bukkit.event.ForgeEvent;
import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.ParticipantType;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import red.kalos.core.Main;
import red.kalos.core.manager.questmanager.manager.QuestManager;
import red.kalos.core.manager.questmanager.quest.TaskType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;


public class QuestListener implements Listener {


    public void initialize() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            Long time = System.currentTimeMillis();
            for (Player player : Bukkit.getOnlinePlayers()) {
                QuestManager.handleMessage(player.getName(), TaskType.TIME_MESSAGE, new Object[]{time});
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            int dayInWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            

            if (hour == 8 && minute == 0 && second == 0) {
                QuestManager.refreshDaily();
            }
            if (hour == 8 && minute == 0 && second == 0 && dayInWeek == Calendar.SUNDAY) {
                QuestManager.refreshWeek();
            }

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                QuestManager.distributeQuest(onlinePlayer);
            }

        }, 0, 20);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerBattleWin(ForgeEvent forgeEvent) {
        if (forgeEvent.getForgeEvent() instanceof BattleEndEvent) {
            BattleEndEvent event = (BattleEndEvent) forgeEvent.getForgeEvent();
            if ((event.getPlayers().size() == 1) && (!event.abnormal) && event.results.size() <= 2) {
                Player player = Bukkit.getPlayer(event.getPlayers().get(0).getPersistentID());
                if (!player.getLocation().getWorld().getName().equals("spawn")) {
                    for (BattleParticipant battleParticipant : event.results.keySet()) {
                        if (battleParticipant.isDefeated) {
                            if (battleParticipant.checkPokemon()) {
                                if (battleParticipant.getType().equals(ParticipantType.WildPokemon)) {
                                    WildPixelmonParticipant a = (WildPixelmonParticipant) battleParticipant;
                                    ArrayList<EntityPixelmon> entityPixelmons = Lists.newArrayList();
                                    for (PixelmonWrapper pixelmonWrapper : a.allPokemon) {
                                        entityPixelmons.add(pixelmonWrapper.entity);
                                    }
                                    QuestManager.handleMessage(player.getName(), TaskType.WIN_MESSAGE, new Object[]{entityPixelmons});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEggSpawn(ForgeEvent forgeEvent) {
        if (forgeEvent.getForgeEvent() instanceof BreedEvent.CollectEgg) {
            BreedEvent.CollectEgg event = (BreedEvent.CollectEgg) forgeEvent.getForgeEvent();
            Player player = Bukkit.getPlayer(event.owner);
            QuestManager.handleMessage(player.getName(), TaskType.HATCH_MESSAGE, new Object[]{event.getEgg()});
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPokemonCatch(ForgeEvent forgeEvent) {
        if (forgeEvent.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture) {
            CaptureEvent.SuccessfulCapture event = (CaptureEvent.SuccessfulCapture) forgeEvent.getForgeEvent();
            UUID uuid = event.player.func_110124_au();
            Player player = Bukkit.getPlayer(uuid);
            String player_id = player.getName();
            EntityPixelmon entityPixelmon = event.getPokemon();
            QuestManager.handleMessage(player_id, TaskType.CATCH_MESSAGE, new Object[]{entityPixelmon});
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCraft(CraftItemEvent event) {
        int amount = 1;
        if (event.isShiftClick()) {
            final ItemStack recipeResult = event.getRecipe().getResult();
            final int resultAmt = recipeResult.getAmount(); // Bread = 1, Cookie = 8, etc.
            int leastIngredient = -1;
            for (ItemStack item : event.getInventory().getMatrix()) {
                if (item != null && !item.getType().equals(Material.AIR)) {
                    final int re = item.getAmount() * resultAmt;
                    if (leastIngredient == -1 || re < leastIngredient) {
                        leastIngredient = item.getAmount() * resultAmt;
                    }
                }
            }
            amount = new ItemStack(recipeResult.getType(), leastIngredient, recipeResult.getDurability()).getAmount();
        }

        for (int i = 0; i < amount; i++) {
            QuestManager.handleMessage(event.getWhoClicked().getName(),TaskType.CRAFT_MASSAGE,new Object[]{event.getCurrentItem()});
        }

    }
}
