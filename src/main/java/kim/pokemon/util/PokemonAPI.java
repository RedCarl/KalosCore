package kim.pokemon.util;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.storage.PCStorage;
import com.pixelmonmod.pixelmon.battles.BattleRegistry;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.controller.BattleControllerBase;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.comm.packetHandlers.OpenScreen;
import com.pixelmonmod.pixelmon.comm.packetHandlers.clientStorage.newStorage.pc.ClientChangeOpenPC;
import com.pixelmonmod.pixelmon.comm.packetHandlers.clientStorage.newStorage.pc.ClientInitializePC;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGuiScreen;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.battle.EnumBattleEndCause;
import com.pixelmonmod.pixelmon.enums.forms.IEnumForm;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import kim.pokemon.Main;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PokemonAPI {
    /**
     * 获取宝可梦是不是闪光
     * @param pokemon 宝可梦
     * @return 自定义内容
     */
    public static String getPokemonName(Pokemon pokemon){

        String pokemonName = pokemon.getLocalizedName();

        if (pokemon.isLegendary()){
            pokemonName=pokemonName+" &c[传奇]";
        }

        if (pokemon.isShiny()){
            pokemonName="&e"+pokemonName;
        }

        return ColorParser.parse("&f"+pokemonName);
    }

    /**
     * 获取宝可梦是不是闪光
     * @param pokemon 宝可梦
     * @return 自定义内容
     */
    public static String isShiny(Pokemon pokemon){
        if (pokemon.isShiny()){
            return ColorParser.parse("&a✔");
        }
        return ColorParser.parse("&c✘");
    }

    /**
     * 获取宝可梦是不是闪光
     * @param attack 招式内容
     * @return 招式名 或者 无招式
     */
    public static String getMove(Attack attack){
        if (attack != null){
            return attack.getMove().getLocalizedName();
        }
        return "&c无招式";
    }

    /**
     * 给这个玩家打开PC
     * @param player 玩家
     */
    public static void openPlayerPC(Player player){
        player.closeInventory();
        Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
            EntityPlayerMP entityPlayerMP = Pixelmon.storageManager.getParty(player.getUniqueId()).getPlayer();
            PCStorage pc = Pixelmon.storageManager.getPC(entityPlayerMP, null);
            Pixelmon.network.sendTo(new ClientInitializePC(pc), entityPlayerMP);
            Pixelmon.network.sendTo(new ClientChangeOpenPC(pc.uuid), entityPlayerMP);
            pc.sendContents(entityPlayerMP);
            OpenScreen.open(entityPlayerMP, EnumGuiScreen.PC);
        });
    }

    /**
     * 恢复这个玩家背包里所有宝可梦的状态
     * @param player 玩家
     */
    public static void setPokemonStater(Player player){
        PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(player.getUniqueId());
        playerPartyStorage.heal();
        //进入延迟状态
        MainMenu.HealSleep.put(player,System.currentTimeMillis());
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7哇，背包里的宝可梦们都恢复健康了!"));
    }

    /**
     * 按宝可梦名字生成宝可梦
     * @param pokemon 宝可梦名字
     * @return 宝可梦
     */
    public static Pokemon SpawnPokemon(String pokemon){
        PokemonSpec spec = PokemonSpec.from(pokemon);

        return spec.create();
    }

    /**
     * 给玩家随机获取一只宝可梦
     * @return 宝可梦
     */
    public static Pokemon getRandomPokemon(){
        EnumSpecies.getNameList();
        PokemonSpec spec = PokemonSpec.from(EnumSpecies.randomPoke().getPokemonName());

        return spec.create();
    }

    /**
     * 给玩家随机获取一直顶级传说宝可梦
     * @return 宝可梦
     */
    public static Pokemon getRandomLegendaryMaxPokemon(){
        String[] PokeBlackList = new String[]{
                "calyrex",
                "glastrier",
                "groudon",
                "kyogre",
                "zamazenta",
                "zacian",
                "eternatus",
                "mewtwo",
                "urshifu",
                "spectrier",
                "lunala",
                "solgaleo",
                "type_null",
                "ho_oh",
                "lugia",
                "yveltal",
                "giratina",
                "xerneas",
                "dialga",
                "palkia",
                "zygarde",
                "arceus",
                "zekrom",
                "reshiram",
                "kyurem"
        };
        PokemonSpec spec = PokemonSpec.from(Arrays.asList(PokeBlackList).get(new Random().nextInt(Arrays.asList(PokeBlackList).size()-1)));

        return spec.create();
    }

    /**
     * 该宝可梦是不是顶级宝可梦
     * @return 结果
     */
    public static boolean isLegendaryMaxPokemon(Pokemon pokemon){
        String[] PokeBlackList = new String[]{
                "calyrex",
                "glastrier",
                "groudon",
                "kyogre",
                "zamazenta",
                "zacian",
                "eternatus",
                "mewtwo",
                "urshifu",
                "spectrier",
                "lunala",
                "solgaleo",
                "type_null",
                "ho_oh",
                "lugia",
                "yveltal",
                "giratina",
                "xerneas",
                "dialga",
                "palkia",
                "zygarde",
                "arceus",
                "zekrom",
                "reshiram",
                "kyurem"
        };
        System.out.println(PokemonAPI.subString(pokemon.getUnlocalizedName().toLowerCase(),"pixelmon.",".name"));
        return Arrays.asList(PokeBlackList).contains(PokemonAPI.subString(pokemon.getUnlocalizedName().toLowerCase(),"pixelmon.",".name"));
    }

    /**
     * 给玩家随机获取一直传说宝可梦
     * @return 宝可梦
     */
    public static Pokemon getRandomLegendaryPokemon(){
        PokemonSpec spec = PokemonSpec.from(EnumSpecies.randomLegendary().getPokemonName());
        return spec.create();
    }

    /**
     * 给玩家随机获取一直伪传说宝可梦
     * @return 宝可梦
     */
    public static Pokemon getRandomPseudoLegendaryPokemon(){
        String[] PokeBlackList = new String[]{
                "Dragonite",
                "Tyranitar",
                "Salamence",
                "Metagross",
                "Garchomp",
                "Hydreigon",
                "Goodra",
                "Kommo-o",
                "Dragapult"
        };
        PokemonSpec spec = PokemonSpec.from(Arrays.asList(PokeBlackList).get(new Random().nextInt(Arrays.asList(PokeBlackList).size()-1)));

        return spec.create();
    }

    /**
     * 给玩家一个随机的异兽
     * @return 宝可梦
     */
    public static Pokemon getRandomUltraBeastPokemon(){
        String[] PokeBlackList = new String[]{
                "Nihilego",
                "Buzzwole",
                "Pheromosa",
                "Xurkitree",
                "Celesteela",
                "Kartana",
                "Guzzlord",
                "Poipole",
                "Naganadel",
                "Stakataka",
                "Blacephalon"
        };


        PokemonSpec spec = PokemonSpec.from(Arrays.asList(PokeBlackList).get(new Random().nextInt(Arrays.asList(PokeBlackList).size()-1)));

        return spec.create();
    }

    /**
     * 给玩家一个指定的宝可梦
     * @param player 玩家
     */
    public static void GivePokemon(Player player, Boolean shiny,int Iv, int species,Boolean mt, Pokemon...pokemons){
        for (Pokemon pokemon:pokemons) {

            //设置宝可梦的闪光
            pokemon.setShiny(shiny);

            //设置宝可梦的皮肤
            List< IEnumForm > forms = pokemon.getSpecies().getPossibleForms(false);
            pokemon.setForm(forms.get(species));

            //设置宝可梦的MT
            if (mt){
                pokemon.setAbilitySlot(2);
            }

            //设置宝可梦的Iv
            pokemon.getIVs().setStat(StatsType.HP,(new Random().nextInt(25)+1));
            pokemon.getIVs().setStat(StatsType.Speed,(new Random().nextInt(25)+1));
            pokemon.getIVs().setStat(StatsType.Attack,(new Random().nextInt(25)+1));
            pokemon.getIVs().setStat(StatsType.SpecialAttack,(new Random().nextInt(25)+1));
            pokemon.getIVs().setStat(StatsType.Defence,(new Random().nextInt(25)+1));
            pokemon.getIVs().setStat(StatsType.SpecialDefence,(new Random().nextInt(25)+1));
            Set<Integer> set = new HashSet<>();
            do {
                int n = (int) (Math.random() * (6 - 1 + 1)) + 1;
                set.add(n);
            } while (set.size() < Iv);
            set.forEach((o)->{
                switch (o){
                    case 1:
                        pokemon.getIVs().setStat(StatsType.HP,31);
                        break;
                    case 2:
                        pokemon.getIVs().setStat(StatsType.Speed,31);
                        break;
                    case 3:
                        pokemon.getIVs().setStat(StatsType.Attack,31);
                        break;
                    case 4:
                        pokemon.getIVs().setStat(StatsType.SpecialAttack,31);
                        break;
                    case 5:
                        pokemon.getIVs().setStat(StatsType.Defence,31);
                        break;
                    case 6:
                        pokemon.getIVs().setStat(StatsType.SpecialDefence,31);
                        break;
                }
            });


            //给玩家宝可梦
            PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(player.getUniqueId());

            for (Pokemon p:playerPartyStorage.getAll()) {
                if (p==null){
                    playerPartyStorage.add(pokemon);
                    pokemon.getLocalizedName();
                    return;
                }
            }
            Pixelmon.storageManager.getPCForPlayer(player.getUniqueId()).add(pokemon);
        }
    }

    /**
     * 给玩家一个指定的随机宝可梦
     * @param player 玩家
     */
    public static void GiveRandomPokemon(Player player, Boolean shiny,int Iv, int species, Pokemon...pokemons){

        Pokemon pokemon = Arrays.asList(pokemons).get(new Random().nextInt(Arrays.asList(pokemons).size()-1));

        //设置宝可梦的闪光
        pokemon.setShiny(shiny);

        //设置宝可梦的皮肤
        List< IEnumForm > forms = pokemon.getSpecies().getPossibleForms(false);
        pokemon.setForm(forms.get(species));

        //设置宝可梦的Iv
        pokemon = setIv(pokemon,Iv);

        //给玩家宝可梦
        PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(player.getUniqueId());

        for (Pokemon p:playerPartyStorage.getAll()) {
            if (p==null){
                playerPartyStorage.add(pokemon);
                return;
            }
        }
        Pixelmon.storageManager.getPCForPlayer(player.getUniqueId()).add(pokemon);
    }

    /**
     * 自定义宝可梦Iv数量
     * @param pokemon 宝可梦
     * @param Iv Iv数量
     */
    public static Pokemon setIv(Pokemon pokemon,int Iv){
        //设置宝可梦的Iv
        pokemon.getIVs().setStat(StatsType.HP,(new Random().nextInt(25)+1));
        pokemon.getIVs().setStat(StatsType.Speed,(new Random().nextInt(25)+1));
        pokemon.getIVs().setStat(StatsType.Attack,(new Random().nextInt(25)+1));
        pokemon.getIVs().setStat(StatsType.SpecialAttack,(new Random().nextInt(25)+1));
        pokemon.getIVs().setStat(StatsType.Defence,(new Random().nextInt(25)+1));
        pokemon.getIVs().setStat(StatsType.SpecialDefence,(new Random().nextInt(25)+1));
        Set<Integer> set = new HashSet<>();
        do {
            int n = (int) (Math.random() * (6 - 1 + 1)) + 1;
            set.add(n);
        } while (set.size() < Iv);
        set.forEach((o)->{
            switch (o){
                case 1:
                    pokemon.getIVs().setStat(StatsType.HP,31);
                    break;
                case 2:
                    pokemon.getIVs().setStat(StatsType.Speed,31);
                    break;
                case 3:
                    pokemon.getIVs().setStat(StatsType.Attack,31);
                    break;
                case 4:
                    pokemon.getIVs().setStat(StatsType.SpecialAttack,31);
                    break;
                case 5:
                    pokemon.getIVs().setStat(StatsType.Defence,31);
                    break;
                case 6:
                    pokemon.getIVs().setStat(StatsType.SpecialDefence,31);
                    break;
            }
        });

        return pokemon;
    }

    /**
     * 随机给玩家一个超进化石
     * @param player 玩家
     * @return 宝可梦名字
     */
    public static String getRandomEvolution(Player player){
        String[] ItemBlackList = new String[]{
                "PIXELMON_BEEDRILLITE",
                "PIXELMON_BLASTOISINITE",
                "PIXELMON_CAMERUPTITE",
                "PIXELMON_CHARIZARDITE_X",
                "PIXELMON_CHARIZARDITE_Y",
                "PIXELMON_DIANCITE",
                "PIXELMON_GALLADITE",
                "PIXELMON_GARCHOMPITE",
                "PIXELMON_BLAZIKENITE",
                "PIXELMON_GENGARITE",
                "PIXELMON_GARDEVOIRITE",
                "PIXELMON_PIDGEOTITE",
                "PIXELMON_ABOMASITE",
                "PIXELMON_ABSOLITE",
                "PIXELMON_MEWTWONITE_Y",
                "PIXELMON_MEWTWONITE_X",
                "PIXELMON_GLALITITE",
                "PIXELMON_HOUNDOOMINITE",
                "PIXELMON_KANGASKHANITE",
                "PIXELMON_LATIASITE",
                "PIXELMON_LATIOSITE",
                "PIXELMON_GYARADOSITE",
                "PIXELMON_METAGROSSITE",
                "PIXELMON_HERACRONITE",
                "PIXELMON_MEDICHAMITE",
                "PIXELMON_MAWILITE",
                "PIXELMON_MANECTITE",
                "PIXELMON_LUCARIONITE",
                "PIXELMON_LOPUNNITE",
                "PIXELMON_BANETTITE",
                "PIXELMON_AUDINITE",
                "PIXELMON_AMPHAROSITE",
                "PIXELMON_ALTARIANITE",
                "PIXELMON_ALAKAZITE",
                "PIXELMON_AGGRONITE",
                "PIXELMON_AERODACTYLITE",
                "PIXELMON_VENUSAURITE",
                "PIXELMON_TYRANITARITE",
                "PIXELMON_PINSIRITE",
                "PIXELMON_SABLENITE",
                "PIXELMON_SALAMENCITE",
                "PIXELMON_SCIZORITE",
                "PIXELMON_SLOWBRONITE",
                "PIXELMON_SWAMPERTITE",
                "PIXELMON_STEELIXITE",
                "PIXELMON_SHARPEDONITE",
                "PIXELMON_SCEPTILITE"

        };

        ItemStack itemStack = ItemFactoryAPI.getItemStack(Material.getMaterial(Arrays.asList(ItemBlackList).get(new Random().nextInt(Arrays.asList(ItemBlackList).size()-1))));

        player.getInventory().addItem(itemStack);

        return itemStack.getType().name();
    }


    /**
     * 获取范围内最近的玩家
     * @param pixelmonLocation 坐标
     * @return 玩家
     */
    public static Player getRandPlayer(Location pixelmonLocation) {
        double distance = Double.MAX_VALUE;
        Player player = null;
        Iterator<Player> var6 = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
        while (var6.hasNext()) {
            Player p = var6.next();
            Location loc = p.getLocation();
            if (pixelmonLocation.getWorld().getName().equalsIgnoreCase(loc.getWorld().getName()) && pixelmonLocation.distance(loc) < distance) {
                distance = pixelmonLocation.distance(loc);
                player = p;
            }
        }
        return player;
    }

    /**
     * 计算宝可梦价值
     * @param pokemon 宝可梦
     * @return 价格
     */
    public static double getPokemonVault(Pokemon pokemon){
        double money = 0.0;

        //传奇黑名单
        String[] LegendaryBlackList = new String[]{
                "时拉比"
        };
        //普通黑名单
        String[] PokemonBlackList = new String[]{
                "小火马"
        };
        //特性
        if (pokemon.getAbilitySlot()==2){
            money+=2500;
        }

        //传奇
        if (pokemon.isLegendary()){
            if (!Arrays.asList(LegendaryBlackList).contains(pokemon.getLocalizedName())){
                money+=2000;
            }
        }

        //闪光
        if (pokemon.isShiny()){
            money+=400;
        }

        //努力值估算
        for (int ev:pokemon.getEVs().getArray()) {
            money+=ev;
        }

        //个体值估算
        int a = 0;
        for (int iv:pokemon.getIVs().getArray()) {
            money+=iv;
            if (iv==31){
                a++;
            }
        }
        switch (a){
            case 6:
                money+=800;
                break;
            case 5:
                money+=500;
                break;
            case 4:
                money+=200;
                break;
            case 3:
                money+=50;
                break;
            case 2:
                money+=20;
                break;
            case 1:
                money+=10;
        }

        //等级估算
        money+=(pokemon.getLevel()*0.6);

        //会员加成
        if (Bukkit.getPlayer(pokemon.getOwnerPlayerUUID()).hasPermission("group.pikanium")){
            money+=money*0.05;
        }else if (Bukkit.getPlayer(pokemon.getOwnerPlayerUUID()).hasPermission("group.eevee")){
            money+=money*0.15;
        }

        //黑名单宝可梦
        if (Arrays.asList(PokemonBlackList).contains(pokemon.getLocalizedName())){
            money=10;
        }

        return money;
    }

    /**
     * 结束战斗
     * @param player 玩家
     */
    public static void endBattle(Player player) {
        PlayerPartyStorage pps = Pixelmon.storageManager.getParty(player.getUniqueId());
        if (pps != null && BattleRegistry.getBattle(pps.getPlayer()) != null){
            BattleRegistry.getBattle(pps.getPlayer()).endBattle();
            BattleRegistry.deRegisterBattle(BattleRegistry.getBattle(pps.getPlayer()));
        }
    }



    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* 开始截取 */
        return str.substring(strStartIndex, strEndIndex).substring(strStart.length());
    }

    /**
     * 随机字符串
     * @param length 长度
     * @return 字符
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取库存该物品的数量
     * @param inventory 库存
     * @param itemStack 物品
     * @return 物品
     */
    public static ItemStack getAllItem(Inventory inventory,ItemStack itemStack){
        itemStack.setAmount(0);
        for (ItemStack i:inventory) {
            if (i!=null&&i.getType()==itemStack.getType()){
                itemStack.setAmount(itemStack.getAmount()+i.getAmount());
            }
        }
        return itemStack;
    }
}
