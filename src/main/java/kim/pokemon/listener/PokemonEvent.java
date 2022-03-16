package kim.pokemon.listener;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.events.KeyEvent;
import com.pixelmonmod.pixelmon.api.events.PixelmonDeletedEvent;
import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.LegendaryCheckSpawnsEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.LegendarySpawnEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnActionPokemon;
import com.pixelmonmod.pixelmon.api.world.MutableLocation;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.ParticipantType;
import com.pixelmonmod.pixelmon.comm.packetHandlers.EnumKeyPacketMode;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumBossMode;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.pokespawn.SpawnTime;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.Random;

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

            //指定世界不刷新的宝可梦类型
            switch (world){
                case "plot":
                case "spawn":
                    if (entityPixelmon.isBossPokemon()){
                        event.setCanceled(true);
                    }
                    break;
            }

            //禁止刷新封禁的宝可梦
            if (Arrays.asList(PokeBlackList).contains(entityPixelmon.getPokemonName())){
                event.setCanceled(true);
            }

            //野外闪光宝可梦
            entityPixelmon.getPokemonData().setShiny(false);
            //野外特性宝可梦
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
            SpawnTime.playerStringHashMap.put(player,pokemon.getLocalizedName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            Bukkit.broadcastMessage(ColorParser.parse("&8[&6&l!&8] &7一只传说宝可梦 &c"+pokemon.getLocalizedName()+" &7出现在玩家 &c"+player.getName()+" &7附近快去寻找它。"));
        }

        //成功捕捉精灵事件
        if (forgeEvent.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture){
            CaptureEvent event = (CaptureEvent) forgeEvent.getForgeEvent();
            String player = event.player.displayName;
            String pokemon = event.getPokemon().getLocalizedName();
            boolean legendary = event.getPokemon().isLegendary();

            //数据记录
            if (legendary){
//                Main.getInstance().getPlayerEventDataSQLReader().addPlayerEvent(PlayerEventData.setPlayerEventData(player,"SuccessfulCaptureLegendary",pokemon,new Timestamp(System.currentTimeMillis())));
            }else {
//                Main.getInstance().getPlayerEventDataSQLReader().addPlayerEvent(PlayerEventData.setPlayerEventData(player,"SuccessfulCapture",pokemon,new Timestamp(System.currentTimeMillis())));
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
        //生蛋
        if (forgeEvent.getForgeEvent() instanceof BreedEvent.MakeEgg) {
            BreedEvent.MakeEgg event = (BreedEvent.MakeEgg)forgeEvent.getForgeEvent();
            Pokemon egg = event.getEgg();

            //带有红线不对蛋进行操作
            if (event.parent1.getHeldItem().field_151002_e!=null&&event.parent1.getHeldItem().field_151002_e.func_77658_a().equals("item.destiny_knot")){
                return;
            }else if (event.parent2.getHeldItem().field_151002_e!=null&&event.parent2.getHeldItem().field_151002_e.func_77658_a().equals("item.destiny_knot")){
                return;
            }

            int a = 0;
            for (int iv:egg.getIVs().getArray()) {
                if (iv==31){
                    a++;
                }
            }
            switch (a){
                case 6:
                    PokemonAPI.setIv(egg, new Random().nextInt(3)+4);
                    break;
                case 5:
                    PokemonAPI.setIv(egg, new Random().nextInt(6));
                    break;
                case 4:
                    PokemonAPI.setIv(egg, new Random().nextInt(5));
                    break;
                case 3:
                    PokemonAPI.setIv(egg, new Random().nextInt(4));
                    break;
                case 2:
                    PokemonAPI.setIv(egg, new Random().nextInt(3));
                    break;
                case 1:
                    PokemonAPI.setIv(egg, new Random().nextInt(2));
                    break;
            }
            event.setEgg(egg);
        }

        //孵化蛋
        if (forgeEvent.getForgeEvent() instanceof BreedEvent.CollectEgg) {

        }

        //精灵回收
        if (forgeEvent.getForgeEvent() instanceof PixelmonDeletedEvent) {
            PixelmonDeletedEvent event = (PixelmonDeletedEvent)forgeEvent.getForgeEvent();
            Player player = Bukkit.getPlayer(event.player.getPersistentID());
            Pokemon pokemon = event.pokemon;
            if (pokemon.isLegendary()&&PokemonAPI.getPokemonPoints(pokemon)!=0){
                int money = PokemonAPI.getPokemonPoints(pokemon);
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7系统回收了您的 &c"+pokemon.getLocalizedName()+" &7并给予您 &c"+money+" &7"+ Data.SERVER_POINTS+",请注意查收"));
                Main.ppAPI.giveAsync(player.getUniqueId(),money);
            }else {
                double money = PokemonAPI.getPokemonVault(pokemon);
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7系统回收了您的 &c"+pokemon.getLocalizedName()+" &7并给予您 &c"+money+" &7"+ Data.SERVER_VAULT+",请注意查收"));
                Main.econ.depositPlayer(player,money);
            }


        }

        //战斗结束
        if (forgeEvent.getForgeEvent() instanceof BattleEndEvent){
            BattleEndEvent event = (BattleEndEvent) forgeEvent.getForgeEvent();


            //与野外的宝可梦战斗结束
            if ((event.getPlayers().size()==1)&&(!event.abnormal)){
                if (event.results.size()<=2){
                    Player player = Bukkit.getPlayer(event.getPlayers().get(0).getPersistentID());
                    if (!player.getLocation().getWorld().getName().equals("spawn")){
                        for (BattleParticipant battleParticipant: event.results.keySet()) {
                            if (battleParticipant.isDefeated){
                                if (battleParticipant.checkPokemon()){
                                    if (battleParticipant.getType().equals(ParticipantType.WildPokemon)){
                                        EntityPixelmon pixelmon = (EntityPixelmon)battleParticipant.getEntity();
                                        int money = pixelmon.getStoragePokemonData().getLevel();
                                        player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您在战斗过程中获得了 &c"+money+" &7卡洛币。"));
                                        Main.econ.depositPlayer(player,money);
                                    }
                                }
                            }
                        }
                    }
                    //会员专属战斗结束后恢复精灵状态
                    if (player.hasPermission("group.pikanium")||player.hasPermission("group.eevee")){
                        PokemonAPI.setPokemonStater(player);
                    }
                }
            }


        }

        //神兽刷新查询
        if (forgeEvent.getForgeEvent() instanceof LegendaryCheckSpawnsEvent){
            LegendaryCheckSpawnsEvent event = (LegendaryCheckSpawnsEvent) forgeEvent.getForgeEvent();
            event.shouldShowTime = false;
            event.shouldShowChance = false;
        }

        //宝可梦进化事件EvolveEvent

        //案件
        if (forgeEvent.getForgeEvent() instanceof KeyEvent){
            KeyEvent event = (KeyEvent) forgeEvent.getForgeEvent();

            //丢出宝可梦
            if (event.key.equals(EnumKeyPacketMode.SendPokemon)){
                PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(Bukkit.getPlayer(event.player.displayName).getUniqueId());
                for (Pokemon p:playerPartyStorage.getAll()) {
                    if (p!=null&&p.getLocalizedName().equals("无极汰那")){
                        p.setGrowth(EnumGrowth.Microscopic);
                    }
                }
            }
        }
    }



}
