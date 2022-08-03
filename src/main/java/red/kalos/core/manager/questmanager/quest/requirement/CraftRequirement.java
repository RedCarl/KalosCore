package red.kalos.core.manager.questmanager.quest.requirement;

import com.google.common.collect.Maps;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.TaskType;
import red.kalos.core.manager.questmanager.quest.quest.Quest;
import red.kalos.core.manager.questmanager.quest.requirement.handler.ItemRequirementHandler;

import java.util.Map;

public class CraftRequirement extends Requirement {
    public ItemRequirementHandler requirementHandler;
    Map<String, Integer> counter = Maps.newHashMap();
    private int requireCount = 999;

    public CraftRequirement(ItemRequirementHandler requirementHandler) {
        this.requirementHandler = requirementHandler;
    }

    @Override
    public void handleMessage(String player_id, int message_id, Object[] message, Quest quest) {
        if (message_id == TaskType.CRAFT_MASSAGE) {
            if (quest.getState(player_id) == QuestState.ACCEPTED_STATE && quest.isDependsFinished(player_id)) {
                int count = getCount(player_id);
                ItemStack item = (ItemStack) message[0];
                if (handle(item)) {
                    count+=item.getAmount();
                }
                if (count > requireCount) {
                    count = requireCount;
                }
                setCount(player_id, count);
            }
        }
    }

    @Override
    public boolean isCompleted(String player_id) {
        return getCount(player_id) == requireCount;
    }

    @Override
    public boolean isFailed(String player_id) {
        return false;
    }

    @Override
    public String getText(String player_id) {
        String info = String.format("%d/%d", getCount(player_id), requireCount);
        String a= info.substring(0, info.indexOf("/"));
        String b= info.substring(a.length()+1);;
        if (Integer.parseInt(a)>=Integer.parseInt(b)){
            return String.format("&a%d&7/&f%d", getCount(player_id), requireCount);
        }
        return String.format("&c%d&7/&f%d", getCount(player_id), requireCount);
    }

    private int getCount(String player_id) {
        if (counter.containsKey(player_id)) {
            return counter.get(player_id);
        }
        return 0;
    }

    private void setCount(String player_id, int count) {
        counter.put(player_id, count);
    }

    public void setRequireCount(int requireCount) {
        this.requireCount = requireCount;
    }

    public boolean handle(ItemStack itemStack) {
        return requirementHandler.handle(itemStack);
    }

    ;

    @Override
    public void clear(String player_id) {
        counter.remove(player_id);
    }

    @Override
    public void loadData(ConfigurationSection cs) {
        Map<String,Integer> map = Maps.newHashMap();
        for (String key : cs.getKeys(false)) {
            map.put(key,cs.getInt(key));
        }
        counter = map;
    }

    @Override
    public void saveData(ConfigurationSection cs) {
        for (String key : cs.getKeys(false)) {
            cs.set(key,null);
        }
        for (String s : counter.keySet()) {
            cs.set(s,counter.get(s).intValue());
        }
    }
}


