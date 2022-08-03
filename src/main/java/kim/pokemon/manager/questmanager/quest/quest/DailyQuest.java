package kim.pokemon.manager.questmanager.quest.quest;

import org.bukkit.entity.Player;
import kim.pokemon.manager.questmanager.quest.QuestState;
import kim.pokemon.manager.questmanager.quest.QuestType;

public class DailyQuest extends Quest{

    public DailyQuest(){
        super(QuestState.INVISIBLE_STATE, QuestType.DAILY);
    }

    @Override
    public void accept(Player player) {
        setState(player.getName(),QuestState.ACCEPTED_STATE);
    }

}
