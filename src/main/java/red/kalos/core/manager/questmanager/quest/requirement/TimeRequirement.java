package red.kalos.core.manager.questmanager.quest.requirement;


import com.google.common.collect.Maps;
import org.bukkit.configuration.ConfigurationSection;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.TaskType;
import red.kalos.core.manager.questmanager.quest.quest.Quest;

import java.util.Calendar;
import java.util.Map;

public class TimeRequirement extends Requirement {



    private Map<String,Long> timestamp_map = Maps.newHashMap();
    private Map<String,Boolean> fail_map = Maps.newHashMap();

    @Override
    public void handleMessage(String player_id, int message_id, Object[] message, Quest quest) {
        if(quest.getState(player_id)== QuestState.ACCEPTED_STATE){
            if (message_id == TaskType.TIME_MESSAGE) {
                long time = (Long) message[0];
                long timestamp = getTimestamp(player_id);
                if(timestamp == -1){
                    return;
                }
                if(time > timestamp){
                    setFailed(player_id);
                }
            }
        }
    }

    @Override
    public boolean isCompleted(String player_id) {
        return !isFailed(player_id);
    }

    @Override
    public boolean isFailed(String player_id) {
        if(fail_map.containsKey(player_id)){
            return fail_map.get(player_id).booleanValue();
        }
        return false;
    }

    public void setFailed(String player_id) {
        fail_map.put(player_id,Boolean.TRUE);
    }

    public void setTimestamp(String player_id,long timestamp){
        timestamp_map.put(player_id,timestamp);
    }

    public long getTimestamp(String player_id) {
        if(timestamp_map.containsKey(player_id)){
            return timestamp_map.get(player_id).longValue();
        }
        return -1;
    }

    @Override
    public String getText(String player_id) {
        Calendar calendar = new Calendar.Builder().setInstant(getTimestamp(player_id)).build();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return String.format("%d月%d日 %02d:%02d", month, day, hour, minute);
    }

    @Override
    public void clear(String player_id) {
        timestamp_map.remove(player_id);
        fail_map.remove(player_id);
    }

    @Override
    public void loadData(ConfigurationSection cs) {
        Map<String,Long> timestamp = Maps.newHashMap();
        Map<String,Boolean> fail = Maps.newHashMap();
        if(cs.contains("timestamp")){
            ConfigurationSection leaf = cs.getConfigurationSection("timestamp");
            for (String key : leaf.getKeys(false)) {
                timestamp.put(key,leaf.getLong(key));
            }
        }
        else{
            cs.createSection("timestamp");
        }
        if(cs.contains("fail")){
            ConfigurationSection leaf = cs.getConfigurationSection("fail");
            for (String key : leaf.getKeys(false)) {
                fail.put(key,leaf.getBoolean(key));
            }
        }
        else{
            cs.createSection("fail");
        }
        this.timestamp_map = timestamp;
        this.fail_map = fail;
    }

    @Override
    public void saveData(ConfigurationSection cs) {
        for (String key : cs.getKeys(false)) {
            cs.set(key,null);
        }
        if(!cs.contains("timestamp")){
            cs.createSection("timestamp");
        }
        ConfigurationSection leaf = cs.getConfigurationSection("timestamp");
        for (String key : timestamp_map.keySet()) {
            leaf.set(key,timestamp_map.get(key).longValue());
        }
        if(!cs.contains("fail")){
            cs.createSection("fail");
        }
        leaf = cs.getConfigurationSection("fail");
        for (String key : fail_map.keySet()) {
            leaf.set(key,fail_map.get(key).booleanValue());
        }
    }
}
