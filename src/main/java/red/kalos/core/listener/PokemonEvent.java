package red.kalos.core.listener;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.*;
import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.LegendaryCheckSpawnsEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.LegendarySpawnEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnActionPokemon;
import com.pixelmonmod.pixelmon.api.world.MutableLocation;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.ParticipantType;
import com.pixelmonmod.pixelmon.comm.packetHandlers.EnumKeyPacketMode;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.drops.DroppedItem;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.pokespawn.SpawnTime;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;

import java.util.Arrays;
import java.util.Random;

public class PokemonEvent implements Listener {

    @EventHandler
    public void onForgeEvent(final ForgeEvent forgeEvent) {

        //普通生成事件
        if (forgeEvent.getForgeEvent() instanceof SpawnEvent) {
            String[] PokeBlackList = new String[]{
                    "Ditto",
                    "Manaphy",
                    "Celesteela",
                    "Kartana"
            };

            String world;
            EntityPixelmon entityPixelmon;

            SpawnEvent event = (SpawnEvent)forgeEvent.getForgeEvent();

            if (!(event.action instanceof SpawnActionPokemon)) {
                return;
            }

            world = event.action.spawnLocation.location.world.func_72912_H().func_76065_j();
            entityPixelmon = ((SpawnActionPokemon)event.action).getOrCreateEntity();

            //指定世界不刷新的宝可梦类型
            switch (world){
                case "plot":
                case "training":
                case "pokerank":
                case "spawn":
                    event.setCanceled(true);
                    break;
            }

            //禁止刷新封禁的宝可梦
            if (Arrays.asList(PokeBlackList).contains(entityPixelmon.getPokemonName())){
                event.setCanceled(true);
            }

            //设置野外宝可梦MT
            if (entityPixelmon.getPokemonData().getAbilitySlot()>0){
                int a = new Random().nextInt(100);
                if (a<=50){
                    entityPixelmon.getPokemonData().setAbilitySlot(0);
                }
            }
            //设置野生宝可梦闪光
            if (entityPixelmon.getPokemonData().isShiny()){
                int b = new Random().nextInt(100);
                if (b<=30){
                    entityPixelmon.getPokemonData().setShiny(false);
                }
            }




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

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            Bukkit.broadcastMessage(ColorParser.parse("&r"));
            Bukkit.broadcastMessage(ColorParser.parse("&r"));
            Bukkit.broadcastMessage(ColorParser.parse("&7------ [ &6传奇宝可梦 &7] ------"));
            Bukkit.broadcastMessage(ColorParser.parse("&7宝可梦名称: &c"+pokemon.getLocalizedName()));
            Bukkit.broadcastMessage(ColorParser.parse("&7附近的玩家: &a"+player.getName()));
            Bukkit.broadcastMessage(ColorParser.parse("&7宝可梦坐标: &f"+location.getBlockX()+"&7,&f"+location.getBlockY()+"&7,&f"+location.getBlockZ()));
            Bukkit.broadcastMessage(ColorParser.parse("&r"));
            Bukkit.broadcastMessage(ColorParser.parse("&r"));
//            Bukkit.broadcastMessage(ColorParser.parse("&8[&6&l!&8] &7一只传说宝可梦 &c"+pokemon.getLocalizedName()+" &7出现在玩家 &c"+player.getName()+" &7附近快去寻找它。"));
            SpawnTime.isSpawner = true;
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
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，牧场禁止繁殖 &c"+ Main.getInstance().getConfig().getInt("BreedLimit.Ivs")+"v &7以上的宝可梦."));
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
//
//            if ((!Main.getInstance().getConfig().getBoolean("BreedLimit.Ability")) && (event.pokemon.getAbilitySlot() == 2)) {
//                event.setCanceled(true);
//                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，牧场禁止繁殖 &c特性 &7宝可梦."));
//                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                player.closeInventory();
//            }
        }

        //生蛋
        if (forgeEvent.getForgeEvent() instanceof BreedEvent.MakeEgg) {
            BreedEvent.MakeEgg event = (BreedEvent.MakeEgg)forgeEvent.getForgeEvent();
            Pokemon egg = event.getEgg();

            //特性管辖
            if (egg.getAbilitySlot()>0){
                int b = new Random().nextInt(100);
                if (b<=97){
                    egg.setAbilitySlot(0);
                }
            }

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

        //精灵回收
        if (forgeEvent.getForgeEvent() instanceof PixelmonDeletedEvent) {
            PixelmonDeletedEvent event = (PixelmonDeletedEvent)forgeEvent.getForgeEvent();
            Player player = Bukkit.getPlayer(event.player.getPersistentID());
            Pokemon pokemon = event.pokemon;
            double money = PokemonAPI.getPokemonVault(pokemon);
            player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7系统回收了您的 &c"+pokemon.getLocalizedName()+" &7并给予您 &c"+money+" &7"+ Data.SERVER_VAULT+",请注意查收"));
            Main.getEcon().depositPlayer(player,money);
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
                                        Main.getEcon().depositPlayer(player,money);
                                    }
                                }
                            }
                        }
                    }
//                    //会员专属战斗结束后恢复精灵状态
//                    if (player.hasPermission("group.pikanium")||player.hasPermission("group.eevee")){
//                        PokemonAPI.setPokemonStater(player);
//                    }
                }
            }


        }

        //神兽刷新查询
        if (forgeEvent.getForgeEvent() instanceof LegendaryCheckSpawnsEvent){
            LegendaryCheckSpawnsEvent event = (LegendaryCheckSpawnsEvent) forgeEvent.getForgeEvent();
            event.shouldShowTime = false;
            event.shouldShowChance = false;
        }

        //按键事件
        if (forgeEvent.getForgeEvent() instanceof KeyEvent){
            KeyEvent event = (KeyEvent) forgeEvent.getForgeEvent();

            //丢出宝可梦
            if (event.key.equals(EnumKeyPacketMode.SendPokemon)){
                PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(event.player.getPersistentID());
                for (Pokemon p:playerPartyStorage.getAll()) {
                    if (p!=null&&p.getLocalizedName().equals("无极汰那")){
                        p.setGrowth(EnumGrowth.Microscopic);
                    }
                }
            }
        }

        //成功抓捕宝可梦
        if (forgeEvent.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture) {
            CaptureEvent.SuccessfulCapture event = (CaptureEvent.SuccessfulCapture) forgeEvent.getForgeEvent();

            //设置野外宝可梦MT
            int a = new Random().nextInt(100);
            if (a<=80){
                event.getPokemon().getPokemonData().setAbilitySlot(0);
            }

            //禁止捕捉BOSS宝可梦
            if (event.getPokemon().isBossPokemon()){
                event.setCanceled(true);
            }
        }

        //宝可梦掉落物
        if (forgeEvent.getForgeEvent() instanceof DropEvent) {
            DropEvent event = (DropEvent) forgeEvent.getForgeEvent();

            //设置野外宝可梦掉落红线的概率
            for (DroppedItem droppedItem:event.getDrops()) {
                if (droppedItem.itemStack.field_151002_e.func_77658_a().equals("item.destiny_knot")){
                    int a = new Random().nextInt(100);
                    if (a<=80){
                        event.player.getBukkitEntity().getPlayer().sendMessage("DeBug: 01");
                        event.getDrops().remove(droppedItem);
                    }
                }
            }
        }
    }



}
