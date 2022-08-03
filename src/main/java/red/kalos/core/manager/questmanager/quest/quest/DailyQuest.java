package red.kalos.core.manager.questmanager.quest.quest;

import org.bukkit.entity.Player;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.QuestType;

public class DailyQuest extends Quest{

    public DailyQuest(){
        super(QuestState.INVISIBLE_STATE, QuestType.DAILY);
    }

    @Override
    public void accept(Player player) {
        setState(player.getName(),QuestState.ACCEPTED_STATE);
    }

}
