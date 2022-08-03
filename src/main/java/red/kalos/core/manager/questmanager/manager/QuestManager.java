package red.kalos.core.manager.questmanager.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import red.kalos.core.Main;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.QuestType;
import red.kalos.core.manager.questmanager.quest.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class QuestManager {


    private static Map<String, Quest> registeredQuests = Maps.newLinkedHashMap();

    public static void initialize(){
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), QuestManager::save,20*10,20*10);
    }

    public static void save(){
        for (String s : registeredQuests.keySet()) {
            YamlConfiguration yaml = registeredQuests.get(s).saveData(ConfigManager.getQuest(s));
            ConfigManager.saveQuest(yaml,s);
        }
    }

    public static void registerQuest(String name,Quest quest){
        registeredQuests.put(name,quest);
        quest.loadData(ConfigManager.getQuest(name));
    }

    public static void handleMessage(String player_id,int message_id,Object[] message){
        for(Quest quest:registeredQuests.values()){
            quest.handleMessage(player_id,message_id,message);
        }
    }

    public static Quest getQuest(String name){
        if(registeredQuests.containsKey(name)){
            return registeredQuests.get(name);
        }
        return null;
    }

    public static List<Quest> filter(QuestType questType){
        return registeredQuests.values().stream().filter(quest -> quest.getQuestType().equals(questType)).collect(Collectors.toList());
    }

    public static List<Quest> filter(QuestType questType, Player player){
        return registeredQuests.values().stream().filter(quest -> quest.getQuestType().equals(questType)).filter(quest -> quest.getState(player.getName()) != QuestState.INVISIBLE_STATE).collect(Collectors.toList());
    }

    public static List<Quest> filter(QuestType questType, Player player,int questState){
        return registeredQuests.values().stream().filter(quest -> quest.getQuestType().equals(questType)).filter(quest -> quest.getState(player.getName()) == questState).collect(Collectors.toList());
    }

    public static List<Quest> filterAchievementQuests(Player player){
        List<Quest> quests= Lists.newArrayList();
        Collection<Quest> all = registeredQuests.values();
        registeredQuests.entrySet().forEach(entry-> {
            Quest q = entry.getValue();
            if(!q.getQuestType().equals(QuestType.ACHIEVEMENT))return;
            if(!q.isDependsFinished(player.getName()))return;
            if(q.getState(player.getName()) == QuestState.INVISIBLE_STATE)return;
            for(Quest per:all){
                if(per.isDepend(entry.getKey())&&per.isDependsFinished(player.getName()))return;
            }
            quests.add(q);
        });
        return quests;
    }

    public static void refreshDaily(){
        for (String s : registeredQuests.keySet()) {
            Quest quest = registeredQuests.get(s);
            if(quest.getQuestType().equals(QuestType.DAILY)){
                ConfigManager.deleteQuest(s);
                quest.loadData(ConfigManager.getQuest(s));
            }
        }
    }

    public static void refreshWeek(){
        for (String s : registeredQuests.keySet()) {
            Quest quest = registeredQuests.get(s);
            if(quest.getQuestType().equals(QuestType.WEEK)){
                ConfigManager.deleteQuest(s);
                quest.loadData(ConfigManager.getQuest(s));
            }
        }
    }

    public static void distributeQuest(Player p){
        if(filter(QuestType.DAILY,p,QuestState.UNACCEPTED_STATE).size()==0&&filter(QuestType.DAILY,p,QuestState.ACCEPTED_STATE).size()==0&&filter(QuestType.DAILY,p,QuestState.CLOSE_STATE).size()==0){
            List<Quest> all = filter(QuestType.DAILY);
            List<Quest> selected = Lists.newArrayList();
            Random r = new Random();
            for(int i=0;i<5;i++){
                if(all.size()==0)
                    break;
                Quest q = all.get(r.nextInt(all.size()));
                selected.add(q);
                all.remove(q);
            }
            for (Quest quest : selected) {
                quest.setState(p.getName(),QuestState.UNACCEPTED_STATE);
            }
        }
        if(filter(QuestType.WEEK,p,QuestState.UNACCEPTED_STATE).size()==0&&filter(QuestType.WEEK,p,QuestState.ACCEPTED_STATE).size()==0&&filter(QuestType.WEEK,p,QuestState.CLOSE_STATE).size()==0){
            List<Quest> all = filter(QuestType.WEEK);
            List<Quest> selected = Lists.newArrayList();
            Random r = new Random();
            for(int i=0;i<5;i++){
                if(all.size()==0)
                    break;
                Quest q = all.get(r.nextInt(all.size()));
                selected.add(q);
                all.remove(q);
            }
            for (Quest quest : selected) {
                quest.setState(p.getName(),QuestState.UNACCEPTED_STATE);
            }
        }
    }


}
