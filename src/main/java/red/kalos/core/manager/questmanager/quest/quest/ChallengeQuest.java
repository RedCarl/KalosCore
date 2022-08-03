package red.kalos.core.manager.questmanager.quest.quest;

import org.bukkit.entity.Player;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.QuestType;
import red.kalos.core.manager.questmanager.quest.requirement.TimeRequirement;


public class ChallengeQuest extends Quest{


    private TimeRequirement timeRequirement = new TimeRequirement();
    private long time;


//    time为分钟
    public ChallengeQuest(long time){
        super(QuestState.UNACCEPTED_STATE, QuestType.CHALLENGE);
        this.time = time;
        addRequirement("time",timeRequirement);
    }

    @Override
    public void accept(Player player) {
        timeRequirement.setTimestamp(player.getName(),System.currentTimeMillis()+time*1000*60);
        setState(player.getName(),QuestState.ACCEPTED_STATE);
    }

    public TimeRequirement getTimeRequirement(){
        return this.timeRequirement;
    }
}
