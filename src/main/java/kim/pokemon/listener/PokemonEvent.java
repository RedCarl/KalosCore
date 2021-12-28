package kim.pokemon.listener;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.events.PixelmonDeletedEvent;
import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.LegendarySpawnEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnActionPokemon;
import com.pixelmonmod.pixelmon.api.world.MutableLocation;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.ParticipantType;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.PlayerEventDataSQLReader;
import kim.pokemon.entity.PlayerEventData;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.Timestamp;
import java.util.Arrays;

public class PokemonEvent implements Listener {
    public static String[] PokeBlackList = new String[]{
            "Ditto",
            "Manaphy",
            "Celesteela",
            "Kartana"
    };

    @EventHandler
    public void onForgeEvent(final ForgeEvent forgeEvent) {
        String world;
        EntityPixelmon entityPixelmon;

        //普通生成事件
        if (forgeEvent.getForgeEvent() instanceof SpawnEvent) {
            SpawnEvent event = (SpawnEvent)forgeEvent.getForgeEvent();

            if (!(event.action instanceof SpawnActionPokemon)) {
                return;
            }

            world = event.action.spawnLocation.location.world.func_72912_H().func_76065_j();
            entityPixelmon = ((SpawnActionPokemon)event.action).getOrCreateEntity();

            switch (world){
                case "plot":
                case "lobby":
                    if (entityPixelmon.isLegendary()||entityPixelmon.isBossPokemon()){
                        event.setCanceled(true);
                    }
                    break;
            }

            if (Arrays.asList(PokeBlackList).contains(entityPixelmon.getPokemonName())){
                event.setCanceled(true);
            }

            entityPixelmon.getPokemonData().setShiny(false);
            entityPixelmon.getPokemonData().setAbilitySlot(0);
        }

        //传说宝可梦生成事件
        if (forgeEvent.getForgeEvent() instanceof LegendarySpawnEvent.DoSpawn) {
            LegendarySpawnEvent.DoSpawn event = (LegendarySpawnEvent.DoSpawn)forgeEvent.getForgeEvent();
            if (event.isCanceled())
                return;
            Pokemon pokemon = ((SpawnActionPokemon)event.action).getOrCreateEntity().getStoragePokemonData();
            MutableLocation mutableLocation = event.action.spawnLocation.location;
            World w = Bukkit.getWorld(mutableLocation.world.func_72912_H().func_76065_j());
            double x = mutableLocation.pos.func_177958_n();
            double y = mutableLocation.pos.func_177956_o();
            double z = mutableLocation.pos.func_177952_p();
            Location location = new Location(w, x, y, z);
            Player player = PokemonAPI.getRandPlayer(location);

            Bukkit.broadcastMessage(ColorParser.parse("&8[&6&l!&8] &7一只传说宝可梦 &c"+pokemon.getLocalizedName()+" &7出现在 &C玩家 "+player.getName()+" 附近("+x+","+z+") &7快去寻找它。"));
        }

        //成功捕捉精灵事件
        if (forgeEvent.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture){
            CaptureEvent event = (CaptureEvent) forgeEvent.getForgeEvent();
            String player = event.player.displayName;
            String pokemon = event.getPokemon().getLocalizedName();
            boolean legendary = event.getPokemon().isLegendary();

            //数据记录
            if (legendary){
                PlayerEventDataSQLReader.addPlayerEvent(PlayerEventData.setPlayerEventData(player,"SuccessfulCaptureLegendary",pokemon,new Timestamp(System.currentTimeMillis())));
            }else {
                PlayerEventDataSQLReader.addPlayerEvent(PlayerEventData.setPlayerEventData(player,"SuccessfulCapture",pokemon,new Timestamp(System.currentTimeMillis())));
            }
        }

        //牧场孵化
        if (forgeEvent.getForgeEvent() instanceof BreedEvent.AddPokemon) {
            BreedEvent.AddPokemon event = (BreedEvent.AddPokemon)forgeEvent.getForgeEvent();
            Player player = Bukkit.getPlayer(event.owner);
            int i = 0;
            StatsType[] var5 = StatsType.getStatValues();

            for (StatsType type : var5) {
                if (event.pokemon.getIVs().get(type) >= 31) {
                    ++i;
                }

                if (i > Main.getInstance().getConfig().getInt("BreedLimit.Ivs")) {
                    event.setCanceled(true);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，牧场禁止繁殖 &c"+Main.getInstance().getConfig().getInt("BreedLimit.Ivs")+"v &7以上的宝可梦."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.closeInventory();
                }
            }

            for (String name : Main.getInstance().getConfig().getStringList("BreedLimit.Pokemon")) {
                if (event.pokemon.isPokemon(EnumSpecies.valueOf(name))) {
                    event.setCanceled(true);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，牧场禁止繁殖 &c"+name+" &7宝可梦."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.closeInventory();
                }
            }

            if ((!Main.getInstance().getConfig().getBoolean("BreedLimit.Ability")) && (event.pokemon.getAbilitySlot() == 2)) {
                event.setCanceled(true);
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，牧场禁止繁殖 &c特性 &7宝可梦."));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                player.closeInventory();
            }
        }
        //获取牧场蛋
        if (forgeEvent.getForgeEvent() instanceof BreedEvent.CollectEgg) {
            BreedEvent.CollectEgg event = (BreedEvent.CollectEgg)forgeEvent.getForgeEvent();
            Pokemon egg = event.getEgg();

            int a = 0;
            for (int iv:egg.getIVs().getArray()) {
                if (iv==31){
                    a++;
                }
            }
            if (a==6){
                PokemonAPI.setIv(egg, 5);
            }
            event.setEgg(egg);
        }

            //精灵回收
        if (forgeEvent.getForgeEvent() instanceof PixelmonDeletedEvent) {
            PixelmonDeletedEvent event = (PixelmonDeletedEvent)forgeEvent.getForgeEvent();
            Player player = Bukkit.getPlayer(event.player.displayName);
            Pokemon pokemon = event.pokemon;
            double money = PokemonAPI.getPokemonVault(pokemon);
            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_YES,1,1);
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7系统回收了您的 &c"+pokemon.getLocalizedName()+" &7并给予您 &c"+money+" &7"+ Data.SERVER_VAULT+",请注意查收"));
            Main.econ.depositPlayer(player,money);

            //数据记录
            PlayerEventDataSQLReader.addPlayerEvent(PlayerEventData.setPlayerEventData(player.getName(),"PokemonRecycle ",pokemon.getLocalizedName()+" "+money,new Timestamp(System.currentTimeMillis())));
        }

        //战斗结束
        if (forgeEvent.getForgeEvent() instanceof BattleEndEvent){
            BattleEndEvent event = (BattleEndEvent) forgeEvent.getForgeEvent();
            //与野外的宝可梦战斗结束
            if ((event.getPlayers().size()==1)&&(!event.abnormal)){
                if (event.results.size()<=2){
                    Player player = Bukkit.getPlayer(event.getPlayers().get(0).getPersistentID());
                    Pokemon pokemon;
                    if (!player.getLocation().getWorld().getName().equals("spawn")){
                        for (BattleParticipant battleParticipant: event.results.keySet()) {
                            if (battleParticipant.isDefeated){
                                if (battleParticipant.checkPokemon()){
                                    if (battleParticipant.getType().equals(ParticipantType.WildPokemon)){
                                        pokemon = PokemonSpec.from(battleParticipant.getDisplayName()).create();
                                        PlayerEventDataSQLReader.addPlayerEvent(PlayerEventData.setPlayerEventData(player.getName(),"BattleEndEvent",pokemon.getLocalizedName(),new Timestamp(System.currentTimeMillis())));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
