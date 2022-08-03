package kim.pokemon.manager.questmanager.quest.requirement;

import org.bukkit.configuration.ConfigurationSection;
import kim.pokemon.manager.questmanager.quest.quest.Quest;

public abstract class Requirement {

    abstract public void handleMessage(String player_id, int message_id, Object[] message, Quest quest);
    abstract public boolean isCompleted(String player_id);
    abstract public boolean isFailed(String player_id);
    abstract public String getText(String player_id);
    abstract public void clear(String player_id);
    abstract public void loadData(ConfigurationSection cs);
    abstract public void saveData(ConfigurationSection cs);

}
