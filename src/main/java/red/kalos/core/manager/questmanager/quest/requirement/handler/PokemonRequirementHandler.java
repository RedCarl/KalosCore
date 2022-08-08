package red.kalos.core.manager.questmanager.quest.requirement.handler;

import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;

public interface PokemonRequirementHandler {
    boolean handle(EntityPixelmon pokemon);
}
