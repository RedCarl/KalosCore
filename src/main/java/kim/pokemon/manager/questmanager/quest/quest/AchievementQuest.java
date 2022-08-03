package kim.pokemon.manager.questmanager.quest.quest;

import kim.pokemon.manager.questmanager.quest.QuestState;
import kim.pokemon.manager.questmanager.quest.QuestType;
import org.bukkit.entity.Player;


public class AchievementQuest extends Quest{

    public AchievementQuest(){
        super(QuestState.ACCEPTED_STATE, QuestType.ACHIEVEMENT);
    }

    @Override
    public void accept(Player player) {
        setState(player.getName(),QuestState.ACCEPTED_STATE);
    }
}
