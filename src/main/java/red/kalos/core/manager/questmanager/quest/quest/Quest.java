package red.kalos.core.manager.questmanager.quest.quest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.manager.questmanager.manager.QuestManager;
import red.kalos.core.manager.questmanager.quest.ItemCreator;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.QuestType;
import red.kalos.core.manager.questmanager.quest.Reward;
import red.kalos.core.manager.questmanager.quest.requirement.*;
import red.kalos.core.manager.questmanager.quest.requirement.handler.ItemRequirementHandler;
import red.kalos.core.manager.questmanager.quest.requirement.handler.PokemonRequirementHandler;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.ItemFacAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Quest {

    private int defaultState;
    private Map<String, Requirement> requirements;
    private QuestType questType;
    private List<String> depends;
    private HashMap<String,Integer> map = Maps.newHashMap();
    private Reward reward = null;
    private ItemCreator itemCreator = null;


    public Quest(int defaultState,QuestType questType){
        this(defaultState,questType,Lists.newArrayList());
    }

    public Quest(int defaultState,QuestType questType,List<String> depends){
        this.defaultState = defaultState;
        this.requirements = Maps.newHashMap();
        this.questType = questType;
        this.depends = depends;
    }

    public boolean isDependsFinished(String player_id){
        for (String depend : depends) {
            Quest quest = QuestManager.getQuest(depend);
            if(quest == null)
                continue;
            if(quest.getState(player_id)!= QuestState.CLOSE_STATE){
                return false;
            }
        }
        return true;
    }

    public void addDepend(String name){
        depends.add(name);
    }

    public boolean isDepend(String name){
        return depends.contains(name);
    }

    public void addRequirement(String name,Requirement requirement){
        this.requirements.put(name,requirement);
    }

    public Map<String,Requirement> getRequirement(){
        return this.requirements;
    }

    public int getState(String player_id){
        if(map.containsKey(player_id)){
            return map.get(player_id);
        }
        return this.defaultState;
    }

    public void setState(String player_id,int state){
        map.put(player_id,state);
    }

    public void setReward(Reward reward){
        this.reward = reward;
    }

    public void setItemCreator(ItemCreator itemCreator){
        this.itemCreator = itemCreator;
    }

    public abstract void accept(Player player);

    public QuestType getQuestType(){
        return this.questType;
    }

    public boolean isSatisfied(String player_id){
        boolean completed = true;
        for(Requirement requirement:requirements.values()){
            if(!requirement.isCompleted(player_id)){
                completed = false;
            }
        }
        return completed;
    }

    public void handleMessage(String player_id,int message_id,Object[] message){
        if(getState(player_id)==QuestState.ACCEPTED_STATE){
            for(Requirement requirement:requirements.values()){
                requirement.handleMessage(player_id,message_id,message,this);
                if(requirement.isFailed(player_id)){
                    setState(player_id,defaultState);
                    clear(player_id);
                    break;
                }
            }
        }
    }

    public void tryFinish(Player p){
        String player_id = p.getName();
        if(getState(player_id)==QuestState.ACCEPTED_STATE){
            if(isSatisfied(player_id)){
                if(reward != null){
                    reward.getReward(p);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    p.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功完成了这个任务并获得了奖励。"));
                }else {
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    p.sendMessage(ColorParser.parse("&8[&c&l!&8] &7在领取奖励的时候系统错误,请联系管理员。"));
                }
                setState(player_id,QuestState.CLOSE_STATE);
                clear(player_id);
            }else {
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                p.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您还没有完成该任务，无法领取奖励。"));
                p.closeInventory();
            }
        }else {
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            p.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您还没有接受该任务，无法完成任务。"));
            p.closeInventory();
        }
    }

    public ItemStack getItemStack(Player player){
        if(this.itemCreator==null){
            return ItemFacAPI.getItemStack(Material.APPLE);
        }
        return this.itemCreator.getItemStack(player);
    };

    public void clear(String player_id){
        for (Requirement requirement : requirements.values()) {
            requirement.clear(player_id);
        }
    }

    public void loadData(YamlConfiguration cs){
        if(!cs.contains("map")){
            cs.createSection("map");
        }
        ConfigurationSection csmap = cs.getConfigurationSection("map");
        HashMap<String,Integer> m = Maps.newHashMap();
        for (String key : csmap.getKeys(false)) {
            m.put(key,csmap.getInt(key));
        }
        this.map = m;
        for (String s : requirements.keySet()) {
            if(!cs.contains(s)){
                cs.createSection(s);
            }
            ConfigurationSection section = cs.getConfigurationSection(s);
            requirements.get(s).loadData(section);
        }
    }

    public YamlConfiguration saveData(YamlConfiguration cs){
        cs.set("map",null);
        if(!cs.contains("map")){
            cs.createSection("map");
        }
        ConfigurationSection csmap = cs.getConfigurationSection("map");
        for (String key : map.keySet()) {
            csmap.set(key, map.get(key));
        }

        for (String s : requirements.keySet()) {
            if(!cs.contains(s)){
                cs.createSection(s);
            }
            ConfigurationSection section = cs.getConfigurationSection(s);
            requirements.get(s).saveData(section);
        }
        return cs;
    }

    public CatchRequirement addCatchRequirement(String name, int count, PokemonRequirementHandler handler){
        CatchRequirement catch1 = new CatchRequirement(handler);
        catch1.setRequireCount(count);
        this.addRequirement(name,catch1);
        return catch1;
    }

    public HatchRequirement addHatchRequirement(String name, int count, PokemonRequirementHandler handler){
        HatchRequirement catch1 = new HatchRequirement(handler);
        catch1.setRequireCount(count);
        this.addRequirement(name,catch1);
        return catch1;
    }

    public WinRequirement addWinRequirement(String name, int count, PokemonRequirementHandler handler){
        WinRequirement catch1 = new WinRequirement(handler);
        catch1.setRequireCount(count);
        this.addRequirement(name,catch1);
        return catch1;
    }

    public CraftRequirement addCraftRequirement(String name, int count, ItemRequirementHandler handler){
        CraftRequirement catch1 = new CraftRequirement(handler);
        catch1.setRequireCount(count);
        this.addRequirement(name,catch1);
        return catch1;
    }
}
