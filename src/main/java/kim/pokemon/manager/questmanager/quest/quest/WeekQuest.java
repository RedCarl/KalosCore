package kim.pokemon.manager.questmanager.quest.quest;

import org.bukkit.entity.Player;
import kim.pokemon.manager.questmanager.quest.QuestState;
import kim.pokemon.manager.questmanager.quest.QuestType;

public class WeekQuest extends Quest{

    public WeekQuest(){
        super(QuestState.INVISIBLE_STATE, QuestType.WEEK);
    }

    @Override
    public void accept(Player player) {
        setState(player.getName(),QuestState.ACCEPTED_STATE);
    }

}
