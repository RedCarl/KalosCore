package kim.pokemon.util;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.storage.PCStorage;
import com.pixelmonmod.pixelmon.battles.BattleRegistry;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.comm.packetHandlers.OpenScreen;
import com.pixelmonmod.pixelmon.comm.packetHandlers.clientStorage.newStorage.pc.ClientChangeOpenPC;
import com.pixelmonmod.pixelmon.comm.packetHandlers.clientStorage.newStorage.pc.ClientInitializePC;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.EVStore;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.IVStore;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGuiScreen;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.forms.IEnumForm;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import kim.pokemon.Main;
import kim.pokemon.command.PokeAward.PokeFormCommand;
import kim.pokemon.configFile.Data;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.*;
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
     * 随机给玩家一个宝可梦皮肤
     * @param player 玩家
     * @return 皮肤名称
     */
    public static String getRandomCustomSkin(Player player){

        HashMap<String,String> list = new HashMap<>();
        list.put("极北之地","伊裴尔塔尔");
        list.put("五彩缤纷","达克莱伊");
        list.put("杰克","超梦");
        list.put("覅空","烈空坐");


        String Skin;
        String skin;
        String name;

        //宝可梦自定义皮肤
        do {
            Skin = Data.CUSTOM_SKIN.get(new Random().nextInt(Data.CUSTOM_SKIN.size() - 1));

            skin = Skin.substring(0, Skin.indexOf("·"));
            name = Skin.substring(Skin.indexOf("·") + 1);

        } while (list.containsKey(skin) && list.get(skin).equals(name));


        PokeFormCommand.addPokemonForm(player.getName(),name,skin,1);

        return Skin;
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
                "时拉比",
        };

        //普通黑名单
        String[] PokemonBlackList = new String[]{
                "小火马",
        };

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
            int a = ev/2;
            money+=a;
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
     * 计算宝可梦价值
     * @param pokemon 宝可梦
     * @return 价格
     */
    public static int getPokemonPoints(Pokemon pokemon){
        int money = 0;

        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("超梦",88);
        hashMap.put("梦幻",2);
        hashMap.put("洛奇亚",20);
        hashMap.put("凤王",20);
        hashMap.put("时拉比",5);
        hashMap.put("雷吉洛克",10);
        hashMap.put("雷吉艾斯",10);
        hashMap.put("雷吉奇卡斯",25);
        hashMap.put("拉帝亚斯",25);
        hashMap.put("拉帝欧斯",25);
        hashMap.put("盖欧卡",60);
        hashMap.put("固拉多",60);
        hashMap.put("烈空坐",60);
        hashMap.put("基拉祈",5);
        hashMap.put("由克希",3);
        hashMap.put("艾姆利多",3);
        hashMap.put("亚克诺姆",3);
        hashMap.put("帝牙卢卡",45);
        hashMap.put("帕路奇亚",45);
        hashMap.put("席多蓝恩",20);
        hashMap.put("骑拉帝纳",45);
        hashMap.put("克雷色利亚",10);
        hashMap.put("菲欧纳",5);
        hashMap.put("玛纳霏",5);
        hashMap.put("达克莱伊",25);
        hashMap.put("谢米",10);
        hashMap.put("阿尔宙斯",90);
        hashMap.put("比克提尼",24);
        hashMap.put("龙卷云",20);
        hashMap.put("雷电云",15);
        hashMap.put("莱希拉姆",20);
        hashMap.put("捷克罗姆",20);
        hashMap.put("土地云",25);
        hashMap.put("酋雷姆",10);
        hashMap.put("凯路迪欧",5);
        hashMap.put("美洛耶塔",15);
        hashMap.put("盖诺赛克特",10);
        hashMap.put("基格尔德",80);
        hashMap.put("蒂安希",15);
        hashMap.put("胡帕",25);
        hashMap.put("波尔凯尼恩",10);
        hashMap.put("露奈雅拉",45);
        hashMap.put("奈克洛兹玛",25);
        hashMap.put("玛机雅娜",10);
        hashMap.put("玛夏多",20);
        hashMap.put("雷吉艾勒奇",15);
        hashMap.put("雷电鸟",3);
        hashMap.put("火焰鸟",3);
        hashMap.put("急冻鸟",3);
        hashMap.put("哲尔尼亚斯",45);
        hashMap.put("伊裴尔塔尔",45);
        hashMap.put("萨戮德",45);
        hashMap.put("熊徒弟",45);
        hashMap.put("武道熊师",45);
        hashMap.put("卡璞·鸣鸣",15);
        hashMap.put("卡璞·蝶蝶",15);
        hashMap.put("卡璞·哞哞",15);
        hashMap.put("卡璞·鳍鳍",15);
        hashMap.put("索尔迦雷欧",25);
        hashMap.put("无极汰那",60);
        hashMap.put("苍响",32);
        hashMap.put("藏玛然特",15);
        hashMap.put("水君",5);
        hashMap.put("炎帝",5);
        hashMap.put("雷公",5);
        hashMap.put("蕾冠王",44);
        hashMap.put("灵幽马",20);
        hashMap.put("代拉基翁",14);
        hashMap.put("勾帕路翁",24);
        hashMap.put("毕力吉翁",15);
        hashMap.put("科斯莫姆",44);
        hashMap.put("代欧奇希斯",24);
        hashMap.put("雷吉斯奇鲁",14);
        hashMap.put("雷吉铎拉戈",24);
        hashMap.put("雪暴马",24);

        if (hashMap.containsKey(pokemon.getLocalizedName())){
            money = hashMap.get(pokemon.getLocalizedName());
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

    /**
     * 克隆机
     * @param player 玩家
     * @param playerPartyStorage 玩家宝可梦
     * @param pokemon 宝可梦
     * @param slot 位置
     */
    public static void getCloningMachine(Player player,PlayerPartyStorage playerPartyStorage,Pokemon pokemon,int slot,boolean isOn){

        //宝可梦信息储存
        EVStore evStore = Objects.requireNonNull(pokemon).getEVs();
        IVStore ivStore= Objects.requireNonNull(pokemon).getIVs();
        int level = Objects.requireNonNull(pokemon).getLevel();

        //创建超梦并应用梦幻宝可梦属性
        Pokemon Mewtwo = Pixelmon.pokemonFactory.create(EnumSpecies.Mewtwo);
        Mewtwo.getEVs().setStat(StatsType.HP,evStore.getStat(StatsType.HP));
        Mewtwo.getEVs().setStat(StatsType.Attack,evStore.getStat(StatsType.Attack));
        Mewtwo.getEVs().setStat(StatsType.Defence,evStore.getStat(StatsType.Defence));
        Mewtwo.getEVs().setStat(StatsType.Speed,evStore.getStat(StatsType.Speed));
        Mewtwo.getEVs().setStat(StatsType.SpecialDefence,evStore.getStat(StatsType.SpecialDefence));
        Mewtwo.getEVs().setStat(StatsType.SpecialAttack,evStore.getStat(StatsType.SpecialAttack));
        Mewtwo.getIVs().setStat(StatsType.HP,ivStore.getStat(StatsType.HP));
        Mewtwo.getIVs().setStat(StatsType.Attack,ivStore.getStat(StatsType.Attack));
        Mewtwo.getIVs().setStat(StatsType.Defence,ivStore.getStat(StatsType.Defence));
        Mewtwo.getIVs().setStat(StatsType.Speed,ivStore.getStat(StatsType.Speed));
        Mewtwo.getIVs().setStat(StatsType.SpecialDefence,ivStore.getStat(StatsType.SpecialDefence));
        Mewtwo.getIVs().setStat(StatsType.SpecialAttack,ivStore.getStat(StatsType.SpecialAttack));
        Mewtwo.setLevel(level);

        if (isOn){
            playerPartyStorage.set(slot,Mewtwo);
            player.sendTitle(ColorParser.parse("&b卡洛斯の克隆机"),ColorParser.parse("&f成功将 &d梦幻 &f克隆为 &6超梦 &f宝可梦."),0,60,0);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
        }else {
            int i = new Random().nextInt(2);
            if (i==1){
                playerPartyStorage.set(slot,Mewtwo);
                player.sendTitle(ColorParser.parse("&b卡洛斯の克隆机"),ColorParser.parse("&f成功将 &d梦幻 &f克隆为 &6超梦 &f宝可梦."),0,60,0);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            }else {
                playerPartyStorage.set(slot,SpawnPokemon("Ditto"));
                player.sendTitle(ColorParser.parse("&b卡洛斯の克隆机"),ColorParser.parse("&f成功将 &d梦幻 &f克隆为 &9百变怪 &f宝可梦."),0,60,0);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            }
        }


    }


    /**
     * 给玩家概率给一个福
     * @param player 玩家
     * @param pixelmon 宝可梦
     */
    public static void getBills(Player player, EntityPixelmon pixelmon){
        int probability = 0;
        if (pixelmon!=null){
            switch (pixelmon.getBossMode().name()){
                case "NotBoss":
                    probability = 9;
                    break;
                case "Uncommon":
                    probability = 60;
                    break;
                case "Common":
                    probability = 70;
                    break;
                case "Rare":
                    probability = 80;
                    break;
                case "Epic":
                    probability = 90;
                    break;
                case "Equal":
                    probability = 95;
                    break;
                case "Legendary":
                    probability = 100;
                    break;
                case "Ultimate":
                    probability = 98;
                    break;
                case "Spooky":
                    probability = 50;
                    break;
                case "Drowned":
                    probability = 55;
                    break;
            }
        }
        
        
        
        int randomInt =  RandomUtils.nextInt(1,101);

        // 多少概率会掉福
        if(randomInt <= probability){
            int random =  RandomUtils.nextInt(1,101);
            //友善福 2%
            if(random <= 2){
                player.getInventory().addItem(ItemAPI.ysf);
                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，掉落了一个 &c友善福 &7请注意查看背包！"));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                return;
            }
            //富强福 5%
            if(random <= 5){
                player.getInventory().addItem(ItemAPI.fqf);
                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，掉落了一个 &c富强福 &7请注意查看背包！"));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                return;
            }
            //和谐福 8%
            if(random <= 8){
                player.getInventory().addItem(ItemAPI.hxf);
                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，掉落了一个 &c和谐福 &7请注意查看背包！"));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                return;
            }
            //敬业福 15%
            if(random <= 15){
                player.getInventory().addItem(ItemAPI.jyf);
                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，掉落了一个 &c敬业福 &7请注意查看背包！"));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                return;
            }
            //爱国福 50%
            if(random <= 70){
                player.getInventory().addItem(ItemAPI.agf);
                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，掉落了一个 &c爱国福 &7请注意查看背包！"));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        }
    }



    /**
     * 查询神兽
     * @param player 玩家
     */
    public static void check(Player player){
        int money;

        if (player.hasPermission("group.eevee")){
            money = 0;
        }else if (player.hasPermission("group.pikanium")){
            money = 240;
        }else {
            money = 500;
        }

        if (Main.econ.getBalance(player)>=money){
            Main.econ.withdrawPlayer(player,money);
            player.sendMessage(ColorParser.parse("&r"));
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7正在为您查询这片区域的传说宝可梦..."));
            Bukkit.dispatchCommand(player,"checkspawns legendary");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
        }else {
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+Main.econ.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
        }
    }

    public static MinecraftServer getServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance();
    }

    /**
     * 秒格式化
     */
    public static String getDate(Integer date){
        if (date<60) {
            return "&e"+date+" &7秒";
        }else if (date>60&&date<3600) {
            int m = date/60;
            int s = date%60;
            return "&e"+m+" &7分"+" &e"+s+" &7秒";
        }else {
            int h = date/3600;
            int m = (date%3600)/60;
            int s = (date%3600)%60;
            return "&e"+h+" &7小时"+" &e"+m+" &7分"+" &e"+s+" &7秒";
        }
    }


    public static void sendPlayerBaseComponent(List<String> message, List<String> hover){
        List<BaseComponent> lores = new ArrayList<>();

        for (String h : hover) {
            lores.addAll(Arrays.asList(TextComponent.fromLegacyText(h)));
        }
        List<BaseComponent> texts = new ArrayList<>();

        for (String m : message) {
            BaseComponent[] tx = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', m));

            for (BaseComponent baseComponent : tx) {
                HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, lores.toArray(new BaseComponent[0]));
                baseComponent.setHoverEvent(hoverEvent);
            }

            texts.addAll(Arrays.asList(tx));
        }

        Bukkit.spigot().broadcast(texts.toArray(new BaseComponent[0]));
    }
}
