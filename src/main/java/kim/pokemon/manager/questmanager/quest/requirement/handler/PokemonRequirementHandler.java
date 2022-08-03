package kim.pokemon.manager.questmanager.quest.requirement.handler;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;

public interface PokemonRequirementHandler {
    boolean handle(Pokemon pokemon);
}
