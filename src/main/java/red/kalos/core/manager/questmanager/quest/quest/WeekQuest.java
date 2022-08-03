package red.kalos.core.manager.questmanager.quest.quest;

import org.bukkit.entity.Player;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.QuestType;

public class WeekQuest extends Quest{

    public WeekQuest(){
        super(QuestState.INVISIBLE_STATE, QuestType.WEEK);
    }

    @Override
    public void accept(Player player) {
        setState(player.getName(),QuestState.ACCEPTED_STATE);
    }

}
